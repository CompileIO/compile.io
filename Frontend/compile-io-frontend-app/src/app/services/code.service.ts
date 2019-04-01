import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';
import {Code} from '../../models/code';

@Injectable({
  providedIn: 'root'
})
export class CodeService {

  private apiUrl = environment.BackendapiUrl;
  constructor(private http: HttpClient) { }


  getCode(codeData: Code): Observable<Code>  {
    var response; 
    response = this.http.get(this.apiUrl + "/Code/"  + codeData.id);
    return response;
  }
  getCodes(): Observable<Code[]> {
    var response;
    response = this.http.get(this.apiUrl + '/Code');
    return response;
    
  }

  getCodesForSpecificAssignment(assignmentId: string, studentUsername: string): Observable<Code> { 
    var response;
    response = this.http.get(this.apiUrl + '/Code/getAssignment/' + assignmentId +'/' + studentUsername);
    return response;
  }

  getCodesForStudent(studentUsername: string): Observable<Code[]> { 
    var response;
    response = this.http.get(this.apiUrl + '/Code/getStudent/' + studentUsername);
    return response;
  }

  runCode(file: File, codeId: string): Observable<Code> {
    let body: FormData = new FormData();  
    body.append('file', file);
    body.append('codeId', codeId);
    const fileHeaders = new HttpHeaders({'enctype': "multipart/form-data"}); 
    var response;
    response = this.http.post(this.apiUrl + "/Code/runCode", body, {headers: fileHeaders, withCredentials: true});
    return response;
  }

  serveFile(filename: string, filepath: string) { //: Observable<Resource>
    let body: FormData = new FormData();
    body.append('filepath', filepath);
    const headers = new HttpHeaders({ 'Content-Type': 'application/json'  });
    var response; 
    response = this.http.post(this.apiUrl + "/Code/getFile/" + filename, body , {headers: headers, withCredentials: true});
    return response;
  }

  createCode(codeData: Code): Observable<Code> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json'  });
    var response;
    codeData.id = null;
    response = this.http.post(this.apiUrl + '/Code/Create' , codeData, { headers: headers, withCredentials: true });
    return response;
  }

  updateCode(codeData: Code): Observable<Code> {
    const headers = new HttpHeaders({'Content-Type': 'application/json'  });
    var response;
    response = this.http.put(this.apiUrl + '/Code/Update/' + codeData.id, codeData, { headers: headers, withCredentials: true });
    return response;
  }

  deleteCode(id: string): Observable<String>{
    var response;
    response = this.http.delete(this.apiUrl + '/Code/deleteCode' + id, {withCredentials: true });
    return response;
  }
}
