import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-class-button',
  templateUrl: './class-button.component.html',
  styleUrls: ['./class-button.component.css']
})
export class ClassButtonComponent implements OnInit {
  className: String = '';

  constructor(name: String) {
    this.className = name;
  }

  ngOnInit() {
  }

}
