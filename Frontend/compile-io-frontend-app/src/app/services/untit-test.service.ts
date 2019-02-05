import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UntitTestService {

  private apiUrl = environment.BackendapiUrl;
  constructor(private http: HttpClient) { }
}
