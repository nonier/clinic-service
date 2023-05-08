import {Component} from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";
import {TokenService} from "../../service/token.servise";

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent {

  isLoggedIn = false;
  username = '';
  password = '';
  name = '';
  surname = '';

  constructor(private auth: AuthService, private tokenService: TokenService, private router: Router) {
    this.isLoggedIn = !tokenService.isAccessTokenExpired();
  }

  ngOnInit(): void {
  }

  onSubmit() {
    let user = {
      username: this.username,
      password: this.password,
      name: this.name,
      surname: this.surname
    };
    this.auth.register(user)
      .subscribe(result => this.router.navigateByUrl("/login"));
  }
}
