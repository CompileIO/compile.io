import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfessorHomeworkPageComponent } from './professor-homework-page.component';

describe('ProfessorHomeworkPageComponent', () => {
  let component: ProfessorHomeworkPageComponent;
  let fixture: ComponentFixture<ProfessorHomeworkPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProfessorHomeworkPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfessorHomeworkPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
