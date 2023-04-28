import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {LoginPageComponent} from "./login-page/login-page.component";
import {SiteLayoutComponent} from "./shared/layouts/site-layout/site-layout.component";
import {RegistrationPageComponent} from "./registration-page/registration-page.component";
import {DoctorsPageComponent} from "./doctors-page/doctors-page.component";
import {ProfilePageComponent} from "./profile-page/profile-page.component";
import {DialogPageComponent} from "./dialog-page/dialog-page.component";

const routes: Routes = [
  {
    path: '', component: SiteLayoutComponent, children: [
      {path: '', redirectTo: '/doctors', pathMatch: 'full'},
      {path: 'doctors', component: DoctorsPageComponent},
      {path: 'profile', component: ProfilePageComponent},
      {path: 'login', component: LoginPageComponent},
      {path: 'registration', component: RegistrationPageComponent},
      {path: 'dialog/:id', component: DialogPageComponent}
    ]
  }
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
