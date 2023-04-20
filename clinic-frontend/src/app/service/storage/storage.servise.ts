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

  constructor(private http: HttpClient) {
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
    return jwtDecode(localStorage.getItem(ACCESS_TOKEN));
  }

  public getRefreshToken(): any {
    return localStorage.getItem(REFRESH_TOKEN);
  }

  public getDecodedRefreshToken(): any {
    return jwtDecode(localStorage.getItem(REFRESH_TOKEN));
  }

  public isLoggedIn(): boolean {
    if (localStorage.getItem(ACCESS_TOKEN)) {
      return true;
    }
    return false;
  }

  public isAccessTokenExpired(): boolean {
    const expiryTime = this.getDecodedAccessToken()['exp'];
    if (expiryTime) {
      return expiryTime * 1000 - new Date().getTime() < 0;
    } else {
      return false;
    }
  }

  public isRefreshTokenExpired(): boolean {
    const expiryTime = this.getDecodedAccessToken()['exp'];
    if (expiryTime) {
      return expiryTime * 1000 - new Date().getTime() < 5000;
    } else {
      return false;
    }
  }
}
