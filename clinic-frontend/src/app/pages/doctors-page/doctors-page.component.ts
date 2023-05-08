import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {Doctor} from "../../model/Doctor";
import {DoctorService} from "../../service/doctor.service";
import {SpecializationService} from "../../service/specialization.service";
import {ClientService} from "../../service/client.service";
import {Specialization} from "../../model/Specialization";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-doctors-page',
  templateUrl: './doctors-page.component.html',
  styleUrls: ['./doctors-page.component.css']
})
export class DoctorsPageComponent implements OnInit {

  doctors: Doctor[] = [];
  nameFilter: string = "";
  allSpecializations: Specialization[] = [];
  specializationsCtrl = new FormControl<Specialization[]>([]);

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
      .subscribe(specializations => this.allSpecializations = specializations);
  }

  findByFilter(name: string) {
    this.nameFilter = name;
    this.doctorService.getDoctorsWithFilters(this.nameFilter, this.specializationsCtrl.value
      .map(specialization => specialization.id));
  }

  chooseConsultation(consultationId: number) {
    this.clientService.chooseConsultation(consultationId);
  }

  protected readonly Specialization = Specialization;
}
