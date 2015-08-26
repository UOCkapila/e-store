package hms.mchoice.estore.repository;

import hms.mchoice.estore.domain.Book;

import java.sql.*;

/**
 * Created by kapila on 8/24/15.
 */
public class Bookrepository implements Bookrepositoryimpl{

    private final String driver ="com.mysql.jdbc.Driver";
    private final String url ="jdbc:mysql://localhost/";
    private final String user="root";
    private final String pass="admin";
    private  Statement statement=null;
    private  Connection connection =null;
    private  ResultSet resultSet = null;

    Book book = new Book();

    public Bookrepository() {
        makeConnection();

    }

    public boolean makeBook(String book_name, String book_author, int price, String version){
        book.setBook_name(book_name);
        book.setAuthor(book_author);
        book.setPrice(price);
        book.setVersion(version);
        return true;
    }

    @Override
    public boolean insertBook(Book book) {

        String book_name = book.getBook_name();
        String book_author = book.getAuthor();
        int price = book.getPrice();
        String version = book.getVersion();

        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO bookStore.book(book_name,book_author,price,version) VALUES ('" + book_name + "','" + book_author + "'," + price + ",'" + version + "')");
            System.out.println("Insert data");
            return true;
        }catch (Exception e) {
            System.out.println(e+" Cannot insert data");
            return false;
        }
    }

    @Override
    public boolean viewDetail(int id) {
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from bookStore.book where book_id="+id+"");
            while(resultSet.next()){
                String bookName = resultSet.getString("book_name");
                String bookAuthor = resultSet.getString("book_author");
                String price = resultSet.getString("price");
                String version = resultSet.getString("version");

                System.out.println(bookName +" "+bookAuthor+" "+price+" "+version);
            }
            return true;
        }catch (Exception e) {
            System.out.println("Cannot retrieve data");
            return false;
        }
    }

    @Override
    public boolean updateBook(int id, String book_name, String book_author, int price, String version) {
        try {
            statement = connection.createStatement();
            String sql = "update bookStore.book set book_name='"+book_name+"',book_author='"+book_author+"',price="+price+",version='"+version+"' where book_id ="+id+"";
            statement.executeUpdate(sql);
            System.out.println("Update data");
            return true;
        }catch (Exception e) {
            System.out.println("Cannot update data");
            return false;
        }
    }

    @Override
    public boolean deleteBook(int id) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("delete from bookStore.book where book_id = " + id + "");
            System.out.println("delete data");
            return true;
        }catch (Exception e) {
            System.out.println(e+" Cannot delete data");
            return false;
        }
    }

    public boolean makeConnection() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, pass);
            return true;
        } catch (Exception e) {
            System.out.println("Couldn't make the connection with the database");
            return true;
        }
    }

    @Override
    public boolean viewAllDetails() {
        try{
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from bookStore.book");
            while(resultSet.next()){
                String bookName = resultSet.getString("book_name");
                String bookAuthor = resultSet.getString("book_author");
                String price = resultSet.getString("price");
                String version = resultSet.getString("version");
                System.out.println(bookName +" "+bookAuthor+" "+price+" "+version);
            }
            return true;
        }catch (Exception e) {
            System.out.println("Cannot retrieve data");
            return false;
        }
    }
}
