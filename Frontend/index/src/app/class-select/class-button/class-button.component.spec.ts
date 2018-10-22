import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClassButtonComponent } from './class-button.component';

describe('ClassButtonComponent', () => {
  let component: ClassButtonComponent;
  let fixture: ComponentFixture<ClassButtonComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClassButtonComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClassButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
