package site.recomi.studentmanagement.entity;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class Message extends LitePalSupport {
    private String sender;
    private int headiconRes;
    private String content;
    private String receivingTime;

    public Message(String sender, int headiconRes, String content, String receivingTime) {
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

    public String getReceivingTime() {
        return receivingTime;
    }

    public void setReceivingTime(String receivingTime) {
        this.receivingTime = receivingTime;
    }
}
