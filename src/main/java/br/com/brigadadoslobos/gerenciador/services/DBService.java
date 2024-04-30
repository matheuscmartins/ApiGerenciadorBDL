package br.com.brigadadoslobos.gerenciador.services;

import br.com.brigadadoslobos.gerenciador.domains.*;
import br.com.brigadadoslobos.gerenciador.domains.enums.InfractionType;
import br.com.brigadadoslobos.gerenciador.domains.enums.KmControl;
import br.com.brigadadoslobos.gerenciador.domains.enums.Profile;
import br.com.brigadadoslobos.gerenciador.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private BCryptPasswordEncoder encoder;
    @Autowired
    private MemberPatchRepository memberPatchRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private HeadQuarterRepository headQuarterRepository;
    @Autowired
    private InfractionRepository infractionRepository;
    @Autowired
    private FeedRepository feedRepository;
    @Autowired
    private TravelControlRepository travelControlRepository;

    @Autowired
    private RoleDutyRepository roleDutyRepository;

    public void instanciaDB() {
        BloodType bloodType1 = new BloodType(null, "A+");
        BloodType bloodType2 = new BloodType(null, "O-");
        bloodTypeRepository.saveAll(Arrays.asList(bloodType1, bloodType2));
        Country country1 = new Country(null, "Brasil");
        countryRepository.saveAll(Arrays.asList(country1));
        Uf uf1 = new Uf(null, "São Paulo", "SP", country1);
        Uf uf2 = new Uf(null, "Paraná", "PR", country1);
        Uf uf3 = new Uf(null, "Mato Grosso", "MT", country1);
        ufRepository.saveAll(Arrays.asList(uf1, uf2, uf2));
        City city1 = new City(null, "Maringá", uf2);
        City city2 = new City(null, "Presidente Prudente", uf1);
        City city3 = new City(null, "S. J. do Rio Preto", uf1);
        cityRepository.saveAll(Arrays.asList(city1, city2, city3));
        Patch patch1 = new Patch(null, "Próspero");
        Patch patch2 = new Patch(null, "Meio Escudo");
        Patch patch3 = new Patch(null, "Escudo Fechado");
        patchRepository.saveAll(Arrays.asList(patch1, patch2, patch3));
        Address address1 = new Address(null, "Rua Brasil", "505", null,
                "19040-001", city1);
        Address address2 = new Address(null, "Rua America", "1505", null,
                "19111-021", city1);
        Address address3 = new Address(null, "Rua Paraná", "290", null,
                "12411-300", city2);
        Address address4 = new Address(null, "Rua Homer Simpson", "340", null,
                "20911-333", city2);
        addressRepository.saveAll(Arrays.asList(address1, address2, address3, address4));

        HeadQuarter headQuarter1 = new HeadQuarter(null, "Sede Matriz", address1);
        HeadQuarter headQuarter2 = new HeadQuarter(null, "Sub-Sede Pres. Prudente", address4);
        HeadQuarter headQuarter3 = new HeadQuarter(null, "Sub-Sede Rio Preto", null);
        headQuarterRepository.saveAll(Arrays.asList(headQuarter1, headQuarter2, headQuarter3));
        Member member1 = new Member(null, "Primeiro Nome", "Sobre Nome", "Apelido",
                "00111111-8", "866.865.710-08", "090929302938-AB", "189999-9999", null,
                null, null, "member1@hotmail.com", encoder.encode("senha123"),
                LocalDate.parse("2000-11-10"), LocalDate.now(), null, headQuarter1, address1,
                bloodType1,"caminho da foto");
        member1.addProfile(Profile.ADMIN);
        Member member2 = new Member(null, "Primeiro Nome2", "Sobre Nome2", "Apelido2",
                "00111111-8", "518.218.740-80", "090929302938-AB", "189999-9999", null,
                null, null, "member2@hotmail.com", encoder.encode("senha123"),
                LocalDate.parse("1994-05-01"), LocalDate.parse("2015-08-11"), null, headQuarter2,
                address3, bloodType2,"caminho da foto");
        member2.addProfile(Profile.USUARIO);
        memberRepository.saveAll(Arrays.asList(member1, member2));
        MemberPatch memberPatch1 = new MemberPatch(null, "Reunião Nacional", LocalDate.now(), member1, patch3);
        MemberPatch memberPatch2 = new MemberPatch(null, "Reuni de Diretorias", LocalDate.now(), member1, patch1);
        MemberPatch memberPatch3 = new MemberPatch(null, "Aniversario", LocalDate.now(), member1, patch2);
        MemberPatch memberPatch4 = new MemberPatch(null, "Aniversario", LocalDate.now(), member2, patch1);
        memberPatchRepository.saveAll(Arrays.asList(memberPatch1, memberPatch2, memberPatch3, memberPatch4));

        Infraction infraction1 = new Infraction(null,
                "Texto da trangressão.", LocalDate.parse("2015-08-11"), member1, InfractionType.ESCRITA);
        Infraction infraction2 = new Infraction(null,  "Texto da trangressão222.",
                LocalDate.parse("2010-08-11"), member1, InfractionType.VERBAL);
        Infraction infraction3 = new Infraction(null, "Texto da trangressão333.",
                LocalDate.parse("2013-10-01"), member1, InfractionType.SUSPENSAO);
        Infraction infraction4 = new Infraction(null, "Texto da trangressão333.",
                LocalDate.parse("2022-08-18"), member2, InfractionType.ESCRITA);
        Infraction infraction5 = new Infraction(null, "Texto da trangressão333.",
                LocalDate.parse("2020-01-05"), member2, InfractionType.ESCRITA);
        infractionRepository.saveAll(Arrays.asList(infraction1, infraction2, infraction3, infraction4, infraction5));
        Feed feed1 = new Feed(null, LocalDate.now(), LocalDate.parse("2010-08-11"), "Title 1",
                "Introdução\n" +
                        "O processamento de texto é uma área que vem crescendo, principalmente impulsionado pelas redes sociais, onde os dados não são estruturados como em um banco de dados relacional. Dados não tabelados, que anteriormente eram ignorados, têm hoje uma grande importância no mundo corporativo. O cruzamento de informações está se mostrando uma área lucrativa, por exemplo, no mundo financeiro. Mas todas as corporações podem se beneficiar. A análise do que está sendo produzido em uma organização pode revelar padrões, e essa informação muitas vezes não está gravada em tabelas.\n" +
                        "\n" +
                        "Para ilustrar, vamos criar uma aplicação de exemplo que faz processamento de uma grande quantidade de dados. Esta aplicação vai utilizar os dados da Wikipedia, que disponibiliza uma versão grátis do seu conteúdo. A versão mais recente está disponível aqui.\n" +
                        "\n" +
                        "Modelo de página da Wikipedia:"
                , headQuarter1);
        Feed feed2 = new Feed(null, LocalDate.now(), LocalDate.parse("2020-08-11"), "Title 2",
                "Os métodos disponíveis na classe String podem servir para uma simples junção de texto, comparação de conteúdo, busca de determinados dados em mensagens advindas de sistemas externos, dentre muitas outras funcionalidades. As classes StringBuffer e StringBuilder, por sua vez, podem ser utilizadas para reduzir a criação de objetos desnecessários na memória, possibilitando que uma mesma String seja modificada diversas vezes.\n" +
                        "\n" +
                        "Saber como funciona a criação de objetos String no pool e na memória, bem como a utilização e o resultado da execução dos métodos desta classe melhora o desempenho do código. Esta melhoria também é conseguida através da utilização das classes StringBuffer e StringBuilder, pois elas trabalham com objetos String mutáveis que ocupam menos espaço em memória.\n" +
                        "\n" +
                        "Neste artigo serão demonstradas algumas maneiras de como trabalhar com “texto” na linguagem de programação Java. Das opções disponíveis, String, StringBuffer e StringBuilder são certamente as classes mais utilizadas. Cada uma delas apresenta particularidades com relação à criação de objetos na memória e no relacionamento com as variáveis de referência, assim como apresentam também métodos específicos para a manipulação de seus conteúdos.\n" +
                        "\n" +
                        "Por isso, abordaremos como fazer uso destas classes de maneira eficiente e as melhores situações onde utilizar cada uma delas. Um passo inicial para que o leitor possa trabalhar de modo a atender aos requisitos do sistema/código em que estiver trabalho e adquirir o conhecimento necessário para se aprofundar mais no tema futuramente.\n" +
                        "\n" +
                        "O que são Strings?\n" +
                        "Em Java, String é uma sequência de caracteres utilizada para representação e manipulação de texto. Quando é necessário representar informações textuais em uma aplicação, seja ela para Desktop ou Web, como nome de pessoas, informações sobre endereço ou comentários em uma matéria no jornal, instâncias da classe String serão criadas invariavelmente. Isto é, sempre que precisarmos mostrar alguma informação em um sistema, dificilmente vamos conseguir isso sem o auxílio de Strings."
                , headQuarter2);
        Feed feed3 = new Feed(null, LocalDate.now(), LocalDate.parse("2010-10-10"), "Title 3",
                "Como utilizar Strings em Java\n" +
                        "A classe String permite a criação de suas instâncias de diversas maneiras. Ela possui vários construtores que recebem diversos tipos de parâmetros. Pelo fato de ser uma classe amplamente utilizada, ela também fornece um “atalho” para a criação de forma mais rápida de seus objetos, como pode ser observado no código da Listagem 1."
                , headQuarter2);
        feedRepository.saveAll(Arrays.asList(feed1, feed2, feed3));

        TravelControl travelControl1 = new TravelControl(null,LocalDate.parse("2020-08-11"),200.0,
                "Presidente Prudente - SP", "Maringá - Pr",
                "Viagem Comum", member1, KmControl.KMCHEIO);
        TravelControl travelControl2 = new TravelControl(null,LocalDate.parse("2023-10-11"),1800.0,
                "Presidente Prudente - SP", "Curitiba PR",
                "Viagem Comum", member1, KmControl.KMCHEIO);
        TravelControl travelControl3 = new TravelControl(null,LocalDate.parse("2019-01-14"),100.0,
                "Presidente Prudente - SP", "Santo Inascio - Pr",
                "Viagem Comum", member2, KmControl.MEIOKM);
        TravelControl travelControl4 = new TravelControl(null,LocalDate.parse("2022-12-11"),500.0,
                "Presidente Prudente - SP", "São Paulo SP",
                "Viagem Comum", member2, KmControl.MEIOKM);

        travelControlRepository.saveAll(Arrays.asList(travelControl1, travelControl2,
                travelControl3, travelControl4));
        RoleDuty duty1 = new RoleDuty(null, "Diretor Regional", member1);
        RoleDuty duty2 = new RoleDuty(null, "Diretor Nacional", member2);
        roleDutyRepository.saveAll(Arrays.asList(duty1, duty2));
    }
}
