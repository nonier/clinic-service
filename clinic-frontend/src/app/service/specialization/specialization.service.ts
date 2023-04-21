import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Specialization} from "../../model/Specialization";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";

const SPECIALIZATION_API_URL = environment.apiHost + '/specializations';

@Injectable({
  providedIn: 'root'
})
export class SpecializationService {

  constructor(private http: HttpClient) {
  }

  getSpecializations(): Observable<Specialization[]> {
    return this.http.get<Specialization[]>(SPECIALIZATION_API_URL);
  }
}
