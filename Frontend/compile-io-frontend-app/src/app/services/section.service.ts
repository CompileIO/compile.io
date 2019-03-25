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


  getSections(): Observable<any>{
    return this.http.get(this.apiUrl + '/Sections')
  }

  getSection(sectionData: Section) {
    console.log("Get Course: " + sectionData.sectionNumber);
    return this.http.get(this.apiUrl + "/Section/"  + sectionData.id);
  }

  getSectionForGivenClass(sectionData: Section) {
    console.log("Get Course: " + sectionData.sectionNumber);
    return this.http.get(this.apiUrl + "/Section/"  + sectionData.id);
  }
  

  createSection(sectionData: Section): Observable<Section> {
    var response;
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    response = this.http.post(this.apiUrl + '/Section/Create' , sectionData, { headers: headers, withCredentials: true });
    return response
  }

  updateSection(sectionData: Section) {
    const headers = new HttpHeaders({'enctype': "multipart/form-data" });
    // for(var i = 0; i < sectionData.students.length; i++) {
    //   sectionData.students[i].sections.push(sectionData);
      
    // }
    return this.http.put(this.apiUrl + '/Section/Update/' + sectionData.id, sectionData, { headers: headers, withCredentials: true })
  }

  deleteSection(id: string){
    return this.http.delete(this.apiUrl + '/Section/Delete' + id, {withCredentials: true })
  }
}
