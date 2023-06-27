package br.com.brigadadoslobos.gerenciador.domains;

import br.com.brigadadoslobos.gerenciador.domains.dtos.FeedDTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
public class Feed implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate postDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate reunionDate;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String text;
    @ManyToOne
    @JoinColumn(name = "headQuarter_id")
    private HeadQuarter headQuarter;

    public Feed() {
    }

    public Feed(Integer id, LocalDate postDate, LocalDate reunionDate, String title, String text, HeadQuarter headQuarter) {
        this.id = id;
        this.postDate = postDate;
        this.reunionDate = reunionDate;
        this.title = title;
        this.text = text;
        this.headQuarter = headQuarter;
    }
    public Feed(FeedDTO obj) {
        this.id = obj.getId();
        this.postDate = obj.getPostDate();
        this.reunionDate = obj.getReunionDate();
        this.title = obj.getTitle();
        this.text = obj.getText();
        this.headQuarter = obj.getHeadQuarter();
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
}
