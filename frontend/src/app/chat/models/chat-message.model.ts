import { MessageType } from './message-type.model';

export interface ChatMessage {
    type: MessageType;
    message: string;
}
