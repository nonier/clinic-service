import {Injectable} from '@angular/core';
import {BehaviorSubject, map, Observable} from "rxjs";
import {Message} from "../../model/Message";


@Injectable({
  providedIn: 'root'
})
export class DataService {

  messages: BehaviorSubject<Map<number, Message>> = new BehaviorSubject(new Map());

  updateMessages(messages: Message[]) {
    let oldMessages = this.messages.value;
    messages.map(message => oldMessages.set(message.id, message));
    this.messages.next(oldMessages);
  }

  getMessages(dialogId: number): Observable<Message[]> {
    return this.messages.pipe(
      map(message => {
        let messages: Message[] = []
        message.forEach((v, k) => { if (v.dialogId == dialogId) messages.push(v) })
        messages.sort((a, b) => a.creationDate > b.creationDate ? 1 : 0);
        return messages;
      })
    )
  }
}
