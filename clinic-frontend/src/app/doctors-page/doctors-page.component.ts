import { Component } from '@angular/core';
import {Doctor} from "../model/Doctor";
import {DoctorService} from "../service/doctor/doctor.service";
import {IMultiSelectOption, IMultiSelectTexts} from "ngx-bootstrap-multiselect";
import {SpecializationService} from "../service/specialization/specialization.service";
import {Specialization} from "../model/Specialization";

@Component({
  selector: 'app-doctors-page',
  templateUrl: './doctors-page.component.html',
  styleUrls: ['./doctors-page.component.css']
})
export class DoctorsPageComponent {

  doctors: Doctor[] = [];
  nameFilter: string = "";
  specializationIdsFilter: number[] = [];
  specializationOptions: IMultiSelectOption[] = [];
  specializationsTexts: IMultiSelectTexts = {
    defaultTitle: "Specializations"
  }

  constructor(private doctorService: DoctorService, private specializationService: SpecializationService) {
  }

  ngOnInit() {
    this.doctorService.doctorsSubject
      .subscribe((doctors: Doctor[]) => {
        this.doctors = doctors;
      })
    this.doctorService.getDoctors();

    this.specializationService.specializationSubject
      .subscribe((specializations: Specialization[]) => {
        this.specializationOptions = specializations;
      })
  }

  findByFilter(name: string) {
    this.nameFilter = name;
    this.doctorService.getDoctorsWithFilters(this.nameFilter, this.specializationIdsFilter);
  }

  onChangeSpecializations() {
    this.doctorService.getDoctorsWithFilters(this.nameFilter, this.specializationIdsFilter);
  }
}
