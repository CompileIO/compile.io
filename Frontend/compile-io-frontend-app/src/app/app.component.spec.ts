import { TestBed, async } from '@angular/core/testing';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';

import { UserPageComponent } from './user-page/user-page.component';
import { HomeworkPageComponent } from './homework-page/homework-page.component'
import { ChangeHomeworkComponent } from './change-homework/change-homework.component';
import { CoursePageComponent } from './course-page/course-page.component';
import { SectionComponent } from './section/section.component';
import { ChangeCoursePageComponent } from './change-course-page/change-course-page.component';
import { ChangeSectionPageComponent } from './change-section-page/change-section-page.component';
import { StudentControllerModalComponent } from './student-controller-modal/student-controller-modal.component';
import { GradesPageComponent } from './grades-page/grades-page.component';

describe('AppComponent', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
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
    }).compileComponents();
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'compile-io-frontend-app'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('compile-io-frontend-app');
  });

  it('should render title in a h1 tag', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.debugElement.nativeElement;
    expect(compiled.querySelector('h1').textContent).toContain('Welcome to compile-io-frontend-app!');
  });
});
