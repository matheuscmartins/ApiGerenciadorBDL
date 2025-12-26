package br.com.brigadadoslobos.gerenciador.domains.dtos;

import br.com.brigadadoslobos.gerenciador.domains.Feed;
import br.com.brigadadoslobos.gerenciador.domains.HeadQuarter;
import br.com.brigadadoslobos.gerenciador.domains.enums.FeedVisibility;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

public class FeedDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate postDate;
    @NotNull(message = "O campo Data de Reunião é requerido")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate reunionDate;
    @NotNull(message = "O campo Titulo é requerido")
    private String title;
    @NotNull(message = "O campo Texto é requerido")
    @Column(columnDefinition = "TEXT")
    private String text;
    @NotNull(message = "O campo Sede é requerido")
    private HeadQuarter headQuarter;
    @Column(nullable = false)
    private FeedVisibility feedVisibility;
    public FeedDTO(){}

    public FeedDTO(Feed obj) {
        this.id = obj.getId();
        this.postDate = obj.getPostDate();
        this.reunionDate = obj.getReunionDate();
        this.title = obj.getTitle();
        this.text = obj.getText();
        this.headQuarter = obj.getHeadQuarter();
        this.feedVisibility = obj.getFeedVisibility();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public LocalDate getReunionDate() {
        return reunionDate;
    }

    public void setReunionDate(LocalDate reunionDate) {
        this.reunionDate = reunionDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public HeadQuarter getHeadQuarter() {
        return headQuarter;
    }

    public void setHeadQuarter(HeadQuarter headQuarter) {
        this.headQuarter = headQuarter;
    }
    public FeedVisibility getFeedVisibility() {
        return feedVisibility;
    }

    public void setFeedVisibility(FeedVisibility feedVisibility) {
        this.feedVisibility = feedVisibility;
    }
}