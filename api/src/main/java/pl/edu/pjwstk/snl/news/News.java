package pl.edu.pjwstk.snl.news;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "news")
@Getter
@Setter
@NoArgsConstructor
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description", columnDefinition = "text", nullable = true)
    private String description;

    private String author;

    private LocalDate date;

    private String title;

    @Column(columnDefinition = "bytea")
    private byte[] image;

    public News(String description, String author, LocalDate date, String title, byte[] image) {
        this.description = description;
        this.author = author;
        this.date = date;
        this.title = title;
        this.image = image;
    }

}
