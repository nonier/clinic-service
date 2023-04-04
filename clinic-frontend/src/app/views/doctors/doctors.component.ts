import {Component, OnInit} from '@angular/core';
import {DoctorService} from "../../service/doctor.service";
import {Doctor} from "../../model/Doctor";
import {SpecializationService} from "../../service/specialization.service";
import {Specialization} from "../../model/Specialization";
import {IMultiSelectOption, IMultiSelectTexts} from 'ngx-bootstrap-multiselect';

@Component({
  selector: 'app-doctors',
  templateUrl: './doctors.component.html',
  styleUrls: ['./doctors.component.css']
})
export class DoctorsComponent implements OnInit {

  doctors: Doctor[] = [];
  nameFilter: string = "";
  specializationIdsFilter: number[] = [];
  specializationOptions: IMultiSelectOption[] = [];
  specializationsTexts: IMultiSelectTexts = {
    defaultTitle: "Специализации"
  }

  constructor(private doctorService: DoctorService, private specializationService: SpecializationService) {
  }

  ngOnInit() {
    this.doctorService.getDoctors()
      .subscribe((doctors: Doctor[]) => {
        this.doctors = doctors;
      })
    this.specializationService.getSpecializations()
      .subscribe((specializations: Specialization[]) => {
        this.specializationOptions = specializations;
      })
  }

  findByFilter(name: string) {
    this.nameFilter = name;
    this.doctorService.getDoctorsWithFilters(this.nameFilter, this.specializationIdsFilter)
      .subscribe((doctors: Doctor[]) => {
        this.doctors = doctors;
      })
  }

  onChangeSpecializations() {
    this.doctorService.getDoctorsWithFilters(this.nameFilter, this.specializationIdsFilter)
      .subscribe((doctors: Doctor[]) => {
        this.doctors = doctors;
      })
  }
}
