import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Message} from "../model/Message";
import {DataService} from "./data.service";
import {UpdateMessage} from "../model/UpdateMessage";

const MESSAGE_API_URL = environment.apiHost + '/messages';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private http: HttpClient, private dataService: DataService) {
  }

  fetchAllMessages() {
    this.http.get(MESSAGE_API_URL)
      .subscribe((messages: Message[]) => this.dataService.updateMessages(messages));
  }

  getMessages(dialogId: number): Observable<Message[]> {
    return this.dataService.getMessages(dialogId);
  }

  sendMessage(message: UpdateMessage) {
    this.http.post(MESSAGE_API_URL, message)
      .subscribe((message: Message) => console.log(message));
  }
}
