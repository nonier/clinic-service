import {Injectable} from '@angular/core';
import {User} from "../../model/User";
import {HttpClient} from "@angular/common/http";
import {TokenService} from "../storage/storage.servise";
import {Observable} from "rxjs";
import {TokenResponse} from "../../model/TokenResponse";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private storageService: TokenService) {
  }

  login(user: User): Observable<TokenResponse> {
    return this.http.post<TokenResponse>(environment.apiHost + '/login', {
      username: user.username,
      password: user.password
    });
  }

  register(user: User): Observable<any> {
    return this.http.post<string>(environment.apiHost + '/register', {
      username: user.username,
      password: user.password
    });
  }

  logout() {
    this.storageService.clean();
  }
}
