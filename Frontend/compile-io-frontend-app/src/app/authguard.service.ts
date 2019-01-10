import { CanActivate, Router } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router) {}

    public getToken(): string {
      return sessionStorage.getItem('token');
    }

    canActivate(): boolean {
      if (!this.getToken()) {
        this.router.navigate([ '/login' ]);
        return false;
      }
      return true;
    }
}
