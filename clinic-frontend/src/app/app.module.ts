import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {AppComponent} from "./app.component";
import {FreeConsultationPipe} from "./pipe/free-consultation.pipe";
import {NgxBootstrapMultiselectModule} from "ngx-bootstrap-multiselect";
import {AppRoutingModule} from "./app-routing.module";
import {LoginPageComponent} from './pages/login-page/login-page.component';
import {SiteLayoutComponent} from './layouts/site-layout/site-layout.component';
import {RegistrationPageComponent} from './pages/registration-page/registration-page.component';
import {DoctorsPageComponent} from './pages/doctors-page/doctors-page.component';
import {AuthInterceptor} from "./interseptor/AuthInterseptor";
import {ProfilePageComponent} from "./pages/profile-page/profile-page.component";
import {RoleNamePipe} from './pipe/role-name.pipe';
import { DialogPageComponent } from './pages/dialog-page/dialog-page.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatIconModule} from "@angular/material/icon";
import { NZ_I18N } from 'ng-zorro-antd/i18n';
import { en_US } from 'ng-zorro-antd/i18n';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import {NzSpaceModule} from "ng-zorro-antd/space";
import {NzCardModule} from "ng-zorro-antd/card";
import {NzGridModule} from "ng-zorro-antd/grid";
import {NzAvatarModule} from "ng-zorro-antd/avatar";
import {NzListModule} from "ng-zorro-antd/list";
import {NzDescriptionsModule} from "ng-zorro-antd/descriptions";
import {NzTypographyModule} from "ng-zorro-antd/typography";
import {MatGridListModule} from "@angular/material/grid-list";
import {MatListModule} from "@angular/material/list";
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {MatInputModule} from "@angular/material/input";
import {MatChipsModule} from "@angular/material/chips";
import {MatAutocompleteModule} from "@angular/material/autocomplete";
import {MatSelectModule} from "@angular/material/select";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";

registerLocaleData(en);


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
    BrowserAnimationsModule,
    NzSpaceModule,
    NzCardModule,
    NzGridModule,
    NzAvatarModule,
    NzListModule,
    NzDescriptionsModule,
    NzTypographyModule,
    MatGridListModule,
    MatListModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatChipsModule,
    MatAutocompleteModule,
    MatSelectModule,
    MatToolbarModule,
    MatDatepickerModule,
    MatNativeDateModule
  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true},
    { provide: NZ_I18N, useValue: en_US }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
