import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Student } from 'src/models/student';

@Injectable({
  providedIn: 'root'
})
export class StudentService {
  private apiUrl = environment.BackendapiUrl;
  constructor(private http: HttpClient) { }

  getStudents(): Observable<Student[]>{
    var response;
    response = this.http.get(this.apiUrl + '/Students');
    return response;
  }

  getStudent(studentData: Student): Observable<Student> {
    console.log("Get Student: " + studentData.name);
    var response;
    response = this.http.get(this.apiUrl + "/Student/"  + studentData.id);
    return response;
  }

  getStudentbyUsername(username: string): Observable<Student> {
    var response;
    response =  this.http.get(this.apiUrl + "/Student/Username/" + username);
    return response;
  }
  
  createStudent(studentData: Student): Observable<Student> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json'  });
    var response;
    response = this.http.post(this.apiUrl + '/Student/Create' , studentData, { headers: headers, withCredentials: true });
    return response;
  }

  updateStudent(studentData: Student): Observable<Student> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'  });
    var response;
    response = this.http.put(this.apiUrl + '/Student/Update/' + studentData.id, studentData, { headers: headers, withCredentials: true });
    return response;
  }

  deleteStudent(id: string):  Observable<string>{
    var response;
    response = this.http.delete(this.apiUrl + '/Student/Delete' + id, {withCredentials: true });
    return response;
  }
}
