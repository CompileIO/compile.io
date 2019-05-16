import { TestBed, getTestBed } from '@angular/core/testing';
import {HttpTestingController, HttpClientTestingModule} from '@angular/common/http/testing';

import { ProfessorService } from '../professor.service';
import { HttpClient } from '@angular/common/http';
import { Professor } from 'src/models/professor';
import { environment } from '../../../environments/environment';

describe('ProfessorService', () => {
    let service:  ProfessorService;
    let http: HttpClient;
    let httpMock: HttpTestingController;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports:[HttpClientTestingModule],
        providers: [ProfessorService]
      });
      httpMock = getTestBed().get(HttpTestingController);
      service = getTestBed().get(ProfessorService);
    });
    it('it is created', ()=> {
      expect(service).toBeTruthy();
    });

    it('should get all professors from http', () => {
      let dummyProfessors:Professor[] = [
        {id:'1', courses: null, name: 'default', userName: 'defaultUsername'}
      ];

      service.getProfessors().subscribe(res => {
        expect(res.length).toBe(1);
        expect(res).toEqual(dummyProfessors);
      });

      let req = httpMock.expectOne(environment.BackendapiUrl+ '/Professors')
      expect(req.request.method).toBe("GET");
      req.flush(dummyProfessors);
    });

    it('should create a professor', () => {
      let dummyProfessor:Professor = {id:'1', courses: null, name: 'default', userName: 'defaultUsername'};

      service.createProfessor(dummyProfessor).subscribe(professor => {
        expect(professor).toEqual(dummyProfessor);
      });

      let req = httpMock.expectOne(environment.BackendapiUrl + '/Professor/Create')
      expect(req.request.method).toBe("POST");
      req.flush(dummyProfessor);
    });

    it('should update a professor', () => {
      let dummyProfessor:Professor = {id:'1', courses: null, name: 'default', userName: 'defaultUsername'};

      service.updateProfessor(dummyProfessor).subscribe(professor => {
        expect(professor).toEqual(dummyProfessor);
      });

      let req = httpMock.expectOne(environment.BackendapiUrl + '/Professor/Update/'+ dummyProfessor.id)
      expect(req.request.method).toBe("PUT");
      req.flush(dummyProfessor);
    });
  });
