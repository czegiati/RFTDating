package hu.unideb.RFTDatingSite.controller;

import hu.unideb.RFTDatingSite.Model.UserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ChatController {
    Map<String, UserMessage[]> connectedSessions = new ConcurrentHashMap<>();

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/chat/")
    @SendTo("/topic/messages")
    public String privateChatMessage(String message) throws Exception {
        //String time = new SimpleDateFormat("HH:mm").format(new Date());
        return new StringBuilder().append("This is a message for all subscribers").append(message).toString();
    }


    @MessageMapping("/general/{userId}/{partnerUserId}")
    public void generalMessage(UserMessage message, @DestinationVariable String userId, @DestinationVariable String partnerUserId) throws Exception {
        System.out.println("General message arrived on /chat: " + message.toString() + " from " + userId + " to " + partnerUserId);
        System.out.println("Forwarding message to " + getGeneralMessagePath(userId, partnerUserId));

        template.convertAndSend(getGeneralMessagePath(userId, partnerUserId), message.getMessage());
    }

    @MessageMapping("/general/*")
    public void generalMessage2(UserMessage message) throws Exception {
        System.out.println("General message arrived on /chat: " + message.toString()); // + " from " + userId + " to " + partnerUserId);

        template.convertAndSend("topic/chat", message.getMessage());
    }

    @MessageMapping("/chat/{userId}/{partnerUserId}")
    public void chatMessage(UserMessage message, @DestinationVariable String userId, @DestinationVariable String partnerUserId) throws Exception {
        //String time = new SimpleDateFormat("HH:mm").format(new Date());
        System.out.println("Message arrived on /chat: " + message.toString());
        System.out.println("Chat message arrived on /chat: " + message.toString() + " from " + userId + " to " + partnerUserId);

        System.out.println("Forwarding message to " + getGeneralMessagePath(userId, partnerUserId));



        template.convertAndSend(getChatMessagePath(userId, partnerUserId), message.getMessage());
    }

    private String getGeneralMessagePath(String userId, String partnerUserId) {
        return "/topic/general/" + partnerUserId + '/' + userId;
    }

    private String getChatMessagePath(String userId, String partnerUserId) {
        return "/topic/chat/" + partnerUserId + '/' + userId;
    }

    @EventListener
    public void onSessionConnected(SessionConnectEvent event) {
        // todo use this event to create message tracking via concurrent map of messages
    }

    @EventListener
    public void onSessionDisconnected(SessionDisconnectEvent event) {
        // todo store saved messages in db when both users have disconnected
    }

}
