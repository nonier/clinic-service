import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {AuthLayoutComponent} from "./shared/layouts/auth-layout/auth-layout.component";
import {LoginPageComponent} from "./login-page/login-page.component";
import {SiteLayoutComponent} from "./shared/layouts/site-layout/site-layout.component";
import {RegistrationPageComponent} from "./registration-page/registration-page.component";
import {DoctorsPageComponent} from "./doctors-page/doctors-page.component";

const routes: Routes = [
  {path: 'auth', component: AuthLayoutComponent, children: [
      {path: 'login', component: LoginPageComponent},
      {path: 'registration', component: RegistrationPageComponent}
    ]},
  {path: '', component: SiteLayoutComponent, children: [
      {path: '', redirectTo: '/doctors', pathMatch: 'full'},
      {path: 'doctors', component: DoctorsPageComponent}
    ]},
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
  }
)
export class AppRoutingModule {

}
