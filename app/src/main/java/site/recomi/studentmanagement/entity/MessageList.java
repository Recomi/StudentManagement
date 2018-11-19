package site.recomi.studentmanagement.entity;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class MessageList extends LitePalSupport{
    private String user;
    private int headicomRes;
    private Date dateTime;
    private String lastMessage;
}
