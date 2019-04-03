import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import {Assignment} from '../../models/assignment';
import { Observable } from 'rxjs';
// import 'rxjs/Rx' ;
// import {RequestOptions, Request, RequestMethod, ResponseContentType} from '@angular/http';
// import { connectableObservableDescriptor } from 'rxjs/internal/observable/ConnectableObservable';

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
  
  uploadFile(file: File, assignmentId: string): Observable<Assignment> {
    let body: FormData = new FormData();  
    body.append('file', file);
    body.append('assignmentId', assignmentId);
    const fileHeaders = new HttpHeaders({'enctype': "multipart/form-data" }); 
    var response; 
    response = this.http.post(this.apiUrl + "/Assignmnet/uploadFile", body , {headers: fileHeaders, withCredentials: true});
    return response;
  }

  serveFile(filename: string, filepath: string) : Observable<Blob> {
    let body: FormData = new FormData();
    console.log(filepath);
    body.append('filepath', filepath);
    var response; 
    response = this.http.post(this.apiUrl + "/Assignment/getFile/" + filename, body);
    return response;
  }

  createAssignment(assignmentData: Assignment): Observable<Assignment[]> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json'  });
    // console.log("Inside the create Assignment Method: " + "\n" + 
    // "ID: " + assignmentData.id + "\n" +
    // "Created by user: " + assignmentData.createdByUsername + "\n" +
    // "Sections: " + assignmentData.sectionIds[0] + "\n" +
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

  updateAssignment(assignmentData: Assignment): Observable<Assignment[]> {
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
