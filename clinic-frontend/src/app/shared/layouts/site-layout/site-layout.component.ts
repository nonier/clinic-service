import {Component, OnInit} from '@angular/core';
import {TokenService} from "../../../service/token/token.servise";
import {Router} from "@angular/router";

@Component({
  selector: 'app-site-layout',
  templateUrl: './site-layout.component.html',
  styleUrls: ['./site-layout.component.css']
})
export class SiteLayoutComponent implements OnInit {

  isLoggedIn = false;

  constructor(private tokenService: TokenService, private router: Router) {
  }

  check() {
    console.log(this.tokenService.isRefreshTokenExpired());
  }

  logout() {
    this.isLoggedIn = false;
    this.tokenService.clean();
    this.router.navigateByUrl("/");
  }

  ngOnInit(): void {
    this.isLoggedIn = !this.tokenService.isAccessTokenExpired();
  }
}
