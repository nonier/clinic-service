import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AppComponent} from "./app.component";
import {FreeConsultationPipe} from "./pipe/free-consultation.pipe";
import {NgxBootstrapMultiselectModule} from "ngx-bootstrap-multiselect";
import {AppRoutingModule} from "./app-routing.module";
import {LoginPageComponent} from './login-page/login-page.component';
import {SiteLayoutComponent} from './shared/layouts/site-layout/site-layout.component';
import {RegistrationPageComponent} from './registration-page/registration-page.component';
import {DoctorsPageComponent} from './doctors-page/doctors-page.component';
import {AuthInterceptor} from "./interseptor/AuthInterseptor";
import {ProfilePageComponent} from "./profile-page/profile-page.component";
import {RoleNamePipe} from './pipe/role-name.pipe';
import { DialogPageComponent } from './dialog-page/dialog-page.component';


@NgModule({
  declarations: [
    AppComponent,
    FreeConsultationPipe,
    LoginPageComponent,
    SiteLayoutComponent,
    RegistrationPageComponent,
    DoctorsPageComponent,
    ProfilePageComponent,
    RoleNamePipe,
    DialogPageComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    NgxBootstrapMultiselectModule,
    ReactiveFormsModule,
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
