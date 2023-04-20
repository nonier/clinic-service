import { Component } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../service/auth/auth.service";
import {Router} from "@angular/router";
import {TokenService} from "../service/storage/storage.servise";

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent {

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
    this.auth.register(user)
      .subscribe(result => this.router.navigateByUrl("/auth/login"));
  }
}
