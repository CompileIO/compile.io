import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import {Assignment} from '../../models/assignment';

@Injectable({
  providedIn: 'root'
})
export class AssignmentService {

  private apiUrl = environment.BackendapiUrl;
  constructor(private http: HttpClient) {}

  getAssignment(assignmentData: Assignment) {
    return this.http.get(this.apiUrl + "/Assignment/"  + assignmentData.id);
  }
  getAssignments() {
    return this.http.get(this.apiUrl + '/Assignment')
    
  }

  createAssignment(assignmentData: Assignment) {
    const headers = new HttpHeaders({ 'enctype': "multipart/form-data" });
    console.log("Inside the create Assignment Method" + assignmentData.startTime);
    return this.http.post(this.apiUrl + '/Assignment' , assignmentData, { headers: headers, withCredentials: true })
  }

  updateAssignment(assignmentData: Assignment) {
    const headers = new HttpHeaders({'enctype': "multipart/form-data" });
    return this.http.put(this.apiUrl + '/Assignment/' + assignmentData.id +"/updateAssignment", assignmentData, { headers: headers, withCredentials: true })
  }

  deleteAssignment(id: string){
    return this.http.delete(this.apiUrl + '/Assignment/' + id, {withCredentials: true })
  }

}
