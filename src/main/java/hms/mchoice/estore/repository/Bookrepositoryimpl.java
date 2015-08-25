package hms.mchoice.estore.repository;

/**
 * Created by kapila on 8/24/15.
 */
public interface Bookrepositoryimpl {
    public boolean insertBook(String book_name,String book_author,int price,String version);
    public boolean viewDetail(int id);
    public boolean updateBook(int id, String book_name, String book_author, int price, String version);
    public boolean deleteBook(int id);
    public boolean makeConnection();
    public boolean viewAllDetails();
}
