import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Doctor} from "../model/Doctor";
import {environment} from "../../environments/environment";
import {Consultation} from "../model/Consultation";
import {DataService} from "./data.service";
import {User} from "../model/User";

const PROFILE_API_URL = environment.apiHost + '/profile';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http: HttpClient) {
  }

  getUserInfo(): Observable<User> {
    return this.http.get<User>(PROFILE_API_URL);
  }

  updateClientInfo(user: User){
    this.http.put(PROFILE_API_URL, user)
      .subscribe();
  }
}
