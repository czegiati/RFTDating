import { Injectable } from '@angular/core';
import { ROOT_URL } from '../../../services/service.constants';
import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';
import { AuthService } from '../../../services/auth.service';
import { ReplaySubject, Subject } from 'rxjs';
import { User } from '../../../models/user';
import { UserMessage } from '../models/user-message.model';
import { MessageType } from '../models/message-type.model';
import { IS_LOGGING_ENABLED } from '../../../app.constants';
import { Chat } from '../models/chat.model';
import { ChatMessage } from '../models/chat-message.model';
import { Message } from 'stompjs';

@Injectable({
    providedIn: 'root'
})
export class ChatService {
    readonly CHAT_URL = `${ROOT_URL}/app`;
    readonly GENERAL_MESSAGES_URL = '/app/general';
    readonly CHAT_MESSAGES_URL = '/app/chat';

    readonly socket = new SockJS(this.CHAT_URL);
    readonly stompClient = Stomp.over(this.socket);

    public chatMessages: Subject<string> = new Subject<string>();
    public coolChatMessages: Subject<ChatMessage> = new Subject<ChatMessage>();
    private currentUser: User = null;
    private connectedChat: Chat = null;

    private connectedChat$: Subject<Chat> = new ReplaySubject<Chat>(1);

    constructor(authService: AuthService) {
        authService.currentUser$.subscribe((user: User) => {
            this.currentUser = user;
        });
    }

    clearChat(): void {
        this.connectedChat = null;
        this.stompClient.disconnect();
    }

    joinChat(partnerUserId: string): void {
        this.connectToChat(this.currentUser.id, partnerUserId);
        this.connectedChat$.subscribe((chat: Chat) => {
            this.sendGeneralMessage(chat, `${this.currentUser.username} has joined the chat.`);
        });
    }

    connectToChat(userId: string, partnerUserId: string): void {
        this.stompClient.connect({}, () => {
            console.assert(IS_LOGGING_ENABLED, 'Connected to chat with partner!');
            this.connectedChat$.next({userId, partnerUserId});
            this.connectedChat = {userId, partnerUserId};

            this.stompClient.send(
                `app${this.GENERAL_MESSAGES_URL}`,
                    JSON.stringify(this.getChatMessage(this.currentUser, 'valami'))
            );


            this.stompClient.subscribe('/topic/messages', (message: any) => {
                console.log('On topic/chat ', message);
                this.chatMessages.next(message);
            });

            this.stompClient.subscribe(`/topic/chat/${userId}/${partnerUserId}`, (message: Message) => {
                console.log('On topic/chat ', message);
                this.coolChatMessages.next({type: MessageType.PARTNER, message: message.body});
            });

            this.stompClient.subscribe(`/topic/general/${userId}/${partnerUserId}`, (message: any) => {
                console.log('On topic/chat ', message);
                this.coolChatMessages.next({type: MessageType.GENERAL, message: message.body});

            });
        });
    }

    sendMessage(message: string): void {
        if (this.connectedChat) {
            this.sendChatMessage(this.connectedChat, message);
            this.coolChatMessages.next({type: MessageType.MINE, message: message});
        }
    }
    private sendGeneralMessage(chat: Chat, message: string): void {
        this.stompClient.send(
            `${this.GENERAL_MESSAGES_URL}/${chat.partnerUserId}/${chat.userId}`,
            JSON.stringify(this.getChatMessage(this.currentUser, message))
        );
    }

    private sendChatMessage(chat: Chat, message: string): void {
        this.stompClient.send(
            `${this.CHAT_MESSAGES_URL}/${chat.userId}/${chat.partnerUserId}`,
            JSON.stringify(this.getChatMessage(this.currentUser, message))
        );
    }

    private getChatMessage(user: User, message: string): UserMessage {
        return {
            userId: user.id,
            username: user.username,
            message
        };
    }
}
