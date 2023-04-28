import * as SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import {AuthService} from "../auth/auth.service";
import {environment} from "../../../environments/environment";
import {TokenService} from "../token/token.servise";
import {Message} from "../../model/Message";
import {Injectable} from "@angular/core";
import {DataService} from "../data/data.service";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  stompClient: any;
  topic: string

  constructor(private authService:AuthService, private tokenService: TokenService,
              private dataService: DataService) {
  }

  subscribe() {
    console.log(this.authService.getCurrentUser().username)
    this.topic = `/notifications/${this.authService.getCurrentUser().id}`
    console.log(this.topic);
    let ws = new SockJS(`${environment.apiHost}/ws`);
    this.stompClient = Stomp.over(ws);
    this.stompClient.debug = () => {};
    const _this = this;
    _this.stompClient.connect({ "Authorization": "Bearer " + this.tokenService.getAccessToken() },
      function (frame) {
        _this.stompClient.subscribe(_this.topic, function (sdkEvent) {
          _this.onMessageReceived(sdkEvent);
        });
      }, function (error) { setTimeout(() => _this.subscribe(), 5000); });
  }

  onMessageReceived(message) {
    let json = JSON.parse(message.body)
    if (json['type'] == "NEW_MESSAGE") {
      let data = json['body'] as Message;
      this.dataService.updateMessages([data]);
      console.log('message recieved' + message);
    }
    else {
      console.log(json);
    }
  }

}
