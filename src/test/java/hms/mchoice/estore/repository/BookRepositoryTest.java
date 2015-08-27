package hms.mchoice.estore.repository;

import hms.mchoice.estore.domain.Book;
import org.junit.Assert;
import org.junit.Test;

public class BookRepositoryTest {

    BookRepositoryImpl testRepo = new BookRepositoryImpl();

    @Test
    public void testInsertBook() throws Exception {
        Book book = new Book();
        book.setBookName("asbd");
        book.setAuthor("unds");
        book.setPrice(3212);
        book.setVersion("uihjhk");
        testRepo.insertBook(book);
        Book resultBook = testRepo.findBook(69);
        Assert.assertEquals(resultBook.getBookName(), "asbd");
        Assert.assertEquals(resultBook.getAuthor(), "unds");
        Assert.assertEquals(resultBook.getPrice(), 3212);
        Assert.assertEquals(resultBook.getVersion(), "uihjhk");
        testRepo.deleteBook(69);
    }

    @Test
    public void testViewDetail() throws Exception {
        Book dbBook = testRepo.findBook(2);
        Assert.assertEquals(dbBook.getBookName(), "cfd");
        Assert.assertEquals(dbBook.getAuthor(), "ami");
        Assert.assertEquals(dbBook.getPrice(), 120);
        Assert.assertEquals(dbBook.getVersion(), "2.2");
    }

    @Test(timeout=100)
    public void testUpdateBook() throws Exception {
        Book book = testRepo.findBook(7);
        testRepo.updateBook(3,"djdf","sdfsd",423,"jjij");
        Book dbBook = testRepo.findBook(3);
        Assert.assertEquals(dbBook.getBookName(), "djdf");
        Assert.assertEquals(dbBook.getAuthor(), "sdfsd");
        Assert.assertEquals(dbBook.getPrice(), 423);
        Assert.assertEquals(dbBook.getVersion(), "jjij");
        testRepo.updateBook(3,book.getBookName(),book.getAuthor(),book.getPrice(),book.getVersion());
    }

    @Test
    public void testDeleteBook() throws Exception {
        Book book = testRepo.findBook(24);
        testRepo.deleteBook(24);
        Book resultBook = testRepo.findBook(26);
        Assert.assertNull(resultBook.getBookName());
        Assert.assertNull(resultBook.getAuthor());
        Assert.assertEquals(resultBook.getPrice(), 0);
        Assert.assertNull(resultBook.getVersion());
        testRepo.insertBook(book);

    }

    @Test
    public void testViewAllDetails() throws Exception {
        Book[] allBooks =testRepo.viewAllDetails();
        int arraySize = 0;
        for (Book book:allBooks) {
            if (book == null) {
                break;
            }else{
                arraySize++;
                Book resultBook = testRepo.findBook(book.getBookId());
                Assert.assertEquals(book.getBookName(), resultBook.getBookName());
            }
        }
        Assert.assertEquals(testRepo.bookCount(),arraySize);
    }

    @Test
    public void testBookCount() throws Exception{
        testRepo.bookCount();
    }


}