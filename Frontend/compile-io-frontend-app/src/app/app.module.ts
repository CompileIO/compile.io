import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { UserPageComponent } from './user-page/user-page.component';
import { HomeworkPageComponent } from './homework-page/homework-page.component'
import { ROUTING } from './app-routing/app-routing.module';

import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthenticationService } from './services/authentication.service';
import { ProfessorPageComponent } from './professor-page/professor-page.component';
import { ProfessorHomeworkPageComponent } from './professor-homework-page/professor-homework-page.component';
import { AddHomeworkComponent } from './add-homework/add-homework.component';
import { ChangeHomeworkComponent } from './change-homework/change-homework.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserPageComponent,
    HomeworkPageComponent,
    ProfessorPageComponent,
    ProfessorHomeworkPageComponent,
    AddHomeworkComponent,
    ChangeHomeworkComponent
  ],
  imports: [
    BrowserModule,
    ROUTING,
    FormsModule,
    HttpClientModule,
  ],
  providers: [
    AuthenticationService
  ],

  bootstrap: [AppComponent]
})
export class AppModule { }
