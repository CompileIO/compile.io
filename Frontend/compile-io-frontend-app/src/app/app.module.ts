import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { FormsModule } from '@angular/forms';
import { UserPageComponent } from './user-page/user-page.component';
import { HomeworkPageComponent } from './homework-page/homework-page.component'
import { ROUTING } from './app-routing/app-routing.module';

import { AuthenticationService } from './services/authentication.service';
import { ChangeHomeworkComponent } from './change-homework/change-homework.component';
import { CoursePageComponent } from './course-page/course-page.component';
import { SectionComponent } from './section/section.component';
import { ChangeCoursePageComponent } from './change-course-page/change-course-page.component';
import { ChangeSectionPageComponent } from './change-section-page/change-section-page.component';
import { StudentControllerModalComponent } from './student-controller-modal/student-controller-modal.component';
import { GradesPageComponent } from './grades-page/grades-page.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    UserPageComponent,  
    HomeworkPageComponent,
    ChangeHomeworkComponent,
    CoursePageComponent,
    SectionComponent,
    ChangeCoursePageComponent,
    ChangeSectionPageComponent,
    StudentControllerModalComponent,
    GradesPageComponent
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
