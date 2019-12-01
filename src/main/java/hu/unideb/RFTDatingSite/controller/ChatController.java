package hu.unideb.RFTDatingSite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectEvent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ChatController {
    Map<String, String> connectedSessions = new ConcurrentHashMap<>();

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private SimpUserRegistry userRegistry;

    @MessageMapping("/join")
    @SendTo("/chat")
    public void send(String message) throws Exception {
        System.out.println("Message arrived on /join: " + message);
        System.out.println("joined users are " + userRegistry.getUsers().toString());
        this.template.convertAndSend("/chat", "Hi I joined the chat");
        this.template.convertAndSend("/topic", "Hi I joined the chat");
    }

    @EventListener
    public void onSessionConnected(SessionConnectEvent event) {
        System.out.println("CONNECTED - SOCKET EVENT - OnSessionConnected");
        org.springframework.messaging.Message msg = event.getMessage();
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(msg);
        String sessionId = accessor.getSessionId();

        MessageHeaders headers = event.getMessage().getHeaders();
    }
}
