package site.recomi.studentmanagement.entity;

import java.util.Date;

public class ChattingMessage {
    public final static int MINE_TEXT = 0;
    public final static int OTHER_TEXT = 1;
    public final static int MINE_PIC = 2;
    public final static int OTHER_PIC = 3;
    public final static int MINE_VOICE = 3;
    public final static int OTHER_VOICE = 3;

    String userName;
    String content;
    String headIconFileName;
    Date time;
    int type;

    public ChattingMessage(String userName, String content, String headIconFileName, Date time, int type) {
        this.userName = userName;
        this.content = content;
        this.headIconFileName = headIconFileName;
        this.time = time;
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeadIconFileName() {
        return headIconFileName;
    }

    public void setHeadIconFileName(String headIconFileName) {
        this.headIconFileName = headIconFileName;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
