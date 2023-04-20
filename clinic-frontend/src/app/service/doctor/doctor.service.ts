import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Doctor} from "../../model/Doctor";

@Injectable({
  providedIn: 'root'
})
export class DoctorService {

  constructor(private http: HttpClient) {
  }

  getDoctors(): Observable<Doctor[]> {
    return this.http.get<Doctor[]>("http://localhost:8080/api/doctors");
  }

  getDoctorsWithFilters(name: string, specializationIds: number[]): Observable<Doctor[]> {
    let params = new HttpParams()
      .set('name', name)
      .set('specializationIds', specializationIds.join(','));
    return this.http.get<Doctor[]>("http://localhost:8080/api/doctors/filter", {params});
  }
}
