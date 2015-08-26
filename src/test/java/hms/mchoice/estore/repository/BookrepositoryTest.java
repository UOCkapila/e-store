package hms.mchoice.estore.repository;

import hms.mchoice.estore.domain.Book;
import junit.framework.TestCase;

public class BookrepositoryTest extends TestCase {

    Bookrepository testRepo = new Bookrepository();

    public void testInsertBook() throws Exception {
        Book book = new Book();
        testRepo.makeBook("kmnd", "bhsd", 9454, "k2");
        assertEquals(testRepo.insertBook(book), true);
    }

    public void testViewDetail() throws Exception {
        assertTrue(testRepo.viewDetail(2));
    }

    public void testUpdateBook() throws Exception {
        assertTrue(testRepo.updateBook(3, "kmnd", "bhsd", 9454, "k2"));
    }

    public void testDeleteBook() throws Exception {
        assertTrue(testRepo.deleteBook(4));
    }

    public void testViewAllDetails() throws Exception {
        assertTrue(testRepo.viewAllDetails());
    }
}