import {Component, OnInit} from '@angular/core';
import {ConsultationService} from "../service/consultation/consultation.service";
import {Consultation} from "../model/Consultation";

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrls: ['./profile-page.component.css']
})
export class ProfilePageComponent implements OnInit {

  consultations: Consultation[];

  constructor(private consultationService: ConsultationService) {
  }

  ngOnInit(): void {
    this.consultationService.getClientConsultations()
      .subscribe(consultations => this.consultations = consultations);
  }



}
