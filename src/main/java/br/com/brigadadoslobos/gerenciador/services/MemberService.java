package br.com.brigadadoslobos.gerenciador.services;

import br.com.brigadadoslobos.gerenciador.domains.Member;
import br.com.brigadadoslobos.gerenciador.domains.dtos.MemberDTO;
import br.com.brigadadoslobos.gerenciador.domains.dtos.summarys.MemberSummaryDTO;
import br.com.brigadadoslobos.gerenciador.repositories.MemberRepository;
import br.com.brigadadoslobos.gerenciador.services.exceptions.DataIntegrityViolationException;
import br.com.brigadadoslobos.gerenciador.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    @Autowired
    private MemberRepository repository;
    @Autowired
    private BCryptPasswordEncoder encoder;
    private static final String UPLOAD_DIR = "uploads/profiles/";
    private static final List<String> ALLOWED_TYPES = Arrays.asList(
            "image/jpeg", "image/jpg", "image/png", "image/gif", "image/webp"
    );
    private static final long MAX_SIZE = 5 * 1024 * 1024;
    public Member findById(Integer id){
        Optional<Member> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id));
    }

   /* public List<Member> findAll() {
        return repository.findAll();
    } */
   public List<MemberSummaryDTO> findAllSummary() {
       return repository.findAllSummary();
   }

    public Member create(MemberDTO objDTO) {
        objDTO.setId(null);
        objDTO.setPassword(encoder.encode(objDTO.getPassword()));
        validaPorCpfEmail(objDTO);
        Member newObj = new Member(objDTO);
        return repository.save(newObj);
    }
    public Member update(Integer id, MemberDTO objDTO) {
        objDTO.setId(id);
        Member oldObj = findById(id);

        if (!objDTO.getPassword().equals(oldObj.getPassword()))
            objDTO.setPassword(encoder.encode(objDTO.getPassword()));

        validaPorCpfEmail(objDTO);
        oldObj = new Member(objDTO);
        return repository.save(oldObj);
    }
     /*
    public void delete(Integer id) {
        Member obj = findById(id);
       //throw new DataIntegrityViolationException("Não pode ser excluido");
       repository.deleteById(id);
    }
      */
    private void validaPorCpfEmail(MemberDTO objDTO){
        Optional<Member> obj = repository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }
        obj = repository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }
    public List<MemberSummaryDTO> findSummariesByHeadQuarterId(Integer id) {
        Optional <List<MemberSummaryDTO>> listOptional = repository.findSummariesByHeadQuarterId(id);
        return listOptional.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: "+ id));
    }
    public String uploadProfileImage(Integer memberId, MultipartFile file) throws IOException {

        // 1. Validar se o membro existe
        Optional<Member> memberOpt = repository.findById(memberId);
        if (!memberOpt.isPresent()) {
            throw new IllegalArgumentException("Membro não encontrado!");
        }
        Member member = memberOpt.get();

        // 2. Validar tipo de arquivo
        String contentType = file.getContentType();
        if (!ALLOWED_TYPES.contains(contentType)) {
            throw new IllegalArgumentException(
                    "Tipo de arquivo não permitido! Use: JPG, PNG, GIF ou WEBP"
            );
        }

        // 3. Validar tamanho
        if (file.getSize() > MAX_SIZE) {
            throw new IllegalArgumentException(
                    "Arquivo muito grande! Tamanho máximo: 5MB"
            );
        }

        // 4. Gerar nome único para o arquivo
        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String extension = getFileExtension(file.getOriginalFilename());
        String fileName = memberId + "_" + timestamp + extension;

        // 5. Criar diretório se não existir
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 6. Deletar foto antiga se existir
        if (member.getImagePath() != null && !member.getImagePath().isEmpty()) {
            try {
                Path oldFile = Paths.get(member.getImagePath());
                Files.deleteIfExists(oldFile);
            } catch (IOException e) {
                // Log do erro, mas não para o processo
                System.err.println("Erro ao deletar foto antiga: " + e.getMessage());
            }
        }

        // 7. Salvar arquivo no servidor
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // 8. Gerar caminho relativo para salvar no banco
        String relativePath = UPLOAD_DIR + fileName;

        // 9. Atualizar caminho no banco de dados
        member.setImagePath(relativePath);
        repository.save(member);

        // 10. Retornar caminho da imagem
        return relativePath;
    }

    /**
     * Extrai extensão do arquivo
     */
    private String getFileExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return ".jpg";
        }
        int lastDot = filename.lastIndexOf('.');
        if (lastDot == -1) {
            return ".jpg";
        }
        return filename.substring(lastDot);
    }
    /**
     * Altera a senha do usuário
     */
    public void updatePassword(Integer memberId, String oldPassword, String newPassword) {
        // 1. Buscar membro
        Member member = findById(memberId);

        // 2. Verificar se a senha atual está correta (COMPARAR HASH)
        if (!encoder.matches(oldPassword, member.getPassword())) {
            throw new IllegalArgumentException("Senha atual incorreta!");
        }

        // 3. Validar nova senha
        if (newPassword == null || newPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Nova senha não pode ser vazia!");
        }

        if (newPassword.length() < 6) {
            throw new IllegalArgumentException("Nova senha deve ter no mínimo 6 caracteres!");
        }

        // 4. Verificar se a nova senha é diferente da atual
        if (encoder.matches(newPassword, member.getPassword())) {
            throw new IllegalArgumentException("Nova senha deve ser diferente da senha atual!");
        }

        // 5. Atualizar senha (com criptografia BCrypt)
        member.setPassword(encoder.encode(newPassword));
        repository.save(member);

        System.out.println("Senha alterada com sucesso para o membro ID: " + memberId);
    }
}
