import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Specialization} from "../../model/Specialization";
import {Observable, Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SpecializationService {

  constructor(private http: HttpClient) {
  }

  getSpecializations(): Observable<Specialization[]> {
    return this.http.get<Specialization[]>("http://localhost:8080/api/specializations");
  }
}
