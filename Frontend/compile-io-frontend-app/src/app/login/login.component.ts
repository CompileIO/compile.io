import { Component, OnInit } from '@angular/core';
import 'rosefire';
// import { environment } from '../../environments/environment';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(
   
    private authenticationService: AuthenticationService,
    private router: Router
  ) {
  }

  ngOnInit() {
    if (this.authenticationService.hasToken()) {
      const group = sessionStorage.getItem('group');
      const username = sessionStorage.getItem('user');
      console.log(group);
      console.log(username);
      this.router.navigate([`/${group.toLowerCase()}/${username}`]);
    }
  }

  login() {
    this.authenticationService.login();
  }

  logout() {
    this.authenticationService.logout();
  }

}
