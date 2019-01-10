import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse,
} from '@angular/common/http';
import { AuthGuard } from './authguard.service';
import { Observable } from 'rxjs/internal/Observable';
@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(public auth: AuthGuard) {}
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const token = this.auth.getToken();
    if (!token ) {
      return next.handle(request);
    }

    const authReq = request.clone({
        headers: request.headers.set(
            'Authorization',
            token)
    });
    return next.handle(authReq);
  }
}
