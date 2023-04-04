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
  name: string="";

  constructor(public doctorService: DoctorService) {
  }

  ngOnInit() {
    this.doctorService.getDoctors()
      .subscribe((doctors: Doctor[]) => {
        this.doctors=doctors;
      })
  }

  findByFilter(name: string) {
    this.doctorService.getDoctorsWithFilters(name)
      .subscribe((doctors: Doctor[]) => {
        this.doctors=doctors;
      })
  }
}
