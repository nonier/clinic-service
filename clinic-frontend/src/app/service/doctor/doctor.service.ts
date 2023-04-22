import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Doctor} from "../../model/Doctor";
import {TokenService} from "../token/token.servise";
import {Router} from "@angular/router";
import {environment} from "../../../environments/environment";

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
}
