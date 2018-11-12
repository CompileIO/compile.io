import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-class-button',
  templateUrl: './class-button.component.html',
  styleUrls: ['./class-button.component.css']
})
export class ClassButtonComponent implements OnInit {
  className = "";

  constructor(name: string) {
    this.className = name;
  }

  ngOnInit() {
  }

}
