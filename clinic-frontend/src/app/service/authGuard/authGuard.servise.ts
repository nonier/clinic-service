import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, Router, RouterStateSnapshot} from "@angular/router";
import {TokenService} from "../token/token.servise";

@Injectable({providedIn: 'root'})
export class AuthGuard{
  constructor(private router: Router, private tokenService: TokenService) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if (!this.tokenService.isAccessTokenExpired()) {
      return true;
    } else {
      // not logged in so redirect to login page with the return url
      this.router.navigate(['/login'], {queryParams: {returnUrl: state.url}});
      return false;
    }
  }
}
