package br.com.brigadadoslobos.gerenciador.services;

import br.com.brigadadoslobos.gerenciador.domains.*;
import br.com.brigadadoslobos.gerenciador.domains.enums.Profile;
import br.com.brigadadoslobos.gerenciador.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private BloodTypeRepository bloodTypeRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private UfRepository ufRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private PatchRepository patchRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberPatchRepository memberPatchRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private HeadQuarterRepository headQuarterRepository;
    @Autowired
    private InfractionRepository infractionRepository;
    public void instanciaDB(){
        BloodType bloodType1 = new BloodType(null, "A+");
        BloodType bloodType2 = new BloodType(null, "O-");
        bloodTypeRepository.saveAll(Arrays.asList(bloodType1, bloodType2));
        Country country1 = new Country(null, "Brasil");
        countryRepository.saveAll(Arrays.asList(country1));
        Uf uf1 = new Uf(null,"São Paulo", "SP", country1);
        Uf uf2 = new Uf(null,"Paraná", "PR", country1);
        Uf uf3 = new Uf(null,"Mato Grosso", "MT", country1);
        ufRepository.saveAll(Arrays.asList(uf1, uf2, uf2));
        City city1 = new City(null, "Maringá", uf2);
        City city2 = new City(null, "Presidente Prudente", uf1);
        City city3 = new City(null, "S. J. do Rio Preto", uf1);
        cityRepository.saveAll(Arrays.asList(city1, city2, city3));
        Patch patch1 = new Patch(null, "Próspero");
        Patch patch2 = new Patch(null, "Meio Escudo");
        Patch patch3 = new Patch(null, "Escudo Fechado");
        patchRepository.saveAll(Arrays.asList(patch1, patch2, patch3));
        Address address1 = new Address(null, "Rua Brasil","505",null,
                "19040-001", city1);
        Address address2 = new Address(null, "Rua America","1505",null,
                "19111-021", city1);
        Address address3 = new Address(null, "Rua Paraná","290",null,
                "12411-300", city2);
        Address address4 = new Address(null, "Rua Homer Simpson","340",null,
                "20911-333", city2);
        addressRepository.saveAll(Arrays.asList(address1,address2,address3,address4));

        HeadQuarter headQuarter1 = new HeadQuarter(null,"Sede Matriz",address1);
        HeadQuarter headQuarter2 = new HeadQuarter(null,"Sub-Sede Pres. Prudente",address4);
        HeadQuarter headQuarter3 = new HeadQuarter(null,"Sub-Sede Rio Preto",null);
        headQuarterRepository.saveAll(Arrays.asList(headQuarter1,headQuarter2,headQuarter3));
        Member member1 = new Member(null, "Primeiro Nome", "Sobre Nome", "Apelido",
                "00111111-8", "866.865.710-08", "090929302938-AB", "189999-9999", null,
                null, null, "member1@hotmail.com", "senha",
                LocalDate.parse("2000-11-10"), LocalDate.now(), null, headQuarter1, address1,
                bloodType1);
        member1.addProfile(Profile.ADMIN);
        Member member2 = new Member(null, "Primeiro Nome2", "Sobre Nome2", "Apelido2",
                "00111111-8", "518.218.740-80", "090929302938-AB", "189999-9999", null,
                null, null, "member2@hotmail.com", "senha",
                LocalDate.parse("1994-05-01"), LocalDate.parse("2015-08-11"), null, headQuarter2,
                address3, bloodType2);
        member2.addProfile(Profile.USUARIO);
        memberRepository.saveAll(Arrays.asList(member1, member2));
        MemberPatch memberPatch1 = new MemberPatch(null, "Reunião Nacional", LocalDate.now(), member1, patch3);
        MemberPatch memberPatch2 = new MemberPatch(null, "Reuni de Diretorias", LocalDate.now(), member1, patch1);
        MemberPatch memberPatch3 = new MemberPatch(null, "Aniversario", LocalDate.now(), member1, patch2);
        MemberPatch memberPatch4 = new MemberPatch(null, "Aniversario", LocalDate.now(), member2, patch1);
        memberPatchRepository.saveAll(Arrays.asList(memberPatch1, memberPatch2, memberPatch3, memberPatch4));
        Infraction infraction1 = new Infraction(null,"Advertencia Escrita",
                "Texto da trangressão.",LocalDate.parse("2015-08-11"),member1);
        Infraction infraction2 = new Infraction(null,"Advertencia Verbal","Texto da trangressão222.",
                LocalDate.parse("2010-08-11"), member1);
        Infraction infraction3 = new Infraction(null,"Suspensão do Colete","Texto da trangressão333.",
                LocalDate.parse("2013-10-01"), member1);
        Infraction infraction4 = new Infraction(null,"Advertencia Verbal","Texto da trangressão333.",
                LocalDate.parse("2022-08-18"), member2);
        Infraction infraction5 = new Infraction(null,"Advertencia Verbal","Texto da trangressão333.",
                LocalDate.parse("2020-01-05"), member2);
        infractionRepository.saveAll(Arrays.asList(infraction1, infraction2, infraction3, infraction4, infraction5));

    }
}
