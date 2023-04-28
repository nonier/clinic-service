import {Component, OnInit} from '@angular/core';
import {Consultation} from "../model/Consultation";
import {ClientService} from "../service/client/client.service";
import {User} from "../model/User";
import {AuthService} from "../service/auth/auth.service";
import {DoctorService} from "../service/doctor/doctor.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {

  consultations: Consultation[];
  user: User;

  constructor(private clientService: ClientService, private authService: AuthService,
              private doctorService: DoctorService, private router: Router) {
  }

  ngOnInit(): void {
    if (this.authService.getCurrentUser().roles.map(role => role.name).includes("ADMIN")) {
      this.doctorService.getDoctorConsultations()
        .subscribe(consultations => this.consultations = consultations);
    } else {
      this.clientService.getClientConsultations()
        .subscribe(consultations => this.consultations = consultations);
    }
    this.clientService.getClientInfo()
      .subscribe(user => this.user = user);
  }

  isAppointedTimeCome(date: Date): boolean {
    return new Date(date) < new Date();
  }
}
