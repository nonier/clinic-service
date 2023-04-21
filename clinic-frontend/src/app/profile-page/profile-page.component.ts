import {Component, OnInit} from '@angular/core';
import {Consultation} from "../model/Consultation";
import {ClientService} from "../service/client/client.service";
import {User} from "../model/User";

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {

  consultations: Consultation[];
  user: User;

  constructor(private clientService: ClientService) {
  }

  ngOnInit(): void {
    this.clientService.getClientConsultations()
      .subscribe(consultations => this.consultations = consultations);
    this.clientService.getClientInfo()
      .subscribe(user => this.user = user);
  }

}
