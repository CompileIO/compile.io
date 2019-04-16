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

  getCourses(): Observable<Course[]>{
    var response;
    response = this.http.get(this.apiUrl + '/Courses');
    return response;
  }

  getCourse(courseId: string): Observable<Course> {
    console.log("Get Course: " + courseId);
    var response; 
    response = this.http.get(this.apiUrl + "/Course/"  + courseId);
    return response;
  }
  

  createCourse(courseData: Course): Observable<Course> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json'  });
    var response;
    courseData.id = null;
    response = this.http.post(this.apiUrl + '/Course/Create' , courseData, { headers: headers, withCredentials: true });
    return response;
  }

  updateCourse(courseData: Course): Observable<Course> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'  });
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
