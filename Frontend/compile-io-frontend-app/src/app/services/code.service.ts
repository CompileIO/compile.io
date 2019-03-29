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

  getCodesForSpecificAssignment(assignmentId: string, studentUsername: string): Observable<Code[]> { 
    var response;
    response = this.http.get(this.apiUrl + '/Code/getAssignment/' + assignmentId +'/' + studentUsername);
    return response;
  }

  getCodesForStudent(studentUsername: string): Observable<Code[]> { 
    var response;
    response = this.http.get(this.apiUrl + '/Code/getStudent/' + studentUsername);
    return response;
  }

  uploadCode(type: string, runTime: number, givenAssignmentID: string, userName: string): Observable<Code> {
    let body: FormData = new FormData(); 
    body.append('type', type);
    body.append('username', userName);
    body.append('runTime', runTime.toString());
    body.append('assignmentID', givenAssignmentID.toString());
    const fileHeaders = new HttpHeaders({'Content-Type': 'application/json'  });
    var response;
    response = this.http.post(this.apiUrl + "/Code/uploadCode", body , {headers: fileHeaders, withCredentials: true});
    return response;
  }

  uploadFile(file: File, codeFilePath: string): Observable<String> {
    let body: FormData = new FormData();  
    body.append('file', file);
    body.append('filePath', codeFilePath);
    const fileHeaders = new HttpHeaders({'enctype': "multipart/form-data"}); 
    var response;
    response = this.http.post(this.apiUrl + "/Code/uploadFile", body, {headers: fileHeaders, withCredentials: true});
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

  deleteCode(id: string): Observable<String>{
    var response;
    response = this.http.delete(this.apiUrl + '/Code/deleteCode' + id, {withCredentials: true });
    return response;
  }
}
