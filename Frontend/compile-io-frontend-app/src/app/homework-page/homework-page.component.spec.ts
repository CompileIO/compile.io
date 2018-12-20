import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeworkPageComponent } from './homework-page.component';

describe('HomeworkPageComponent', () => {
  let component: HomeworkPageComponent;
  let fixture: ComponentFixture<HomeworkPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HomeworkPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeworkPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
