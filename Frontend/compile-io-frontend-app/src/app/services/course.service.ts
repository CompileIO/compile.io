import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Course } from 'src/models/course';

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  private apiUrl = environment.BackendapiUrl;
  constructor(private http: HttpClient) { }

  
  // getCourses(): Observable<any> {
  //   const empHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  //   return this.http.get(this.apiUrl + "/courses", { headers: empHeaders, withCredentials: true });
  // }

  getCourses(): Observable<Course[]>{
    var response;
    response = this.http.get(this.apiUrl + '/Courses');
    return response;
  }

  getCourse(courseData: Course): Observable<Course> {
    console.log("Get Course: " + courseData.courseName);
    var response; 
    response = this.http.get(this.apiUrl + "/Course/"  + courseData.id);
    return response;
  }
  

  createCourse(courseData: Course): Observable<Course> {
    const headers = new HttpHeaders({ 'enctype': "multipart/form-data" });
    var response;
    response = this.http.post(this.apiUrl + '/Course/Create' , courseData, { headers: headers, withCredentials: true });
    return response;
  }

  updateCourse(courseData: Course): Observable<Course> {
    const headers = new HttpHeaders({'enctype': "multipart/form-data" });
    var response;
    response = this.http.put(this.apiUrl + '/Course/Update/' + courseData.id, courseData, { headers: headers, withCredentials: true });
    return response;
  }

  deleteCourse(id: string): Observable<String>{
    var response;
    response = this.http.delete(this.apiUrl + '/Course/Delete' + id, {withCredentials: true });
    return response;
  }
}
