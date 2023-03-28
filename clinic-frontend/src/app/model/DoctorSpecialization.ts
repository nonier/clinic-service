import {Doctor} from "./Doctor";
import {Specialization} from "./Specialization";

export class DoctorSpecialization {
  doctor: Doctor;
  specialization: Specialization;

  constructor(doctor: Doctor, specialization: Specialization) {
    this.doctor = doctor;
    this.specialization = specialization;
  }
}
