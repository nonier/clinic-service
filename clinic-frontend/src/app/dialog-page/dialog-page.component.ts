import {Component, OnInit} from '@angular/core';
import {NotificationService} from "../service/notification/notification.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-dialog-page',
  templateUrl: './dialog-page.component.html',
  styleUrls: ['./dialog-page.component.css']
})
export class DialogPageComponent implements OnInit{


  constructor(private notificationService: NotificationService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    console.log(this.route.snapshot.paramMap.get('id'));
    this.notificationService.subscribe();
  }
}
