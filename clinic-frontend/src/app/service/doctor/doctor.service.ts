import {Injectable} from '@angular/core';
import {Subject} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Doctor} from "../../model/Doctor";

@Injectable({
  providedIn: 'root'
})
export class DoctorService{

  doctorsSubject =
    new Subject<Doctor[]>();

  constructor(private http: HttpClient) {
  }

  getDoctors() {
    this.http.get<Doctor[]>("http://localhost:8080/doctors")
      .subscribe((doctors: Doctor[]) => {
          this.doctorsSubject.next(doctors);
        }
      )
  }

  getDoctorsWithFilters(name: string, specializationIds: number[]) {
    let params = new HttpParams()
      .set('name', name)
      .set('specializationIds', specializationIds.join(','));
    this.http.get<Doctor[]>("http://localhost:8080/doctors/filter", {params})
      .subscribe((doctors: Doctor[]) => {
        this.doctorsSubject.next(doctors);
      })
  }
}
