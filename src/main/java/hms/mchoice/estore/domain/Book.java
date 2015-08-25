package hms.mchoice.estore.domain;

/**
 * Created by kapila on 8/24/15.
 */
public class Book {
    private String book_name;
    private String author;
    private int price;
    private String version;

    public void setBook_name(String book_name1){
        book_name = book_name1;
    }
    public void  setAuthor(String author1){
        author = author1;
    }
    public void setPrice(int price1){
        price = price1;
    }
    public void setVersion(String version1){
        version =version1;
    }
    public String getBook_name(){
        return book_name;
    }
    public  String getAuthor(){
        return author;
    }
    public int getPrice(){
        return price;
    }
    public String getVersion(){
        return version;
    }

}
