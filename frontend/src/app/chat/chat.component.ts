import { Component } from '@angular/core';
import { ChatService } from '../../services/chat.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent {
  chatService: ChatService;
  public chatMessages: string[] = [];

  constructor(injectedChatService: ChatService) {
    this.chatService = injectedChatService;
    this.chatService.chatMessages.subscribe(chatMessage => {
      this.chatMessages.push(chatMessage);
    });
  }

  onSend(input: string) {
    this.chatService.sendMessage(input);
  }
}
