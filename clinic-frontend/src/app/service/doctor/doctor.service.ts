import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Doctor} from "../../model/Doctor";
import {environment} from "../../../environments/environment";
import {Consultation} from "../../model/Consultation";

const DOCTOR_API_URL = environment.apiHost + '/doctors';

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  constructor(private http: HttpClient) {
  }

  getDoctors(): Observable<Doctor[]> {
    return this.http.get<Doctor[]>(DOCTOR_API_URL);
  }

  getDoctorsWithFilters(name: string, specializationIds: number[]): Observable<Doctor[]> {
    let params = new HttpParams()
      .set('name', name)
      .set('specializationIds', specializationIds.join(','));
    return this.http.get<Doctor[]>(DOCTOR_API_URL + '/filter', {params});
  }

  getDoctorConsultations(): Observable<Consultation[]> {
    return this.http.get<Consultation[]>(DOCTOR_API_URL + '/consultations');
  }
}
