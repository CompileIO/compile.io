import { async, ComponentFixture, TestBed, inject } from '@angular/core/testing';

import { ChangeCoursePageComponent } from './change-course-page.component';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Course } from 'src/models/course';
import { Professor } from 'src/models/professor';

describe('ChangeCoursePageComponent', () => {
  let component: ChangeCoursePageComponent;
  let fixture: ComponentFixture<ChangeCoursePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChangeCoursePageComponent ],
      imports: [FormsModule,
        HttpClientModule,
        Course,
        Professor]
    })
    .compileComponents();
  }));

  
  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeCoursePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', async(inject([Course, Professor],
    (username: string, courseInfo: Course, prof: Professor) => {
    expect(component).toBeTruthy();
  })));
});
