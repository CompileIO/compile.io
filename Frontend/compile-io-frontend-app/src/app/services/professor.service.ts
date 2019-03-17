import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import { Professor } from 'src/models/professor';

@Injectable({
  providedIn: 'root'
})
export class ProfessorService {

  private apiUrl = environment.BackendapiUrl;
  constructor(private http: HttpClient) { }

  
  // getProfessors(): Observable<any> {
  //   const empHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
  //   return this.http.get(this.apiUrl + "/Professors", { headers: empHeaders, withCredentials: true });
  // }

  getProfessors(): Observable<Professor[]>{
    var response;
    response = this.http.get(this.apiUrl + '/Professors');
    return response;
  }

  getProfessor(professorData: Professor) {
    console.log("Get Professor: " + professorData.name);
    return this.http.get(this.apiUrl + "/Professor/"  + professorData.id);
  }

  getProfessorbyUsername(username: string): Observable<any> {
    return this.http.get(this.apiUrl + "/Professor/Username/" + username);
  }
  
  createProfessor(professorData: Professor) {
    const headers = new HttpHeaders({ 'enctype': "multipart/form-data" });
    return this.http.post(this.apiUrl + '/Professor/Create' , professorData, { headers: headers, withCredentials: true })
  }

  updateProfessor(professorData: Professor): Observable<any> {
    const headers = new HttpHeaders({'enctype': "multipart/form-data" });
    return this.http.put(this.apiUrl + '/Professor/Update/' + professorData.id, professorData, { headers: headers, withCredentials: true })
  }

  deleteProfessor(id: string){
    return this.http.delete(this.apiUrl + '/Professor/Delete' + id, {withCredentials: true })
  }
}
