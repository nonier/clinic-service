import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { DoctorsComponent } from './views/doctors/doctors.component';
import { HeaderComponent } from './views/header/header/header.component';

@NgModule({
  declarations: [
    AppComponent,
    DoctorsComponent,
    HeaderComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
