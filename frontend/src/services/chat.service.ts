import { Injectable } from '@angular/core';
import { ROOT_URL } from './service.constants';
import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';
import { AuthService } from './auth.service';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  readonly CHAT_URL = `${ROOT_URL}/app`;
  readonly JOIN_URL = '/join';
  readonly socket = new SockJS(this.CHAT_URL);
  readonly stompClient = Stomp.over(this.socket);

  public chatMessages: Subject<string> = new Subject<string>();

  constructor(authService: AuthService) {
    authService.loggedInUserId$.subscribe(user => {
      console.log('Chat service constructed, opening chat with user: ', user);
      this.stompClient.connect({}, () => {
        console.log('Connected to websocket');

        this.stompClient.subscribe('/chat', (message: any) => {
          console.log('Your partner has joined ', message);
          this.chatMessages.next('Your partner has joined ' + user);
        });

      });

      this.joinChat(user);
    });
  }

  joinChat(username: string): void {
    this.stompClient.send(this.JOIN_URL, username + ' has joined!', {});
  }

  sendMessage(message: string): void {
    this.stompClient.send('/app/join', 'I have joined! ' + message);
  }
}
