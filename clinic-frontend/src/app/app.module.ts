import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {DoctorsComponent} from './views/doctors/doctors.component';
import {NavComponent} from './views/header/nav/nav.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {NgxBootstrapMultiselectModule} from "ngx-bootstrap-multiselect";
import {SearchComponent} from "./views/search/search.component";
import { FreeConsultationPipe } from './pipe/free-consultation.pipe';
import { LoginComponent } from './views/login/login.component';

@NgModule({
    declarations: [
        AppComponent,
        DoctorsComponent,
        NavComponent,
        SearchComponent,
        FreeConsultationPipe,
        LoginComponent
    ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    NgxBootstrapMultiselectModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
