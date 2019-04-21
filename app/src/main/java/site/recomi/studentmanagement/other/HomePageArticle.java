package site.recomi.studentmanagement.other;

/*
* 主页通知数据实体类
* */
public class HomePageArticle {
    private int id;
    private String title;
    private String author;
    private String date;
    private String synopsis;
    private String content;
    private String imgSrcSet;
    private int likeSum;
    private int shareSum;
    private int commentSum;
    private String commentSet;

    public HomePageArticle(int id, String title, String author, String date, String synopsis, String content, String imgSrcSet, int likeSum, int shareSum, int commentSum, String commentSet) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.date = date;
        this.synopsis = synopsis;
        this.content = content;
        this.imgSrcSet = imgSrcSet;
        this.likeSum = likeSum;
        this.shareSum = shareSum;
        this.commentSum = commentSum;
        this.commentSet = commentSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgSrcSet() {
        return imgSrcSet;
    }

    public void setImgSrcSet(String imgSrcSet) {
        this.imgSrcSet = imgSrcSet;
    }

    public int getLikeSum() {
        return likeSum;
    }

    public void setLikeSum(int likeSum) {
        this.likeSum = likeSum;
    }

    public int getShareSum() {
        return shareSum;
    }

    public void setShareSum(int shareSum) {
        this.shareSum = shareSum;
    }

    public int getCommentSum() {
        return commentSum;
    }

    public void setCommentSum(int commentSum) {
        this.commentSum = commentSum;
    }

    public String getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(String commentSet) {
        this.commentSet = commentSet;
    }
}
