
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { Observable } from 'rxjs/internal/Observable';
import { environment } from '../../environments/environment';
import { Router } from '@angular/router';

@Injectable()
export class AuthenticationService {
  isLoginSubject = new BehaviorSubject<boolean>(this.hasToken());
  admins = ["palamujg", "thelenzr", "rileyma"];
  constructor(
    private router: Router,
    
  ) {
  }


  isLoggedIn(): Observable<boolean> {
    return this.isLoginSubject.asObservable();
  }

  hasToken(): boolean {
    return !!sessionStorage.getItem('token');
  }

  login(): void {
    Rosefire.signIn(environment.registryToken, (error, rfUser: RosefireUser) => {
      if (error) {
        console.error(error);
        sessionStorage.clear();
        this.isLoginSubject.next(false);
        return;
      } else {
        if (rfUser) {
          sessionStorage.setItem('user', rfUser.username);
          this.admins.forEach(element => {
            if(element == rfUser.username){
              rfUser.group = 'ADMIN';
              console.log(rfUser.username + " set to admin");
            }
          });
          sessionStorage.setItem('group', rfUser.group);
          sessionStorage.setItem('token', rfUser.token);
          // this.router.navigate([`/${rfUser.group.toLowerCase()}/${rfUser.username}`]);
          this.isLoginSubject.next(true);
        }
      }
      });
  }

  logout(): void {
    sessionStorage.removeItem('token');
    sessionStorage.removeItem('user');
    this.router.navigate(['/login']);

    this.isLoginSubject.next(false);
  }
}
