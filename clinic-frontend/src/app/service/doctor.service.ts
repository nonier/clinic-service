import { Injectable } from '@angular/core';
import {Doctor} from "../model/Doctor";
import {TestData} from "../data/TestData";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  //Переделать на http запрос
  doctorsSubject = new BehaviorSubject<Doctor[]>(TestData.doctors);

  constructor() { }

  //Поменять на получение с фильтром
  getDoctors() {
    //Переделать на http запрос
    this.doctorsSubject.next(TestData.doctors);
  }
}
