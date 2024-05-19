package ro.pastebin.mystringapp.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "texts")
public class Text {

    @Id
    @SequenceGenerator(
            name = "text_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "text_sequence",
            strategy = GenerationType.SEQUENCE
    )
    private Long id;
    private String title;
    private String content;

    public Text() { }

    public Text(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}