import { Component, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ChatService } from '../service/chat.service';
import { ChatMessage } from '../models/chat-message.model';
import { MessageType } from '../models/message-type.model';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnDestroy {
  chatService: ChatService;
  public chatMessages: string[] = [];
  public coolChatMessages: ChatMessage[] = [];

  constructor(injectedChatService: ChatService,
              route: ActivatedRoute) {
    this.chatService = injectedChatService;

    const partnerUserId = route.snapshot.paramMap.get('partnerUserId');
    this.chatService.joinChat(partnerUserId);

    this.chatService.chatMessages.subscribe((chatMessage: string) => {
      this.chatMessages.push(chatMessage);
    });

    this.chatService.coolChatMessages.subscribe((chatMessage: ChatMessage) => {
      this.coolChatMessages.push(chatMessage);
    });
  }

  ngOnDestroy(): void {
    this.chatService.clearChat();
  }

  onSend(input: string, $event: Event) {
    this.chatService.sendMessage(input);
    $event.preventDefault();
  }

  public getCssClassByType(type: MessageType): string {
    switch (type) {
      case MessageType.GENERAL: return 'general-message';
      case MessageType.MINE: return 'own-message';
      case MessageType.PARTNER: return 'partner-message';
    }
  }
}
