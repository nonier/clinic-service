import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {DataService} from "./data.service";
import {Consultation} from "../model/Consultation";

const CONSULTATION_API_URL = environment.apiHost + '/consultations';

@Injectable({
  providedIn: 'root'
})
export class ConsultationService {

  constructor(private http: HttpClient, private dataService: DataService) {
  }

  fetchAllUserConsultations() {
    this.http.get<Consultation[]>(CONSULTATION_API_URL)
      .subscribe(consultations => this.dataService.updateConsultations(consultations));
  }

  getUserConsultations() {
    return this.dataService.getConsultations();
  }

  chooseConsultation(consultationId: number) {
    this.http.patch(CONSULTATION_API_URL + "/" + consultationId + "/choose", {})
      .subscribe(() => location.reload());
  }
}
