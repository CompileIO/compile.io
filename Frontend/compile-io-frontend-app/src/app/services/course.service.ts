import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { BrowserDynamicTestingModule } from '@angular/platform-browser-dynamic/testing';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private apiUrl = environment.BackendapiUrl;
  constructor(private http: HttpClient) { }

  
  getCourses(): Observable<any> {
    const empHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.get(this.apiUrl + "/courses", { headers: empHeaders, withCredentials: true });
  }


  // updateAssignment(assignmentData: Assignment) {
  //   const headers = new HttpHeaders({'enctype': "multipart/form-data" });
  //   return this.http.put(this.apiUrl + '/Assignment/' + assignmentData.id +"/updateAssignment", assignmentData, { headers: headers, withCredentials: true })
  // }
}
