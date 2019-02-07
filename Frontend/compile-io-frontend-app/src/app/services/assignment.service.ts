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

  addAssignment(form: FormData, startDate: Date, endDate: Date): Observable<any> {
    const assignmentHeaders = new HttpHeaders({ 'Content-Type': 'multipart/form-data' });
    return this.http.post(this.apiUrl + "/Homework/updateHomework", form,{ headers: assignmentHeaders, withCredentials: true });
  }

  updateAssignment(form: FormData, startDate: Date, endDate: Date) {
    const fileHeaders = new HttpHeaders({'enctype': "multipart/form-data" }); 
    return this.http.put(this.apiUrl + "/Homework/updateHomework",form, { headers: fileHeaders, withCredentials: true } );
  }
}
