import { TestBed, getTestBed } from '@angular/core/testing';
import {HttpTestingController, HttpClientTestingModule} from '@angular/common/http/testing';

import { ProfessorService } from '../professor.service';
import { HttpClient } from '@angular/common/http';
import { Professor } from 'src/models/professor';
import { environment } from '../../../environments/environment';

fdescribe('ProfessorService', () => {
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

    // it('should get create a professor',)
  });
