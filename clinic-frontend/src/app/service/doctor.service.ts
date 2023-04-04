import {Injectable} from '@angular/core';
import {Doctor} from "../model/Doctor";
import {Subject} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class DoctorService{

  doctorsSubject =
    new Subject<Doctor[]>();

  constructor(private http: HttpClient) {
  }

  getDoctors() {
    return this.http.get<Doctor[]>("http://localhost:8080/doctors");
  }

  getDoctorsWithFilters(name: string) {
    let params = new HttpParams()
      .set('name', name);
    return this.http.get<Doctor[]>("http://localhost:8080/doctors/filter", {params})
  }
}
