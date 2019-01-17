import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UploadService {
  private apiUrl = 'http://137.112.104.111:8080';
  //private apiUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  upload(file: File): Observable<any> {
    let body = new FormData();
    body.append("file", file);
    const fileHeaders = new HttpHeaders({ 'Content-Type': 'multipart/form-data' });
    return this.http.post(this.apiUrl, body);
  }
  getClasses(): Observable<any> {
    const empHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.get(this.apiUrl + "/classes", { headers: empHeaders, withCredentials: true });
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
}

