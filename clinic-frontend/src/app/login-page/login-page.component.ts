import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../service/auth/auth.service";
import {Router} from "@angular/router";
import {TokenService} from "../service/storage/storage.servise";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  form: FormGroup;
  isLoggedIn = false;

  constructor(private auth: AuthService, private tokenService: TokenService, private router: Router) {
    this.isLoggedIn = tokenService.isLoggedIn();
  }

  ngOnInit(): void {
    this.form = new FormGroup({
      username: new FormControl(null, [Validators.required, Validators.minLength(6)]),
      password: new FormControl(null, [Validators.required, Validators.minLength(6)])
    })
  }

  onSubmit() {
    let user = {
      username: this.form.value.username,
      password: this.form.value.password
    };
    this.auth.login(user).subscribe(
      token => {
        this.isLoggedIn = true;
        this.tokenService.saveTokens(token);
        this.router.navigateByUrl("/");
      }
    )
  }
}
