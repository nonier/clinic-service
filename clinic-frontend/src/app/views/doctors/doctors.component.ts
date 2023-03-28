import {Component} from '@angular/core';
import {DoctorService} from "../../service/doctor.service";
import {Doctor} from "../../model/Doctor";

@Component({
  selector: 'app-doctors',
  templateUrl: './doctors.component.html',
  styleUrls: ['./doctors.component.css']
})
export class DoctorsComponent {

  doctors: Doctor[];

  constructor(private doctorService: DoctorService) {
    this.doctors = this.doctorService.getDoctors();
    console.log(this.doctors);
  }
}
