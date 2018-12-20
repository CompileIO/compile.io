import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-class-info',
  templateUrl: './class-info.component.html',
  styleUrls: ['./class-info.component.css']
})
export class ClassInfoComponent implements OnInit {
  @Input() className: string;

  constructor() { }

  ngOnInit() {
  }

}
