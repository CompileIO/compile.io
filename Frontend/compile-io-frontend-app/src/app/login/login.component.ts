import { Component, OnInit } from '@angular/core';
import 'rosefire';
import { AuthenticationService } from '../services/authentication.service';
import { interval } from 'rxjs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loggedIn: boolean;
  finalUsername: string;
  finalGroup: string;
  
  constructor(
    private authenticationService: AuthenticationService
  ) {

  }
  
  ngOnInit() {
    this.checkLogin();
    interval(1000).subscribe(x => {
      if (!this.loggedIn) {
        this.checkLogin();
      }
    });
  }

  checkLogin() {
    if (this.authenticationService.hasToken()) {
      const group = sessionStorage.getItem('group');
      const username = sessionStorage.getItem('user');
      this.finalGroup = group;
      this.finalUsername = username;
      this.loggedIn = true;
    }
  }

  login() {
    this.authenticationService.login();
  }

  logout() {
    this.authenticationService.logout();
  }

}
