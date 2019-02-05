import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CodeService {

  private apiUrl = environment.BackendapiUrl;
  constructor(private http: HttpClient) { }

  uploadCode(file: File): Observable<any> {
    let body = new FormData();
    body.append("file", file);
    const fileHeaders = new HttpHeaders({ 'Content-Type': 'multipart/form-data' });
    return this.http.post(this.apiUrl, body);
  }
}
