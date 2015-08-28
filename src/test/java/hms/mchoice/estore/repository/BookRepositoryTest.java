package hms.mchoice.estore.repository;

import hms.mchoice.estore.domain.Book;
import org.junit.Assert;
import org.junit.Test;

public class BookRepositoryTest {

    BookRepositoryImpl testRepository = new BookRepositoryImpl();

    @Test
    public void testInsertBook() throws Exception {
        Book book = new Book();
        book.setBookName("hij");
        book.setAuthor("unds");
        book.setPrice(3212);
        book.setVersion("uihjhk");
        testRepository.insertBook(book);
        int bookId = testRepository.findByName("hij");
        Book resultBook = testRepository.findBook(bookId);
        Assert.assertEquals(resultBook.getBookName(), "hij");
        Assert.assertEquals(resultBook.getAuthor(), "unds");
        Assert.assertEquals(resultBook.getPrice(), 3212);
        Assert.assertEquals(resultBook.getVersion(), "uihjhk");
        testRepository.deleteBook(bookId);
    }

    @Test
    public void testViewDetail() throws Exception {
        Book dbBook = testRepository.findBook(2);
        Assert.assertEquals(dbBook.getBookName(), "cfd");
        Assert.assertEquals(dbBook.getAuthor(), "ami");
        Assert.assertEquals(dbBook.getPrice(), 120);
        Assert.assertEquals(dbBook.getVersion(), "2.2");
    }

    @Test(timeout=100)
    public void testUpdateBook() throws Exception {
        Book book = testRepository.findBook(7);
        testRepository.updateBook(3,"djdf","sdfsd",423,"jjij");
        Book dbBook = testRepository.findBook(3);
        Assert.assertEquals(dbBook.getBookName(), "djdf");
        Assert.assertEquals(dbBook.getAuthor(), "sdfsd");
        Assert.assertEquals(dbBook.getPrice(), 423);
        Assert.assertEquals(dbBook.getVersion(), "jjij");
        testRepository.updateBook(3,book.getBookName(),book.getAuthor(),book.getPrice(),book.getVersion());
    }

    @Test
    public void testDeleteBook() throws Exception {
        Book book = testRepository.findBook(24);
        testRepository.deleteBook(24);
        Book resultBook = testRepository.findBook(26);
        Assert.assertNull(resultBook.getBookName());
        Assert.assertNull(resultBook.getAuthor());
        Assert.assertEquals(resultBook.getPrice(), 0);
        Assert.assertNull(resultBook.getVersion());
        testRepository.insertBook(book);
    }

    @Test
    public void testViewAllDetails() throws Exception {
        Book[] allBooks = testRepository.viewAllDetails();
        int arraySize = 0;
        for (Book book:allBooks) {
            if (book == null) {
                break;
            } else {
                arraySize++;
                Book resultBook = testRepository.findBook(book.getBookId());
                Assert.assertEquals(book.getBookName(), resultBook.getBookName());
            }
        }
        Assert.assertEquals(testRepository.bookCount(),arraySize);
    }

    @Test
    public void testBookCount() throws Exception{
        Assert.assertNotEquals(testRepository.bookCount(),0);
    }

    @Test
    public void  testFindByName() throws Exception{
        Assert.assertEquals(testRepository.findByName("as"),48);
    }

}