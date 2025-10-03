package br.com.brigadadoslobos.gerenciador.domains.dtos.summarys;

import java.io.Serializable;

public class AddressSummaryDTO implements Serializable {

    private Integer id;
    private String logradouro;
    private String number;
    private String postCode;
    private CitySummary city;

    public AddressSummaryDTO(Integer id, String logradouro, String number, String postCode,
                             Integer cityId, String cityName, String ufAcronym) {
        this.id = id;
        this.logradouro = logradouro;
        this.number = number;
        this.postCode = postCode;
        this.city = new CitySummary(cityId, cityName, ufAcronym);
    }
    public AddressSummaryDTO(Integer id,
                             Integer cityId, String cityName, String ufAcronym) {
        this.id = id;
        this.city = new CitySummary(cityId, cityName, ufAcronym);
    }


    public Integer getId() { return id; }
    public String getLogradouro() { return logradouro; }
    public String getNumber() { return number; }
    public String getPostCode() { return postCode; }
    public CitySummary getCity() { return city; }

    public static class CitySummary implements Serializable {
        private Integer id;
        private String name;
        private UfSummary uf;

        public CitySummary(Integer id, String name, String ufAcronym) {
            this.id = id;
            this.name = name;
            this.uf = new UfSummary(ufAcronym);
        }

        public Integer getId() { return id; }
        public String getName() { return name; }
        public UfSummary getUf() { return uf; }
    }

    public static class UfSummary implements Serializable {
        private String acronym;

        public UfSummary(String acronym) { this.acronym = acronym; }

        public String getAcronym() { return acronym; }
    }
}