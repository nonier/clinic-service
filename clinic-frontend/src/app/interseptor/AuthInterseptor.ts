import {Injectable} from "@angular/core";
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";
import {TokenService} from "../service/token/token.servise";
import {TokenResponse} from "../model/TokenResponse";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private tokenService: TokenService) {
  }

  intercept(req: HttpRequest<any>,
            next: HttpHandler): Observable<HttpEvent<any>> {
    let accessToken = this.tokenService.getAccessToken();
    if (accessToken) {
      console.log('access token found!');
      req = this.setAuthToken(req, accessToken);
    }
    return next.handle(req).pipe(catchError((error: HttpErrorResponse) => {
      if (!req.url.includes("/auth/login") && error.status === 401) {
        console.log('try to refresh accessToken');
        if (this.tokenService.getRefreshToken()) {
          this.tokenService.refreshAccessToken().subscribe({
            next: (tokenResponse:TokenResponse) => {
              console.log('get new access token' + tokenResponse.accessToken);
              this.tokenService.saveAccessToken(tokenResponse.accessToken);
            },
            error: (error) => {
              console.log('invalid refresh token');
              this.tokenService.clean();
            }
          });
        } else {
          console.log('no refresh token')
          this.tokenService.clean();
        }
      }
      return throwError(() => error);
    }));
  }

  private setAuthToken(req: HttpRequest<any>, token: string): HttpRequest<any> {
    return req.clone({
      headers: req.headers.set("Authorization",
        "Bearer " + token)
    });
  }
}
