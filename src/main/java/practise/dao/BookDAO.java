package practise.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import practise.models.Book;
import practise.models.Person;

import java.util.List;
import java.util.Optional;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book show(int id) {
        return jdbcTemplate.query("select * from book where id = ?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class))
                .stream().findFirst().orElse(null);
    }

    public void save(Book book) {
        System.out.println(book);
        jdbcTemplate.update("INSERT INTO book(title, author, releasedate) VALUES (?, ?, ?)",  book.getTitle(), book.getAuthor(), book.getReleaseDate());
    }

    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE book SET title=?, author=?, releasedate=? Where id=?",
                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getReleaseDate(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM book WHERE id=?", id);
    }


    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("SELECT person.* from book join person on book.person_id = person.id where book.id = ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findFirst();
    }

    public void absolve(int id) {
        jdbcTemplate.update("UPDATE book SET person_id=NULL WHERE id=?", id);
    }

    public void assign(int bookId, Person person) {
        jdbcTemplate.update("UPDATE book SET person_id=? WHERE id=?", person.getId(), bookId);
    }
}
