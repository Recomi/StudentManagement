package site.recomi.studentmanagement.entity;

public class UserSharingPost {
    String name;
    String time;
    String content;
    String headIconUrl;

    public UserSharingPost(String name, String time, String content, String headIconUrl) {
        this.name = name;
        this.time = time;
        this.content = content;
        this.headIconUrl = headIconUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeadIconUrl() {
        return headIconUrl;
    }

    public void setHeadIconUrl(String headIconUrl) {
        this.headIconUrl = headIconUrl;
    }
}
