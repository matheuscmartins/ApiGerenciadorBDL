package br.com.brigadadoslobos.gerenciador.domains.dtos.summarys;

import br.com.brigadadoslobos.gerenciador.domains.enums.FeedVisibility;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;

public class FeedSummaryDTO implements Serializable {

    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate postDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate reunionDate;

    private String title;
    private String text;
    private HeadQuarterSummary headQuarter;
    private FeedVisibility feedVisibility;

    public FeedSummaryDTO(Integer id, LocalDate postDate, LocalDate reunionDate,
                          String title, String text,
                          Integer headQuarterId, String headQuarterDescription,
                          FeedVisibility feedVisibility) {
        this.id = id;
        this.postDate = postDate;
        this.reunionDate = reunionDate;
        this.title = title;
        this.text = text;
        this.headQuarter = new HeadQuarterSummary(headQuarterId, headQuarterDescription);
        this.feedVisibility = feedVisibility;
    }

    public Integer getId() { return id; }
    public LocalDate getPostDate() { return postDate; }
    public LocalDate getReunionDate() { return reunionDate; }
    public String getTitle() { return title; }
    public String getText() { return text; }
    public HeadQuarterSummary getHeadQuarter() { return headQuarter; }
    public FeedVisibility getFeedVisibility() { return feedVisibility; }

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
