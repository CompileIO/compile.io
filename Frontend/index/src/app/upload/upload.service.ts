import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Headers, RequestOptions } from '@angular/http';


@Injectable({
  providedIn: 'root'
})
export class UploadService {
private apiUrl = 'http://localhost:8080/api/uploads';
constructor(private http: HttpClient) { }
upload(files: File[]): Promise<File> {
  const empHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  return this.http.post(this.apiUrl, files, { headers: empHeaders, withCredentials: true})
  .toPromise()
  .then(response => response as File)
  .catch();
}
getClasses(): Promise<String[]> {
  const empHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  return this.http.get(this.apiUrl, { headers: empHeaders, withCredentials: true})
  .toPromise()
  .then(response => response as String[])
  .catch();
}
}

