import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from "./app.component";
import {DoctorsComponent} from "./views/doctors/doctors.component";
import {SearchComponent} from "./views/search/search.component";
import {FreeConsultationPipe} from "./pipe/free-consultation.pipe";
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {NgxBootstrapMultiselectModule} from "ngx-bootstrap-multiselect";
import {AppRoutingModule} from "./app-routing.module";
import { LoginPageComponent } from './login-page/login-page.component';
import { AuthLayoutComponent } from './shared/layouts/auth-layout/auth-layout.component';
import { SiteLayoutComponent } from './shared/layouts/site-layout/site-layout.component';
import { RegistrationPageComponent } from './registration-page/registration-page.component';
import { DoctorsPageComponent } from './doctors-page/doctors-page.component';


@NgModule({
    declarations: [
        AppComponent,
        DoctorsComponent,
        SearchComponent,
        FreeConsultationPipe,
        LoginPageComponent,
        AuthLayoutComponent,
        SiteLayoutComponent,
        RegistrationPageComponent,
        DoctorsPageComponent
    ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    NgxBootstrapMultiselectModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
