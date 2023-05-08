import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";
import {Consultation} from "../model/Consultation";
import {User} from "../model/User";

const CLIENT_API_URL = environment.apiHost + '/clients';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient) {
  }

  getClientConsultations(): Observable<Consultation[]> {
    return this.http.get<Consultation[]>(CLIENT_API_URL + '/consultations');
  }

  chooseConsultation(consultationId: number) {
    this.http.put(CLIENT_API_URL + '/consultations/' + consultationId, {})
      .subscribe(() => location.reload());
  }

  getClientInfo(): Observable<User> {
    return this.http.get<User>(CLIENT_API_URL);
  }

  changeClientInfo(user: User) {
    this.http.put(CLIENT_API_URL, user)
      .subscribe();
  }
}
