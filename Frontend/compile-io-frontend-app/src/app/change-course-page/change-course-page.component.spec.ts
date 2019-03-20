import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeCoursePageComponent } from './change-course-page.component';

describe('ChangeCoursePageComponent', () => {
  let component: ChangeCoursePageComponent;
  let fixture: ComponentFixture<ChangeCoursePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChangeCoursePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeCoursePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
