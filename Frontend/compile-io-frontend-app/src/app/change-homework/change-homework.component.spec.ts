import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeHomeworkComponent } from './change-homework.component';

describe('ChangeHomeworkComponent', () => {
  let component: ChangeHomeworkComponent;
  let fixture: ComponentFixture<ChangeHomeworkComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChangeHomeworkComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeHomeworkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
