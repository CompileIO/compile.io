import { TestBed } from '@angular/core/testing';

import { CodeService } from '../services/code.service';

describe('CodeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CodeService = TestBed.get(CodeService);
    expect(service).toBeTruthy();
  });
});
