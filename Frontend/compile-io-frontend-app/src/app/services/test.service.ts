import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TestService {

  private apiUrl = environment.BackendapiUrl;
  constructor(private http: HttpClient) { }

  uploadTest(userName: string, file: File, type: string, runTime: string, givenCourse: string, givenAssignment: string): Observable<any> {
    let body = new FormData();              // MIGHT NEED TO CHANGE THE givenClass and givenHomework variables
    body.append("username", userName)
    body.append("file", file);
    body.append("type", type);
    body.append("runTime", runTime);
    const fileHeaders = new HttpHeaders({ 'Content-Type': 'multipart/form-data' });
    return this.http.post(this.apiUrl + "/" + givenCourse + "/" + givenAssignment +"/test", body);
  }
  
  runDocker(): Observable<any> {
    const empHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.get(this.apiUrl + "/run", { headers: empHeaders, withCredentials: true });
  }
  
  getResults(givenCourse: string, givenAssignment: string): Observable<any> {
    const empHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.get(this.apiUrl + "/" + givenCourse + "/" + givenAssignment, { headers: empHeaders, withCredentials: true });
  }
  getTests(givenCourse: string, givenAssignment: string): Observable<any> {
    const empHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.get(this.apiUrl + "/" + givenCourse + "/" + givenAssignment + "/tests", { headers: empHeaders, withCredentials: true });
  }
}
