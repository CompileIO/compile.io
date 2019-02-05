import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AssignmentService {

  private apiUrl = environment.BackendapiUrl;
  constructor(private http: HttpClient) { }

  getAssignments(givenClass: string): Observable<any> {
    const empHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.get(this.apiUrl + "/" + givenClass, { headers: empHeaders, withCredentials: true });
  }

  addAssignment(): Observable<any> {
    let body = new FormData();
    // body.append("file", file);
    const assignmentHeaders = new HttpHeaders({ 'Content-Type': 'multipart/form-data' });
    return this.http.post(this.apiUrl, { headers: assignmentHeaders, withCredentials: true });
  }
}
