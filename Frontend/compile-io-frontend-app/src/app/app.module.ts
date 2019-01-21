import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { UserPageComponent } from './user-page/user-page.component';
import { HomeworkPageComponent } from './homework-page/homework-page.component'
import { ROUTING } from './app-routing/app-routing.module';

import { AuthGuard } from './authguard.service';
import { TokenInterceptor } from './token.interceptor';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthenticationService } from './services/authentication.service';
import { ProfessorPageComponent } from './professor-page/professor-page.component';
import { ProfessorHomeworkPageComponent } from './professor-homework-page/professor-homework-page.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserPageComponent,
    HomeworkPageComponent,
    ProfessorPageComponent,
    ProfessorHomeworkPageComponent
  ],
  imports: [
    BrowserModule,
    ROUTING,
    FormsModule,
    HttpClientModule,
  ],
  providers: [
    AuthGuard,
    TokenInterceptor,
    AuthenticationService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }],
  bootstrap: [AppComponent]
})
export class AppModule { }
