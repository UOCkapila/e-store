package hms.mchoice.estore.repository;

import hms.mchoice.estore.domain.Book;

import java.sql.*;

/**
 * Created by kapila on 8/24/15.
 */
public class BookRepositoryImpl implements BookRepository {

    private  Statement statement=null;
    private  Connection connection =null;
    private  ResultSet resultSet = null;

    public BookRepositoryImpl() {
        makeConnection();
    }

    @Override
    public void insertBook(Book book) {
        String book_name = book.getBookName();
        String book_author = book.getAuthor();
        int price = book.getPrice();
        String version = book.getVersion();

        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO bookStore.book(book_name,book_author,price,version) VALUES ('" +
                    book_name + "','" + book_author + "'," + price + ",'" + version + "')");
        }catch (Exception e) {
            System.out.println(e+" Cannot insert data");
        }
    }

    @Override
    public Book findBook(int id) {
        Book resultBook = new Book();

        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from bookStore.book where book_id="+id+"");
            while(resultSet.next()){
                resultBook.setBookName(resultSet.getString("book_name"));
                resultBook.setAuthor(resultSet.getString("book_author"));
                resultBook.setPrice(Integer.parseInt(resultSet.getString("price")));
                resultBook.setVersion(resultSet.getString("version"));
            }
        }catch (Exception e) {
            System.out.println("Cannot retrieve data");
        }
        return resultBook;
    }

    @Override
    public void updateBook(int id, String book_name, String book_author, int price, String version) {

        try {
            statement = connection.createStatement();
            String sql = "update bookStore.book set book_name='"+book_name+"',book_author='"+book_author
                    +"',price="+price+",version='"+version+"' where book_id ="+id+"";
            statement.executeUpdate(sql);
        }catch (Exception e) {
            System.out.println("Cannot update data");
        }
    }

    @Override
    public void deleteBook(int id) {

        try {
            statement = connection.createStatement();
            statement.executeUpdate("delete from bookStore.book where book_id = " + id + "");
        }catch (Exception e) {
            System.out.println(e+" Cannot delete data");
        }
    }

    public void makeConnection() {

        try {
            String driver = "com.mysql.jdbc.Driver";
            Class.forName(driver);
            String password = "admin";
            String user = "root";
            String url = "jdbc:mysql://localhost/";
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.out.println("Couldn't make the connection with the database");
        }
    }

    @Override
    public Book[] viewAllDetails() {
        Book bookList[] = new Book[100];

        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from bookStore.book");

            int i=0;

            while(resultSet.next()){
                Book book = new Book();
                book.setBookId(Integer.parseInt(resultSet.getString("book_id")));
                book.setBookName(resultSet.getString("book_name"));
                book.setAuthor(resultSet.getString("book_author"));
                book.setPrice(Integer.parseInt(resultSet.getString("price")));
                book.setVersion(resultSet.getString("version"));
                bookList[i]=book;
                i++;
            }

        }catch (Exception e) {
            System.out.println("Cannot retrieve data");
        }
        return bookList;
    }

    @Override
    public int bookCount() {
        int count = 0;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select count(*) from bookStore.book");
            while(resultSet.next()){
                count=resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

}
