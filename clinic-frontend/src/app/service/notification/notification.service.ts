import * as SockJS from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import {AuthService} from "../auth/auth.service";
import {environment} from "../../../environments/environment";
import {TokenService} from "../token/token.servise";
import {Message} from "../../model/Message";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  stompClient: any;
  topic: string

  constructor(private authService:AuthService, private tokenService: TokenService) {
  }

  subscribe() {
    console.log(this.authService.getCurrentUser().username)
    this.topic = `/notifications/${this.authService.getCurrentUser().username}`
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
    if (json['type'] == "USER_MESSAGE_ADDED") {
      let data = json['data'] as Message
      // this..updateUserMessages([data])
      console.log('message recieved' + message);
    }
    // else if (json['type'] == "USER_CONVERSATION_UPDATED" || json['type'] == "USER_CONVERSATION_ADDED") {
    //   let data = json['data'] as FriendProfile
    //   this.dataService.updateFriends([data])
    else {
      console.log(json)
    }
  }

}
