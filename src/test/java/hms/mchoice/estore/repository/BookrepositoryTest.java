package hms.mchoice.estore.repository;

import hms.mchoice.estore.domain.Book;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;

public class BookrepositoryTest extends TestCase {

    Bookrepository testRepo = new Bookrepository();

    @BeforeClass
    public void testMakeConnection() throws Exception{
        assertTrue(testRepo.makeConnection());
    }
    @Test
    public void testInsertBook() throws Exception {
        Book book = new Book();
        testRepo.makeBook("kmnd", "bhsd", 9454, "k2");
        assertEquals(testRepo.insertBook(book), true);
    }

    @Test
    public void testViewDetail() throws Exception {
        assertTrue(testRepo.viewDetail(2));
    }

    @Test(timeout=1)
    public void testUpdateBook() throws Exception {
        assertTrue(testRepo.updateBook(3, "kmnd", "bhsd", 9454, "k2"));
    }

    @Test
    public void testDeleteBook() throws Exception {
        assertTrue(testRepo.deleteBook(4));
    }

    @Test
    public void testViewAllDetails() throws Exception {
        assertTrue(testRepo.viewAllDetails());
    }

}