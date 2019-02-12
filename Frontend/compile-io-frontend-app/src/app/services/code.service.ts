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

  uploadCode(type: string, runTime: number, givenAssignmentID: string, userName: string): Observable<any> {
    let body: FormData = new FormData(); 
    body.append('type', type);
    body.append('username', userName);
    body.append('runTime', runTime.toString());
    body.append('assignmentID', givenAssignmentID.toString());
    const fileHeaders = new HttpHeaders({'enctype': "multipart/form-data" });
    return this.http.post(this.apiUrl + "/Code/uploadCode", body , {headers: fileHeaders, withCredentials: true});
  }

  uploadFile(file: File, className: string, assignmentName: string, userName: string): Observable<any> {
    let body: FormData = new FormData();  
    body.append('file', file);
    body.append('courseName', className);
    body.append('assignmentName', assignmentName);
    body.append('userName', userName);
    const fileHeaders = new HttpHeaders({'enctype': "multipart/form-data" }); 
    return this.http.post(this.apiUrl + "/Code/uploadFile", body, {headers: fileHeaders, withCredentials: true});
  }
}
