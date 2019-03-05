import { TestBed } from '@angular/core/testing';

import { SectionService } from '../services/section.service';

describe('UntitTestService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SectionService = TestBed.get(SectionService);
    expect(service).toBeTruthy();
  });
});
