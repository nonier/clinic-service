import {Injectable} from "@angular/core";
import jwtDecode from "jwt-decode";
import {TokenResponse} from "../../model/TokenResponse";
import {HttpClient} from "@angular/common/http";

const ACCESS_TOKEN = 'access-token';
const REFRESH_TOKEN = 'refresh-token';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor() {
  }

  clean(): void {
    localStorage.clear();
  }

  public saveTokens(tokenResponse: TokenResponse): void {
    localStorage.removeItem(ACCESS_TOKEN);
    localStorage.removeItem(REFRESH_TOKEN);
    localStorage.setItem(ACCESS_TOKEN, tokenResponse.accessToken);
    localStorage.setItem(REFRESH_TOKEN, tokenResponse.refreshToken);
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
    return localStorage.getItem(REFRESH_TOKEN) === null ? null : localStorage.getItem(REFRESH_TOKEN);
  }

  public isLoggedIn(): boolean {
    if (this.isAccessTokenExpired()) {
      return false;
    }
    return false;
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
