package br.com.brigadadoslobos.gerenciador.services;

import br.com.brigadadoslobos.gerenciador.domains.Member;
import br.com.brigadadoslobos.gerenciador.repositories.MemberRepository;
import br.com.brigadadoslobos.gerenciador.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MemberRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> user = repository.findByEmail(email);
        if (user.isPresent()) {
            Member m = user.get();
            Integer hqId = (m.getHeadQuarter() != null) ? m.getHeadQuarter().getId() : null;

            return new UserSS(
                    m.getId(),
                    m.getEmail(),
                    m.getPassword(),
                    hqId,
                    m.getProfiles()
            );
        }
        throw new UsernameNotFoundException(email);
    }
}
