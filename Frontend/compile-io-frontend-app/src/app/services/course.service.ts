import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { BrowserDynamicTestingModule } from '@angular/platform-browser-dynamic/testing';
import { Course } from 'src/models/course';

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

  // getCourses() {
  //   return this.http.get(this.apiUrl + '/Courses')
    
  // }

  getCourse(courseData: Course) {
    console.log("Get Course: " + courseData.courseName);
    return this.http.get(this.apiUrl + "/Course/"  + courseData.id);
  }
  

  createCourse(courseData: Course) {
    const headers = new HttpHeaders({ 'enctype': "multipart/form-data" });
    return this.http.post(this.apiUrl + '/Course/Create' , courseData, { headers: headers, withCredentials: true })
  }

  updateCourse(courseData: Course) {
    const headers = new HttpHeaders({'enctype': "multipart/form-data" });
    return this.http.put(this.apiUrl + '/Course/Update/' + courseData.id, courseData, { headers: headers, withCredentials: true })
  }

  deleteCourse(id: string){
    return this.http.delete(this.apiUrl + '/Course/Delete' + id, {withCredentials: true })
  }
}
