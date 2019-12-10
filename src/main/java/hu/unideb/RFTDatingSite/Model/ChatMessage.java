package hu.unideb.RFTDatingSite.Model;

import hu.unideb.RFTDatingSite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.io.IOException;
import java.util.Date;

@Entity
@Table(name ="ChatMessage")
public class ChatMessage {

    @Autowired
    @Transient
    UserService userService;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Transient
    private MessageType type;

    @Column(name = "message")
    private String content;
    private String sender;
    private String receiver;
    private String jr;


    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE,
        JOINED
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getJr() {
        return jr;
    }

    public void setJr(String jr) {
        this.jr = jr;
    }
}
