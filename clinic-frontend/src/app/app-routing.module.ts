import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {LoginPageComponent} from "./pages/login-page/login-page.component";
import {SiteLayoutComponent} from "./layouts/site-layout/site-layout.component";
import {RegistrationPageComponent} from "./pages/registration-page/registration-page.component";
import {DoctorsPageComponent} from "./pages/doctors-page/doctors-page.component";
import {ProfilePageComponent} from "./pages/profile-page/profile-page.component";
import {DialogPageComponent} from "./pages/dialog-page/dialog-page.component";

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
