import {Component, OnInit} from '@angular/core';
import {Consultation} from "../../model/Consultation";
import {User} from "../../model/User";
import {Time} from "@angular/common";
import {ConsultationService} from "../../service/consultation.service";
import {ProfileService} from "../../service/profile.service";

const HOUR = 60*60*1000;

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {

  consultations: Consultation[];
  user: User;
  newConsultationDate: Date;
  newConsultationTime: Time;
  maxDate: Date = new Date();
  birthDate: Date;
  surname: string;
  name: string;

  consultationDateTimeFilter = (date: Date | null): boolean => {
    return this.consultations.map(consultation => consultation.date)
      .map(consultationDate=> new Date(consultationDate))
      .filter(consultationDate => {
        console.log(consultationDate)
        return consultationDate.getTime() - HOUR <= date.getTime()
        && consultationDate.getTime() + HOUR >= date.getTime();
      }).length === 0;
  }

  constructor(private consultationService:ConsultationService,
              private profileService: ProfileService) {
    this.consultationService.fetchAllUserConsultations();
    this.consultationService.getUserConsultations()
      .subscribe(consultations => this.consultations = consultations);
  }

  ngOnInit(): void {
    this.profileService.getUserInfo()
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
    this.profileService.updateClientInfo(user);
    window.location.reload();
  }
}
