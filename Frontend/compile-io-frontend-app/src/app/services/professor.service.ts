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

  getProfessor(professorData: Professor): Observable<Professor> {
    console.log("Get Professor: " + professorData.name);
    var response;
    response = this.http.get(this.apiUrl + "/Professor/"  + professorData.id);
    return response;
  }

  getProfessorbyUsername(username: string): Observable<Professor> {
    var response;
    response =  this.http.get(this.apiUrl + "/Professor/Username/" + username);
    return response;
  }
  
  createProfessor(professorData: Professor) : Observable<Professor> {
    var response;
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    response = this.http.post(this.apiUrl + '/Professor/Create' , professorData, { headers: headers, withCredentials: true })
    return response;
  }

  updateProfessor(professorData: Professor): Observable<Professor> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'  });
    var response;
    response = this.http.put(this.apiUrl + '/Professor/Update/' + professorData.id, professorData, { headers: headers, withCredentials: true });
    return response;
  }

  deleteProfessor(id: string): Observable<String>{
    var response;
    response =  this.http.delete(this.apiUrl + '/Professor/Delete' + id, {withCredentials: true });
    return response;
  }
}
