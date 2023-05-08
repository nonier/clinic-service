import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";
import {TokenService} from "../../service/token.servise";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  isLoggedIn = false;
  username = '';
  password = '';

  constructor(private auth: AuthService, private tokenService: TokenService, private router: Router) {
    tokenService.isLoggedIn.subscribe((isLoggedId) => this.isLoggedIn = isLoggedId);
    this.isLoggedIn = !tokenService.isAccessTokenExpired();
  }

  ngOnInit(): void {
  }

  onSubmit() {
    let user = {
      username: this.username,
      password: this.password
    };
    this.auth.login(user).subscribe(
      token => {
        this.tokenService.saveTokens(token);
        this.router.navigateByUrl("/");
      }
    )
  }
}
