import {Component, OnInit} from '@angular/core';
import {Doctor} from "../../model/Doctor";
import {DoctorService} from "../../service/doctor.service";
import {IMultiSelectOption, IMultiSelectTexts} from "ngx-bootstrap-multiselect";
import {SpecializationService} from "../../service/specialization.service";
import {ClientService} from "../../service/client.service";

@Component({
  selector: 'app-doctors-page',
  templateUrl: './doctors-page.component.html',
  styleUrls: ['./doctors-page.component.css']
})
export class DoctorsPageComponent implements OnInit {

  doctors: Doctor[] = [];
  nameFilter: string = "";
  specializationIdsFilter: number[] = [];
  specializationOptions: IMultiSelectOption[] = [];
  specializationsTexts: IMultiSelectTexts = {
    defaultTitle: "Specializations"
  }

  constructor(private doctorService: DoctorService, private specializationService: SpecializationService,
              private clientService: ClientService) {
    this.getDoctors();
    this.getSpecializations();
  }

  ngOnInit() {
  }

  getDoctors() {
    this.doctorService.fetchAllDoctors();
    this.doctorService.getDoctors()
      .subscribe(doctors => this.doctors = doctors);
  }

  getSpecializations() {
    this.specializationService.fetchAllSpecializations();
    this.specializationService.getSpecializations()
      .subscribe(specializations => this.specializationOptions = specializations);
  }

  findByFilter(name: string) {
    this.nameFilter = name;
    this.doctorService.getDoctorsWithFilters(this.nameFilter, this.specializationIdsFilter);
  }

  onChangeSpecializations() {
    this.doctorService.getDoctorsWithFilters(this.nameFilter, this.specializationIdsFilter);
  }

  chooseConsultation(consultationId: number) {
    this.clientService.chooseConsultation(consultationId);
  }
}
