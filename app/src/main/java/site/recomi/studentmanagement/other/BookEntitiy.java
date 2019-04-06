package site.recomi.studentmanagement.other;

public class BookEntitiy {
    private int bookID;
    private String name;
    private String author;
    private String publishingHouse;

    public BookEntitiy(int bookID, String name, String author, String publishingHouse) {
        this.bookID = bookID;
        this.name = name;
        this.author = author;
        this.publishingHouse = publishingHouse;
    }

    public BookEntitiy(){

    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }
}
