package site.recomi.studentmanagement.other;

import android.graphics.drawable.Drawable;

/*
* Book活动中公告碎片中recyview列表的实体类
* */
public class BookNoticeEntitiy {
    private String title;
    private String content;
    private String date;
    private int srcImage;

    public BookNoticeEntitiy(String title, String content, String date, int srcImage) {
        this.title = title;
        this.content = content;
        this.date = date;
        this.srcImage = srcImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getSrcImage() {
        return srcImage;
    }

    public void setSrcImage(int srcImage) {
        this.srcImage = srcImage;
    }
}
