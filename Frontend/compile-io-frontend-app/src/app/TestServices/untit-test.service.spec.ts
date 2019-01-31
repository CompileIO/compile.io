import { TestBed } from '@angular/core/testing';

import { UntitTestService } from '../services/untit-test.service';

describe('UntitTestService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: UntitTestService = TestBed.get(UntitTestService);
    expect(service).toBeTruthy();
  });
});
