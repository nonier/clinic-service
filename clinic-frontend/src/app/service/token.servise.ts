import {Injectable, OnInit} from "@angular/core";
import jwtDecode from "jwt-decode";
import {TokenResponse} from "../model/TokenResponse";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment";
import {BehaviorSubject, Observable, Subject} from "rxjs";

const ACCESS_TOKEN = 'access-token';
const REFRESH_TOKEN = 'refresh-token';

@Injectable({
  providedIn: 'root'
})
export class TokenService implements OnInit {

  isLoggedIn: Subject<boolean> = new BehaviorSubject(false);

  constructor(private http: HttpClient) {
  }


  ngOnInit(): void {
    this.clean();
  }

  clean(): void {
    this.isLoggedIn.next(false);
    localStorage.removeItem(ACCESS_TOKEN);
    localStorage.removeItem(REFRESH_TOKEN);
  }

  public saveTokens(tokenResponse: TokenResponse): void {
    this.isLoggedIn.next(true);
    localStorage.removeItem(ACCESS_TOKEN);
    localStorage.removeItem(REFRESH_TOKEN);
    localStorage.setItem(ACCESS_TOKEN, tokenResponse.accessToken);
    localStorage.setItem(REFRESH_TOKEN, tokenResponse.refreshToken);
  }

  public saveAccessToken(accessToken: string) {
    this.isLoggedIn.next(true);
    localStorage.removeItem(ACCESS_TOKEN);
    localStorage.setItem(ACCESS_TOKEN, accessToken);
  }

  public getAccessToken(): any {
    return localStorage.getItem(ACCESS_TOKEN);
  }

  public getDecodedAccessToken(): any {
    return localStorage.getItem(ACCESS_TOKEN) === null ? null : jwtDecode(localStorage.getItem(ACCESS_TOKEN));
  }

  public getRefreshToken(): any {
    return localStorage.getItem(REFRESH_TOKEN);
  }

  public getDecodedRefreshToken(): any {
    return localStorage.getItem(REFRESH_TOKEN) === null ? null : jwtDecode(localStorage.getItem(REFRESH_TOKEN));
  }

  public isAccessTokenExpired(): boolean {
    let accessToken = this.getDecodedAccessToken();
    if (accessToken) {
      const expiryTime = accessToken['exp'];
      if (expiryTime) {
        return expiryTime * 1000 - new Date().getTime() < 0;
      }
    }
    return true;
  }

  public refreshAccessToken(): Observable<TokenResponse> {
    return this.http.post<TokenResponse>(environment.apiHost + '/auth/token/access', {refreshToken: this.getRefreshToken()});
  }

  public isRefreshTokenExpired(): boolean {
    let refreshToken = this.getDecodedRefreshToken();
    if (refreshToken) {
      const expiryTime = refreshToken['exp'];
      if (expiryTime) {
        return expiryTime * 1000 - new Date().getTime() < 5000;
      }
    }
    return true;
  }
}
