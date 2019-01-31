import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-change-homework',
  templateUrl: './change-homework.component.html',
  styleUrls: ['./change-homework.component.css']
})
export class ChangeHomeworkComponent implements OnInit {

  @Input() givenClass: string;
  @Input() homework: string;

  constructor() { }


  
  ngOnInit() {
  }

}
