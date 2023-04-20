import {Component} from '@angular/core';
import {FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators} from "@angular/forms";
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

  /**
   * Why not working??
   */
  checkPasswordsValidator(): ValidatorFn {
    return (form: FormGroup): ValidationErrors => {
      let password = form.controls['password'];
      let confirmPassword = form.controls['confirmPassword']
      if (password.value === confirmPassword.value) {
        // confirmPassword.setErrors({notEquivalent: true})
        return {notEquivalent: true};
      } else {
        // confirmPassword.setErrors(null);
        return null;
      }
      // return;
    }
  }

  constructor(private auth: AuthService, private tokenService: TokenService, private router: Router) {
    this.isLoggedIn = tokenService.isLoggedIn();
  }

  ngOnInit(): void {
    this.form = new FormGroup({
      username: new FormControl(null, [Validators.required, Validators.minLength(6)]),
      password: new FormControl(null, [Validators.required, Validators.minLength(6)]),
      confirmPassword: new FormControl(null, [Validators.required, this.checkPasswordsValidator()])
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
