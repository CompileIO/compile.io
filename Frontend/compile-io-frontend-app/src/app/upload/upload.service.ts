import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UploadService {
  private apiUrl = 'http://137.112.104.111:8080';
  //private apiUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  upload(file: File): Promise<String[]> {
    let body = new FormData();
    body.append("file", file);
    const fileHeaders = new HttpHeaders({ 'Content-Type': 'multipart/form-data' });
    return this.http.post(this.apiUrl, body)
      .toPromise()
      .then(response => response as String[])
      .catch();
  }
  getClasses(): Promise<String[]> {
    const empHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.get(this.apiUrl + "/classes", { headers: empHeaders, withCredentials: true })
      .toPromise()
      .then(response => response as String[])
      .catch();
  }
  runDocker(): Promise<String[]> {
    const empHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.get(this.apiUrl + "/run", { headers: empHeaders, withCredentials: true })
      .toPromise()
      .then(response => response as String[])
      .catch();
  }
  getHomeworks(givenClass: string): Promise<String[]> {
    const empHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.get(this.apiUrl +"/"+givenClass, { headers: empHeaders, withCredentials: true })
      .toPromise()
      .then(response => response as String[])
      .catch();
  }
  getResults(givenClass: string, givenHomework: string): Promise<String[]> {
    const empHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.get(this.apiUrl + "/" + givenClass + "/" + givenHomework, { headers: empHeaders, withCredentials: true })
      .toPromise()
      .then(response => response as String[])
      .catch();
  }
}

