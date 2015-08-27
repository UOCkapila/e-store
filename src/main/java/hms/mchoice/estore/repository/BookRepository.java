package hms.mchoice.estore.repository;

import hms.mchoice.estore.domain.Book;

/**
 * Created by kapila on 8/24/15.
 */
public interface BookRepository {

    void insertBook(Book book);

    public Book findBook(int id);

    public void updateBook(int id, String book_name, String book_author, int price, String version);

    public void deleteBook(int id);

    public void makeConnection();

    public Book[] viewAllDetails();

    public int bookCount();
}
