import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../service/auth/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  form: FormGroup;

  constructor(private auth: AuthService, private router: Router) {
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
      result => {
            console.log(this.form.get('username') + ':' + this.form.get('password'));
            sessionStorage.setItem(
              'token',
              btoa(this.form.get('username') + ':' + this.form.get('password'))
            );
            this.router.navigate(['']);
        },
      () => {
        console.log('unauthorized');
      }
    )
  }
}
