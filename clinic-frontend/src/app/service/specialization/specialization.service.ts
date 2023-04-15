import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Specialization} from "../../model/Specialization";
import {Subject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class SpecializationService {

  specializationSubject =
    new Subject<Specialization[]>();

  constructor(private http: HttpClient) {
  }

  getSpecializations() {
    this.http.get<Specialization[]>("/api/specializations")
      .subscribe((spec) => {
        this.specializationSubject.next(spec);
      })
  }
}
