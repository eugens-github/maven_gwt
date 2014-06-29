package net.ere.tmp.maven_gwt.spring.model;


import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Blog {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "FK_AUTHOR", nullable = false)
    private Author author;

    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Blog() {
    }

    public Blog(String title, String text) {
        content = text;
        this.title = title;
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
