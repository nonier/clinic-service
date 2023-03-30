import {Component, OnInit} from '@angular/core';
import {DoctorService} from "../../service/doctor.service";
import {Doctor} from "../../model/Doctor";

@Component({
  selector: 'app-doctors',
  templateUrl: './doctors.component.html',
  styleUrls: ['./doctors.component.css']
})
export class DoctorsComponent implements OnInit {

  doctors: Doctor[] = [];

  constructor(private doctorService: DoctorService) {
  }

  ngOnInit() {
    this.doctorService.doctorsSubject.subscribe(doctors => this.doctors = doctors)
  }
}
