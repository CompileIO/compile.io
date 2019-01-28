import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

export interface backendInterface {
  apiUrl: string;
}

@Injectable({
    providedIn: 'root'
  })
export abstract class backendInterfaceService {
  /**
   * Returns a list of all of the current user's todos.
   */
  apiUrl = environment.BackendapiUrl;

  constructor(protected http: HttpClient) { }

  getClasses(): Observable<any> {
    const empHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.get(this.apiUrl + "/classes", { headers: empHeaders, withCredentials: true });
  }
}