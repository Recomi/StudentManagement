package site.recomi.studentmanagement.entity;

public class UserSharingPost {
    String name;
    String time;
    String content;
    String headIconUrl;
    int like;
    int collect;
    int share;

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getCollect() {
        return collect;
    }

    public void setCollect(int collect) {
        this.collect = collect;
    }

    public int getShare() {
        return share;
    }

    public void setShare(int share) {
        this.share = share;
    }

    public UserSharingPost(String name, String time, String content, String headIconUrl, int like, int collect, int share) {
        this.name = name;
        this.time = time;
        this.content = content;
        this.headIconUrl = headIconUrl;
        this.like = like;
        this.collect = collect;
        this.share = share;
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
