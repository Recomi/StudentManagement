package site.recomi.studentmanagement.entity;

import org.litepal.crud.LitePalSupport;

public class Diary extends LitePalSupport {
    private String day;
    private String monthAndWeek;
    private String time;
    private String title;
    private String content;
    private String weather;
    private String address;
    private int imgRes;

//    public Diary(){}

    public Diary(String day, String monthAndWeek, String time, String title, String content, String weather, String address, int imgRes) {
        this.day = day;
        this.monthAndWeek = monthAndWeek;
        this.time = time;
        this.title = title;
        this.content = content;
        this.weather = weather;
        this.address = address;
        this.imgRes = imgRes;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonthAndWeek() {
        return monthAndWeek;
    }

    public void setMonthAndWeek(String monthAndWeek) {
        this.monthAndWeek = monthAndWeek;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getImgRes() {
        return imgRes;
    }

    public void setImgRes(int imgRes) {
        this.imgRes = imgRes;
    }
}

