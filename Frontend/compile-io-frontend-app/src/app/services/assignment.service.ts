import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import {Assignment} from '../../models/assignment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AssignmentService {

  private apiUrl = environment.BackendapiUrl;
  constructor(private http: HttpClient) {}

  getAssignment(assignmentData: Assignment): Observable<Assignment> {
    var response;
    console.log("Get assignment: " + assignmentData.assignmentName);
    response = this.http.get(this.apiUrl + "/Assignment/"  + assignmentData.id)
    return response;
  }
  getAssignments(): Observable<Assignment[]> {
    var response; 
    response = this.http.get(this.apiUrl + '/Assignment');
    return response;
    
  }

  getAssignmentsForSpecificCourse(courseName: string): Observable<Assignment[]> { 
    var response;
    response = this.http.get(this.apiUrl + '/Assignment/getCourse/' + courseName);
    return response;
  }
  uploadFile(file: File, className: string, assignmentName: string, userName: string): Observable<String> {
    let body: FormData = new FormData();  
    body.append('file', file);
    body.append('courseName', className);
    body.append('assignmentName', assignmentName);
    body.append('userName', userName);
    const fileHeaders = new HttpHeaders({'enctype': "multipart/form-data" }); 
    var response; 
    response = this.http.post(this.apiUrl + "/Assignmnet/uploadFile", body , {headers: fileHeaders, withCredentials: true});
    return response;
  }

  createAssignment(assignmentData: Assignment): Observable<Assignment> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json'  });
    // console.log("Inside the create Assignment Method: " + "\n" + 
    // "ID: " + assignmentData.id + "\n" +
    // "Course Name: " + assignmentData.courseName + "\n" +
    // "Assignment Name: " + assignmentData.assignmentName + "\n" +
    // "Timeout: " + assignmentData.timeout + "\n" +
    // "language: " + assignmentData.language + "\n" +
    // "Size: " + assignmentData.size + "\n" +
    // "tries: " + assignmentData.tries + "\n" +
    // "start date: " + assignmentData.startDate + "\n" +
    // "start time: " + assignmentData.startTime + "\n" +
    // "end date: " + assignmentData.endDate + "\n" +
    // "end time: " + assignmentData.endTime);
    var response;
    response =  this.http.post(this.apiUrl + '/Assignment/Create' , assignmentData, { headers: headers, withCredentials: true });
    return response;
  }

  updateAssignment(assignmentData: Assignment): Observable<Assignment> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'  });
    var response;
    response = this.http.put(this.apiUrl + '/Assignment/Update/' + assignmentData.id, assignmentData, { headers: headers, withCredentials: true });
    return response;
  }

  deleteAssignment(id: string): Observable<String>{
    var response;
    response = this.http.delete(this.apiUrl + '/Assignment/Delete' + id, {withCredentials: true });
    return response;
  }

}
