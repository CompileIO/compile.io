import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: string = null;
  finalUsername: string = null;
  loggedIn: boolean = false;

  constructor() { }
  submit() {
    this.finalUsername = this.username;
    this.loggedIn = true;
  }

  ngOnInit() {
  }

}
