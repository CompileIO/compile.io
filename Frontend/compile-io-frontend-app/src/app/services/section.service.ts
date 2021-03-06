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

  getSection(sectionId: string): Observable<Section> {
    console.log("Get Course: " + sectionId);
    var response;
    response = this.http.get(this.apiUrl + "/Section/"  + sectionId);
    return response;
  }
  

  createSection(sectionData: Section): Observable<Section> {
    var response;
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    sectionData.id = null;
    console.log("Term: " + sectionData.term);
    console.log("Year: "  + sectionData.year);
    response = this.http.post(this.apiUrl + '/Section/Create' , sectionData, { headers: headers, withCredentials: true });
    return response
  }

  updateSection(sectionData: Section): Observable<Section> {
    const headers = new HttpHeaders({'Content-Type': 'application/json' });
    var response; 
    console.log("Term: " + sectionData.term);
    console.log("Year: "  + sectionData.year);
    response =  this.http.put(this.apiUrl + '/Section/Update/' + sectionData.id, sectionData, { headers: headers, withCredentials: true });
    return response;
  }

  deleteSection(id: string): Observable<String>{
    var response;
    response = this.http.delete(this.apiUrl + '/Section/Delete' + id, {withCredentials: true });
    return response;
  }
}
