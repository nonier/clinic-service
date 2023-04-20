import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {TokenService} from "../storage/storage.servise";
import {Router} from "@angular/router";
import {Observable} from "rxjs";
import {Consultation} from "../../model/Consultation";

@Injectable({
  providedIn: 'root'
})
export class ConsultationService {

  constructor(private http: HttpClient, private tokenService: TokenService,
              private router: Router) {
  }

  getClientConsultations() : Observable<Consultation[]> {
    return this.http.get<Consultation[]>("http://localhost:8080/api/consultations/client");
  }

  chooseConsultation(consultationId: number) {
    if (this.tokenService.isAccessTokenExpired()) {
      this.router.navigateByUrl("/auth/login");
    } else {
      this.http.put("http://localhost:8080/api/consultations/" + consultationId, {})
        .subscribe();
      location.reload();
    }
  }
}
