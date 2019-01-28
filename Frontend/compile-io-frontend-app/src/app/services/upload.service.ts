import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { backendInterface, backendInterfaceService } from './backendInterface.service';

@Injectable({
  providedIn: 'root'
})
export class UploadService extends backendInterfaceService{
  // private apiUrl = environment.BackendapiUrl;
  // constructor(private http: HttpClient) { }
  super() {}

  upload(file: File): Observable<any> {
    let body = new FormData();
    body.append("file", file);
    const fileHeaders = new HttpHeaders({ 'Content-Type': 'multipart/form-data' });
    return this.http.post(this.apiUrl, body);
  }
  uploadTest(userName: string, file: File, type: string, runTime: string, givenClass: string, givenHomework: string): Observable<any> {
    let body = new FormData();              // MIGHT NEED TO CHANGE THE givenClass and givenHomework variables
    body.append("username", userName)
    body.append("file", file);
    body.append("type", type);
    body.append("runTime", runTime);
    const fileHeaders = new HttpHeaders({ 'Content-Type': 'multipart/form-data' });
    return this.http.post(this.apiUrl + "/" + givenClass + "/" + givenHomework +"/test", body);
  }
  
  runDocker(): Observable<any> {
    const empHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.get(this.apiUrl + "/run", { headers: empHeaders, withCredentials: true });
  }
  getHomeworks(givenClass: string): Observable<any> {
    const empHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.get(this.apiUrl + "/" + givenClass, { headers: empHeaders, withCredentials: true });
  }
  getResults(givenClass: string, givenHomework: string): Observable<any> {
    const empHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.get(this.apiUrl + "/" + givenClass + "/" + givenHomework, { headers: empHeaders, withCredentials: true });
  }
  getTests(givenClass: string, givenHomework: string): Observable<any> {
    const empHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.get(this.apiUrl + "/" + givenClass + "/" + givenHomework + "/tests", { headers: empHeaders, withCredentials: true });
  }
}

