import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { UserPageComponent } from './user-page/user-page.component';
import { ClassInfoComponent } from './class-info/class-info.component';
import { HomeworkPageComponent } from './homework-page/homework-page.component'

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserPageComponent,
    ClassInfoComponent,
    HomeworkPageComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
