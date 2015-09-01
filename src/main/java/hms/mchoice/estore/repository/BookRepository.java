package hms.mchoice.estore.repository;

import hms.mchoice.estore.domain.Book;

import java.util.List;

/**
 * Created by kapila on 8/24/15.
 */
public interface BookRepository {

    void insertBook(Book book);

    public Book findBook(int id);

    public void updateBook(Book book);

    public void deleteBook(Book book);

    public void makeConnection();

    public List viewAllDetails();

    public int bookCount();

    public Book findByName(String bookName);
}
