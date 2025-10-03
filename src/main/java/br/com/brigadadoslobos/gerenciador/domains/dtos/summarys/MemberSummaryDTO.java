package br.com.brigadadoslobos.gerenciador.domains.dtos.summarys;

import java.io.Serializable;

public class MemberSummaryDTO implements Serializable {

    private Integer id;
    private String firstName;
    private String lastName;
    private String nickName;
    private HeadQuarterSummary headQuarter;

    // Construtor usado na JPQL
    public MemberSummaryDTO(Integer id, String firstName, String lastName, String nickName, Integer headQuarterId, String headQuarterDescription) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nickName = nickName;
        this.headQuarter = new HeadQuarterSummary(headQuarterId, headQuarterDescription);
    }

    // Getters públicos obrigatórios
    public Integer getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getNickName() { return nickName; }
    public HeadQuarterSummary getHeadQuarter() { return headQuarter; }

    // Classe interna para o headQuarter
    public static class HeadQuarterSummary implements Serializable {
        private Integer id;
        private String description;

        public HeadQuarterSummary(Integer id, String description) {
            this.id = id;
            this.description = description;
        }

        public Integer getId() { return id; }
        public String getDescription() { return description; }
    }
}
