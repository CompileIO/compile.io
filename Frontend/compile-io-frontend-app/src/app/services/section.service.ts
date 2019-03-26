import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Section } from 'src/models/section';

@Injectable({
  providedIn: 'root'
})
export class SectionService {
  private apiUrl = environment.BackendapiUrl;
  constructor(private http: HttpClient) { }


  getSections(): Observable<Section[]>{
    var response;
    response = this.http.get(this.apiUrl + '/Sections');
    return response;
  }

  getSection(sectionData: Section): Observable<Section> {
    console.log("Get Course: " + sectionData.sectionNumber);
    var response;
    response = this.http.get(this.apiUrl + "/Section/"  + sectionData.id);
    return response;
  }

  getSectionForGivenClass(sectionData: Section): Observable<Section> {
    console.log("Get Course: " + sectionData.sectionNumber);
    var response;
    response = this.http.get(this.apiUrl + "/Section/"  + sectionData.id);
    return response;
  }
  

  createSection(sectionData: Section): Observable<Section> {
    var response;
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    response = this.http.post(this.apiUrl + '/Section/Create' , sectionData, { headers: headers, withCredentials: true });
    return response
  }

  updateSection(sectionData: Section): Observable<Section> {
    const headers = new HttpHeaders({'Content-Type': 'application/json' });
    // for(var i = 0; i < sectionData.students.length; i++) {
    //   sectionData.students[i].sections.push(sectionData);
      
    // }
    var response; 
    response =  this.http.put(this.apiUrl + '/Section/Update/' + sectionData.id, sectionData, { headers: headers, withCredentials: true });
    return response;
  }

  deleteSection(id: string): Observable<String>{
    var response;
    response = this.http.delete(this.apiUrl + '/Section/Delete' + id, {withCredentials: true });
    return response;
  }
}
