import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Doctor} from "../model/Doctor";
import {environment} from "../../environments/environment";
import {Consultation} from "../model/Consultation";
import {DataService} from "./data.service";

const DOCTOR_API_URL = environment.apiHost + '/doctors';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  constructor(private http: HttpClient, private dataService: DataService) {
  }

  getDoctors(): Observable<Doctor[]> {
    return this.dataService.getDoctors();
  }

  fetchAllDoctors() {
    this.http.get<Doctor[]>(DOCTOR_API_URL)
      .subscribe(doctors=> this.dataService.updateDoctors(doctors));
  }

  getDoctorsWithFilters(name: string, specializationIds: number[]) {
    let params = new HttpParams()
      .set('name', name)
      .set('specializationIds', specializationIds.join(','));
    this.http.get<Doctor[]>(DOCTOR_API_URL + '/filter', {params})
      .subscribe(doctors=> this.dataService.updateDoctors(doctors));
  }
}
