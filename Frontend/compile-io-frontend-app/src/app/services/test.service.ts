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

  uploadTest(file: File, type: string, runTime: number, givenHomework: string, givenCourse: string, userName: string): Observable<any> {
    let body: FormData = new FormData();              // MIGHT NEED TO CHANGE THE givenClass and givenHomework variables
    // console.log("THIS SHOULD BE FORM DATA: " + "file: " + file);   
    body.append('file', file);
    body.append('type', type);
    body.append('username', userName);
    body.append('runTime', runTime.toString());
    body.append('class', givenCourse);
    const fileHeaders = new HttpHeaders({'enctype': "multipart/form-data" }); 
    // , {headers: fileHeaders, withCredentials: true}
    return this.http.post(this.apiUrl + "/" + givenCourse + "/" + givenHomework + "/uploadTest", body , {headers: fileHeaders, withCredentials: true});
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
