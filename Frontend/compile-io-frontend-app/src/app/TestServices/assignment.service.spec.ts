import { TestBed } from '@angular/core/testing';

import { AssignmentService } from '../services/assignment.service';

describe('AssignmentService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AssignmentService = TestBed.get(AssignmentService);
    expect(service).toBeTruthy();
  });
});
