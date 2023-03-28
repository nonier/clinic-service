import { Injectable } from '@angular/core';
import {Doctor} from "../model/Doctor";
import {TestData} from "../data/TestData";

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  constructor() { }

  getDoctors(): Doctor[] {
    return TestData.doctors;
  }
}
