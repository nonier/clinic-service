import {Component, OnInit} from '@angular/core';
import {NotificationService} from "../../service/notification.service";
import {ActivatedRoute} from "@angular/router";
import {Message} from "../../model/Message";
import {MessageService} from "../../service/message.service";
import {AuthService} from "../../service/auth.service";
import {UpdateMessage} from "../../model/UpdateMessage";

@Component({
  selector: 'app-dialog-page',
  templateUrl: './dialog-page.component.html',
  styleUrls: ['./dialog-page.component.css']
})
export class DialogPageComponent implements OnInit {

  dialogId: number;
  messages: Message[];

  constructor(private notificationService: NotificationService, private route: ActivatedRoute,
              private messageService: MessageService, private authService: AuthService) {
    this.getDialog();
    this.notificationService.subscribe();
  }

  ngOnInit(): void {
  }

  getDialog() {
    this.messageService.fetchAllMessages();
    this.dialogId = parseInt(this.route.snapshot.paramMap.get('id'));
    this.messages = [];
    this.messageService.getMessages(this.dialogId)
      .subscribe((messages) => this.messages = messages);
  }

  sendMessage(text: string) {
    let user = this.authService.getCurrentUser();
    let message = new UpdateMessage(text, user.id, this.dialogId);
    this.messageService.sendMessage(message);
  }
}
