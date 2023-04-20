import {Injectable} from '@angular/core';
import {User} from "../../model/User";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {map} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {
  }

  register() {

  }

  login(user: User) {
    const headers = new HttpHeaders({ Authorization: 'Basic ' + btoa(user.username + ':' + user.password) });
    return this.http.get<User>('/doctors',{headers}).pipe(
      map(
        userData => {
          sessionStorage.setItem('username',user.username);
          return userData;
        }
      ));
  }

  createBasicAuthToken(username: String, password: String) {
    return 'Basic ' + window.btoa(username + ":" + password)
  }
}
