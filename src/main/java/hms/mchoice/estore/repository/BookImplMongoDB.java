package hms.mchoice.estore.repository;

import com.mongodb.*;
import hms.mchoice.estore.domain.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kapila on 8/31/15.
 */
public class BookImplMongoDB implements BookRepository {

    MongoClient mongoClient;
    DB db;
    int count;

    public BookImplMongoDB() {
        makeConnection();
    }

    @Override
    public void insertBook(Book book) {
        this.bookCount();
        DBCollection table = db.getCollection("book");
        BasicDBObject document = new BasicDBObject();
        document.put("book_id", count);
        document.put("book_name", book.getBookName());
        document.put("book_author", book.getAuthor());
        document.put("price", book.getPrice());
        document.put("version", book.getVersion());
        table.insert(document);
    }

    @Override
    public Book findBook(int id) {
        DBCollection table = db.getCollection("book");
        BasicDBObject searchQuery =  new BasicDBObject();
        searchQuery.put("book_id",id);
        DBCursor cursor =  table.find(searchQuery);
        Book currentBook = new Book();

        while (cursor.hasNext()) {
            DBObject document = cursor.next();
            currentBook.setBookId(id);
            currentBook.setBookName(document.get("book_name").toString());
            currentBook.setAuthor(document.get("book_author").toString());
            currentBook.setPrice(Integer.parseInt(document.get("price").toString()));
            currentBook.setVersion(document.get("version").toString());
        }
        return currentBook;
    }

    @Override
    public void updateBook(Book book) {
        DBCollection table = db.getCollection("book");

        BasicDBObject query = new BasicDBObject();
        query.put("book_id", book.getBookId());

        BasicDBObject newDocument = new BasicDBObject();
        newDocument.put("book_name", book.getBookName());
        newDocument.put("book_author", book.getAuthor());
        newDocument.put("price", book.getPrice());
        newDocument.put("version", book.getVersion());

        BasicDBObject updateObject = new BasicDBObject();
        updateObject.put("$set", newDocument);

        table.update(query, updateObject);

    }

    @Override
    public void deleteBook(Book book) {
        DBCollection table = db.getCollection("book");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("book_id", book.getBookId());

        table.remove(searchQuery);
    }

    @Override
    public void makeConnection() {
        try{
            mongoClient = new MongoClient( "localhost" , 27017 );
            db = mongoClient.getDB( "BookStore" );
        }catch(Exception e){
            System.err.println( e );
        }
    }

    @Override
    public List viewAllDetails() {
        List<Book> bookList = new ArrayList<Book>();
        DBCollection table = db.getCollection("book");
        DBCursor cursor = table.find();

        while (cursor.hasNext()) {
            Book listBook = new Book();
            DBObject document = cursor.next();
//            book.setBookId(Integer.parseInt(document.get("book_id").toString()));
            try {
                listBook.setBookId(Integer.parseInt(document.get("book_id").toString()));
                listBook.setBookName(document.get("book_name").toString());
                listBook.setAuthor(document.get("book_author").toString());
                listBook.setPrice(Integer.parseInt(document.get("price").toString()));
                listBook.setVersion(document.get("version").toString());
                bookList.add(listBook);
            }catch (Exception e){
                System.out.println("null");
            }
        }
        return bookList;
    }

    @Override
    public int bookCount() {
        DBCollection table = db.getCollection("book");
        DBCursor cursor = table.find();
        while (cursor.hasNext()) {
            cursor.next();
            count++;
        }
        return count;
    }

    @Override
    public Book findByName(String bookName) {
        Book book = new Book();

        DBCollection table = db.getCollection("book");
        BasicDBObject searchQuery =  new BasicDBObject();
        searchQuery.put("book_name",bookName);
        DBCursor cursor =  table.find(searchQuery);

        while (cursor.hasNext()) {
            DBObject document = cursor.next();
            if(document.get("book_id")!=null) {
                book.setBookId(Integer.parseInt(document.get("book_id").toString()));
            }
            book.setBookName(document.get("book_name").toString());
            book.setAuthor(document.get("book_author").toString());
            book.setPrice(Integer.parseInt(document.get("price").toString()));
            book.setVersion(document.get("version").toString());
        }
        return book;
    }
}
