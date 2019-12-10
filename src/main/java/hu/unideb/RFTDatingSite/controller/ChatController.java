package hu.unideb.RFTDatingSite.controller;

import hu.unideb.RFTDatingSite.Model.ChatList;
import hu.unideb.RFTDatingSite.Model.ChatMessage;
import hu.unideb.RFTDatingSite.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.events.Event;

import java.io.IOException;
import java.util.EventListener;
import java.util.List;
import java.util.Set;

@RestController
public class ChatController {

    @Autowired
    ChatMessageRepository chatMessageRepository;

    @Autowired
    private SimpMessagingTemplate template;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

   /* @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage,
                               SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("Sender", chatMessage.getSender());
        headerAccessor.getSessionAttributes().put("Reciever", chatMessage.getReceiver());

        String username = (String) headerAccessor.getSessionAttributes().get("Sender");
        chatMessage.setContent(String.valueOf(chatMessageRepository.getChatMessagesByUsers(username)));


        return chatMessage;
    }*/

   public void Recall(ChatMessage chatMessage,String username){
       chatMessage.setType(ChatMessage.MessageType.JOINED);
       chatMessage.setJr(username);
       System.out.println("from: "+chatMessage.getSender()+" to: "+chatMessage.getReceiver()+" "+chatMessage.getContent());
       this.template.convertAndSend("/topic/public",chatMessage);
   }




    @MessageMapping("/chat.addUser")
    @SendTo("/topic/public")
    public ChatMessage addUser(@Payload ChatMessage chatMessage, ChatList chatList,
                                     SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("Sender", chatMessage.getSender());
        headerAccessor.getSessionAttributes().put("Reciever", chatMessage.getReceiver());

        String username = (String) headerAccessor.getSessionAttributes().get("Sender");
        String username2 = (String) headerAccessor.getSessionAttributes().get("Reciever");
        Set<ChatMessage> x= chatMessageRepository.getChatMessagesByUsers(username,username2);
        System.out.println("addUser called | Recalled entities: ");
        //x.forEach(this::Recall);
        for(ChatMessage c:x){
            Recall(c,username);
        }
        return chatMessage;
    }


}