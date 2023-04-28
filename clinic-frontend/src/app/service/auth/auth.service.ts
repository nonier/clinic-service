import {Injectable} from '@angular/core';
import {User} from "../../model/User";
import {HttpClient} from "@angular/common/http";
import {TokenService} from "../token/token.servise";
import {Observable} from "rxjs";
import {TokenResponse} from "../../model/TokenResponse";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private tokenService: TokenService) {
  }

  getCurrentUser(): User {
    let accessToken = this.tokenService.getDecodedAccessToken();
    if (accessToken) {
      let user = {
        id: accessToken['userId'],
        name: accessToken['name'],
        username: accessToken['sub'],
        roles: accessToken['roles']
      };
      return user;
    }
    return null;
  }

  login(user: User): Observable<TokenResponse> {
    return this.http.post<TokenResponse>(environment.apiHost + '/auth/login', {
      username: user.username,
      password: user.password
    });
  }

  register(user: User): Observable<any> {
    return this.http.post<string>(environment.apiHost + '/auth/register', {
      username: user.username,
      password: user.password
    });
  }

  logout() {
    this.tokenService.clean();
  }
}
