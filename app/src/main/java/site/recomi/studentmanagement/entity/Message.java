package site.recomi.studentmanagement.entity;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class Message extends LitePalSupport {
    private String sender;
    private int headiconRes;
    private String content;
    private Date receivingTime;

    public Message(String sender, int headiconRes, String content, Date receivingTime) {
        this.sender = sender;
        this.headiconRes = headiconRes;
        this.content = content;
        this.receivingTime = receivingTime;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getHeadiconRes() {
        return headiconRes;
    }

    public void setHeadiconRes(int headiconRes) {
        this.headiconRes = headiconRes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(Date receivingTime) {
        this.receivingTime = receivingTime;
    }
}
