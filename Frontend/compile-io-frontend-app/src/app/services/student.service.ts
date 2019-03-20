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

  getStudent(studentData: Student) {
    console.log("Get Student: " + studentData.name);
    return this.http.get(this.apiUrl + "/Student/"  + studentData.id);
  }

  getStudentbyUsername(username: string): Observable<any> {
    return this.http.get(this.apiUrl + "/Student/Username/" + username);
  }
  
  createStudent(studentData: Student) {
    const headers = new HttpHeaders({ 'enctype': "multipart/form-data" });
    return this.http.post(this.apiUrl + '/Student/Create' , studentData, { headers: headers, withCredentials: true })
  }

  updateStudent(studentData: Student): Observable<any> {
    const headers = new HttpHeaders({'enctype': "multipart/form-data" });
    return this.http.put(this.apiUrl + '/Student/Update/' + studentData.id, studentData, { headers: headers, withCredentials: true })
  }

  deleteStudent(id: string){
    return this.http.delete(this.apiUrl + '/Student/Delete' + id, {withCredentials: true })
  }
}
