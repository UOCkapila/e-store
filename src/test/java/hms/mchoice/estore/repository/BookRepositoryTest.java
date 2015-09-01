package hms.mchoice.estore.repository;

import hms.mchoice.estore.domain.Book;
import org.junit.After;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class BookRepositoryTest {

    //BookRepository testRepository = new BookRepositoryImpl();
    BookRepository testRepository = new BookImplMongoDB();
    static Book book = new Book();

    @BeforeClass
    public static void createBook(){
        book.setBookName("hij");
        book.setAuthor("unds");
        book.setPrice(3212);
        book.setVersion("uihjhk");
    }

    @Test
    public void testInsertBook() throws Exception {
        testRepository.insertBook(book);
        Book oldBook = testRepository.findByName("hij");
        int bookId = oldBook.getBookId();
        Book resultBook = testRepository.findBook(bookId);
        Assert.assertEquals(resultBook.getBookName(), "hij");
        Assert.assertEquals(resultBook.getAuthor(), "unds");
        Assert.assertEquals(resultBook.getPrice(), 3212);
        Assert.assertEquals(resultBook.getVersion(), "uihjhk");
    }

    @After
    public void deleteRecode(){
        testRepository.deleteBook(book);
    }

    @Test
    public void testViewDetail() throws Exception {
        Book dbBook = testRepository.findBook(4);
        Assert.assertEquals(dbBook.getBookName(), "hij");
        Assert.assertEquals(dbBook.getAuthor(), "unds");
        Assert.assertEquals(dbBook.getPrice(), 3212);
        Assert.assertEquals(dbBook.getVersion(), "uihjhk");
    }

    @Test()
    public void testUpdateBook() throws Exception {
        Book newBook = testRepository.findBook(1) ;
        newBook.setBookName("djdf");
        newBook.setAuthor("sdfsd");
        newBook.setPrice(423);
        newBook.setVersion("jjij");

        testRepository.updateBook(newBook);
        Book dbBook = testRepository.findBook(1);

        Assert.assertEquals(dbBook.getBookName(), "djdf");
        Assert.assertEquals(dbBook.getAuthor(), "sdfsd");
        Assert.assertEquals(dbBook.getPrice(), 423);
        Assert.assertEquals(dbBook.getVersion(), "jjij");

    }

    @Test
    public void testDeleteBook() throws Exception {
        Book book = testRepository.findBook(5);
        testRepository.deleteBook(book);

        Book resultBook = testRepository.findBook(3);

        Assert.assertNull(resultBook.getBookName());
        Assert.assertNull(resultBook.getAuthor());
        Assert.assertEquals(resultBook.getPrice(), 0);
        Assert.assertNull(resultBook.getVersion());

    }

    @Test
    public void testViewAllDetails() throws Exception {
        List<Book> allBooks = testRepository.viewAllDetails();

        for (Book book:allBooks) {
            Book resultBook = testRepository.findBook(book.getBookId());
            Assert.assertEquals(book.getBookName(), resultBook.getBookName());
        }
        Assert.assertEquals(testRepository.bookCount(),allBooks.size());
    }

    @Test
    public void testBookCount() throws Exception{
        Assert.assertNotEquals(testRepository.bookCount(),0);
    }

    @Test
    public void  testFindByName() throws Exception{
        Book newBook = testRepository.findByName("hij");
        Assert.assertEquals(newBook.getBookId(),4);
    }

}