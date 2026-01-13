package practise.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
    private int id;

    @NotEmpty(message = "Title should not be empty")
    @Size(min=1, max=150, message = "Title should be between 1 and 150 characters")
    private String title;

    @NotEmpty(message = "Author`s name should not be empty")
    @Size(min=1, max=100, message = "Author`s name should be between 1 and 100 characters")
    private String author;

    @Min(value=0, message = "Release year should be over than 0")
    private int releaseDate;

    public Book() {}

    public Book(String title, String author, int releaseDate, int id) {
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                '}';
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {
        this.releaseDate = releaseDate;
    }
}
