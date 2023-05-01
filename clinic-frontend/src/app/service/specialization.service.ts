import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Specialization} from "../model/Specialization";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {DataService} from "./data.service";

const SPECIALIZATION_API_URL = environment.apiHost + '/specializations';

@Injectable({
  providedIn: 'root'
})
export class SpecializationService {

  constructor(private http: HttpClient, private dataService: DataService) {
  }

  fetchAllSpecializations() {
    this.http.get<Specialization[]>(SPECIALIZATION_API_URL)
      .subscribe(specializations => this.dataService.updateSpecializations(specializations));
  }

  getSpecializations(): Observable<Specialization[]> {
    return this.dataService.getSpecializations();
  }
}
