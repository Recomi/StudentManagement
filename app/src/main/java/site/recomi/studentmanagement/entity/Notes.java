package site.recomi.studentmanagement.entity;

import org.litepal.crud.LitePalSupport;

public class Notes extends LitePalSupport {
    private String title;
    private String content;
    private String month;
    private String day;
    private int id;
    public Notes(String title, String content, String month, String day, int id){
        this.title = title;
        this.content = content;
        this.month = month;
        this.day = day;
        this.id = id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
