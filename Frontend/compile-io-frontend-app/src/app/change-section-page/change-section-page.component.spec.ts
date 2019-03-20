import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeSectionPageComponent } from './change-section-page.component';

describe('ChangeSectionPageComponent', () => {
  let component: ChangeSectionPageComponent;
  let fixture: ComponentFixture<ChangeSectionPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChangeSectionPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeSectionPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
