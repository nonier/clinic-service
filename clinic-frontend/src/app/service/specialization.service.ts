import {Injectable} from '@angular/core';
import {Doctor} from "../model/Doctor";
import {HttpClient} from "@angular/common/http";
import {Specialization} from "../model/Specialization";

@Injectable({
  providedIn: 'root'
})
export class SpecializationService {

  constructor(private http: HttpClient) {
  }

  getSpecializations() {
    return this.http.get<Specialization[]>("http://localhost:8080/specializations");
  }
}
