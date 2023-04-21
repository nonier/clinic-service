import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";
import {Consultation} from "../../model/Consultation";
import {User} from "../../model/User";
import {TokenService} from "../storage/storage.servise";
import {Router} from "@angular/router";

const CLIENT_API_URL = environment.apiHost + '/clients';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient, private tokenService: TokenService, private router: Router) {
  }

  getClientConsultations(): Observable<Consultation[]> {
    if (this.tokenService.isAccessTokenExpired()) {
      this.router.navigateByUrl("/auth/login");
    } else {
      return this.http.get<Consultation[]>(CLIENT_API_URL + '/consultations');
    }
  }

  chooseConsultation(consultationId: number) {
    if (this.tokenService.isAccessTokenExpired()) {
      this.router.navigateByUrl("/auth/login");
    } else {
      this.http.put(CLIENT_API_URL + '/consultations/' + consultationId, {})
        .subscribe();
      location.reload();
    }
  }

  getClientInfo(): Observable<User> {
    if (this.tokenService.isAccessTokenExpired()) {
      this.router.navigateByUrl("/auth/login");
    } else {
      return this.http.get<User>(CLIENT_API_URL);
    }
  }
}
