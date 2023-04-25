import {Component, OnInit} from '@angular/core';
import {TokenService} from "../../../service/token/token.servise";
import {Router} from "@angular/router";
import {AuthService} from "../../../service/auth/auth.service";

@Component({
  selector: 'app-site-layout',
  templateUrl: './site-layout.component.html',
  styleUrls: ['./site-layout.component.css']
})
export class SiteLayoutComponent implements OnInit {

  isLoggedIn = false;

  constructor(private tokenService: TokenService, private router: Router, private authService: AuthService) {
    tokenService.isLoggedIn.subscribe((isLoggedId) => this.isLoggedIn = isLoggedId);
  }

  check() {
    this.authService.getCurrentUser();
  }

  logout() {
    this.tokenService.clean();
    this.router.navigateByUrl("/");
  }

  ngOnInit(): void {
    this.isLoggedIn = !this.tokenService.isAccessTokenExpired();
  }
}
