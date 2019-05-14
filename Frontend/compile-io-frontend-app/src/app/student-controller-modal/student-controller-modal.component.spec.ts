import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentControllerModalComponent } from './student-controller-modal.component';

describe('StudentControllerModalComponent', () => {
  let component: StudentControllerModalComponent;
  let fixture: ComponentFixture<StudentControllerModalComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StudentControllerModalComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentControllerModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  // it('should create', () => {
  //   expect(component).toBeTruthy();
  // });
});
