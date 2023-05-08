import {Component, OnInit} from '@angular/core';
import {Consultation} from "../../model/Consultation";
import {ClientService} from "../../service/client.service";
import {User} from "../../model/User";
import {AuthService} from "../../service/auth.service";
import {DoctorService} from "../../service/doctor.service";

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {

  consultations: Consultation[];
  user: User;
  maxDate: Date = new Date();
  birthDate: Date;
  surname: string;
  name: string;

  constructor(private clientService: ClientService, private authService: AuthService,
              private doctorService: DoctorService) {
  }

  ngOnInit(): void {
    if (this.authService.getCurrentUser().roles.map(role => role.name).includes("DOCTOR")) {
      this.doctorService.getDoctorConsultations()
        .subscribe(consultations => this.consultations = consultations);
    } else {
      this.clientService.getClientConsultations()
        .subscribe(consultations => this.consultations = consultations);
    }
    this.clientService.getClientInfo()
      .subscribe(user => {
          this.user = user;
          this.name = user.name;
          this.surname = user.surname;
          this.birthDate = user.birthDate;
        }
      );
  }

  isAppointedTimeCome(date: Date): boolean {
    return new Date(date) < new Date();
  }

  changeInfo() {
    let user = {
      name: this.name,
      surname: this.surname,
      birthDate: this.birthDate
    }
    this.clientService.changeClientInfo(user);
    window.location.reload();
  }
}
