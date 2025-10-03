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
        BloodType bloodType3 = new BloodType(null, "B-");
        bloodTypeRepository.saveAll(Arrays.asList(bloodType1, bloodType2, bloodType3));

        Country country1 = new Country(null, "Brasil");
        countryRepository.saveAll(Arrays.asList(country1));

        Uf uf1 = new Uf(null, "São Paulo", "SP", country1);
        Uf uf2 = new Uf(null, "Paraná", "PR", country1);
        Uf uf3 = new Uf(null, "Mato Grosso", "MT", country1);
        ufRepository.saveAll(Arrays.asList(uf1, uf2, uf3));

        City city1 = new City(null, "Maringá", uf2);
        City city2 = new City(null, "Presidente Prudente", uf1);
        City city3 = new City(null, "Monte Alto", uf1);
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
                "12411-300", city3);
        Address address4 = new Address(null, "Rua Homer Simpson", "340", null,
                "20911-333", city2);
        addressRepository.saveAll(Arrays.asList(address1, address2, address3, address4));

        HeadQuarter headQuarter1 = new HeadQuarter(null, "Sede Matriz Maringá", address1);
        HeadQuarter headQuarter2 = new HeadQuarter(null, "Sub-Sede Pres. Prudente", address4);
        HeadQuarter headQuarter3 = new HeadQuarter(null, "Sub-Sede Monte Alto", address3);
        headQuarterRepository.saveAll(Arrays.asList(headQuarter1, headQuarter2, headQuarter3));

        Member member1 = new Member(null, "Primeiro Nome", "Sobre Nome", "Apelido",
                "00111111-8", "866.865.710-08", "090929302938-AB", "189999-9999", null,
                null, null, "member1@hotmail.com", encoder.encode("senha123"),
                LocalDate.parse("2000-11-22"), LocalDate.now(), null, headQuarter1, address1,
                bloodType1,"caminho da foto");
        member1.addProfile(Profile.ADMIN);
        Member member2 = new Member(null, "Primeiro Nome2", "Sobre Nome2", "Apelido2",
                "00111111-8", "518.218.740-80", "090929302938-AB", "189999-9999", null,
                null, null, "member2@hotmail.com", encoder.encode("senha123"),
                LocalDate.parse("1994-05-01"), LocalDate.parse("2015-08-28"), null, headQuarter2,
                address3, bloodType3,"caminho da foto");
        member2.addProfile(Profile.USUARIO);
        Member member3 = new Member(null, "Primeiro Nome3", "Sobre Nome3", "Apelido3",
                "00111234-9", "430.924.330-41", "090929302940-AB", "189999-534", null,
                null, null, "member3@hotmail.com", encoder.encode("senha123"),
                LocalDate.parse("1990-05-01"), LocalDate.parse("2015-08-28"), null, headQuarter3,
                address2, bloodType2,"caminho da foto");
        member2.addProfile(Profile.COMANDO);
        memberRepository.saveAll(Arrays.asList(member1, member2, member3));

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

        Feed feed1 = new Feed(null, LocalDate.now(), LocalDate.parse("2024-08-11"), "Introdução as Estratégias",
                "Introdução\n" +
                        "Nas últimas semanas temos acompanhado a necessidade de alinhar as estratégias comerciais da empresa com as metas do próximo trimestre. A concorrência está cada vez mais agressiva, e precisamos definir quais serão os diferenciais que vamos oferecer. Uma comunicação clara entre os times de vendas, marketing e operações é essencial para evitar retrabalhos e garantir que os prazos sejam cumpridos. O objetivo da reunião de hoje é justamente consolidar esse alinhamento, identificando pontos críticos e oportunidades que possam ser exploradas.\n" +
                        "\n" +
                        "Para exemplificar, vamos analisar o lançamento do novo produto que está programado para o próximo mês. Precisamos verificar se todos os materiais de divulgação estarão prontos, se a equipe de suporte recebeu treinamento adequado e se a área de logística consegue atender a demanda estimada. Essa checagem é fundamental para evitar gargalos no processo e assegurar que a experiência do cliente seja positiva.\n" +
                        "\n" +
                        "Fim:"
                , headQuarter1);
        Feed feed2 = new Feed(null, LocalDate.now(), LocalDate.parse("2025-01-11"), "Metas de Vendas",
                "Os tópicos discutidos na reunião podem envolver desde a definição de metas de vendas, revisão do desempenho das equipes, análise de resultados financeiros, até o planejamento de ações estratégicas para o próximo trimestre. Além disso, é comum que sejam abordadas demandas específicas trazidas pelos gestores de cada área, garantindo que todas as frentes da organização estejam alinhadas.\n" +
                        "\n" +
                        "Ter clareza nos objetivos de cada reunião e registrar as decisões tomadas facilita o acompanhamento posterior. Esse registro formal ajuda a evitar retrabalhos e garante que cada responsável saiba exatamente quais tarefas deve executar. A utilização de atas ou relatórios também contribui para que os times mantenham a mesma visão e direcionamento sobre as prioridades da empresa.\n" +
                        "\n" +
                        "Nesse contexto, a preparação prévia dos participantes é fundamental. Quando cada setor traz dados, indicadores e pontos de atenção bem estruturados, as discussões se tornam mais objetivas e focadas em soluções práticas. Esse processo otimiza o tempo e torna os encontros muito mais produtivos, já que se evita prolongar debates em assuntos secundários.\n" +
                        "\n" +
                        "Por isso, reforça-se a importância da colaboração entre as equipes, da definição clara de responsáveis e da comunicação contínua após o encontro. Esse é um passo essencial para transformar o que foi discutido em ações concretas que realmente tragam impacto positivo nos resultados do negócio.\n" +
                        "\n" +
                        "O que são reuniões estratégicas?\n" +
                        "No ambiente corporativo, reuniões estratégicas são encontros voltados para alinhar objetivos de médio e longo prazo. Elas não tratam apenas de tarefas operacionais do dia a dia, mas buscam identificar oportunidades de crescimento, revisar processos e antecipar riscos. Independentemente do porte da empresa, esse tipo de reunião é indispensável para manter a competitividade e assegurar que todos estejam trabalhando em direção aos mesmos objetivos."
                , headQuarter2);
        Feed feed3 = new Feed(null, LocalDate.now(), LocalDate.parse("2024-10-10"), "Reuniões de Negócios",
                "Como conduzir reuniões de negócios\n" +
                        "A realização de reuniões empresariais pode ocorrer de diversas maneiras. Elas podem ser presenciais, virtuais ou híbridas, dependendo da necessidade e da disponibilidade das equipes. Por serem uma prática amplamente utilizada no ambiente corporativo, também existem formatos mais rápidos e objetivos de condução, como reuniões de alinhamento diárias ou semanais, que permitem acelerar a tomada de decisão, como pode ser observado no exemplo apresentado na Agenda 1."

                , headQuarter2);
        feedRepository.saveAll(Arrays.asList(feed1, feed2, feed3));

        TravelControl travelControl1 = new TravelControl(null,LocalDate.parse("2024-08-11"),200.0,
                "Presidente Prudente - SP", "Maringá - Pr",
                "Viagem Comum", member1, KmControl.KMCHEIO);
        TravelControl travelControl2 = new TravelControl(null,LocalDate.parse("2024-10-11"),1800.0,
                "Presidente Prudente - SP", "Curitiba PR",
                "Viagem Comum", member1, KmControl.KMCHEIO);
        TravelControl travelControl3 = new TravelControl(null,LocalDate.parse("2024-01-14"),100.0,
                "Presidente Prudente - SP", "Santo Inascio - Pr",
                "Viagem Comum", member2, KmControl.MEIOKM);
        TravelControl travelControl4 = new TravelControl(null,LocalDate.parse("2024-12-11"),500.0,
                "Presidente Prudente - SP", "São Paulo SP",
                "Viagem Comum", member2, KmControl.MEIOKM);
        travelControlRepository.saveAll(Arrays.asList(travelControl1, travelControl2,
                travelControl3, travelControl4));

        RoleDuty duty1 = new RoleDuty(null, "Diretor Regional", LocalDate.now(), null, member1);
        RoleDuty duty2 = new RoleDuty(null, "Diretor Nacional", LocalDate.now(), null, member2);
        roleDutyRepository.saveAll(Arrays.asList(duty1, duty2));
    }
}
