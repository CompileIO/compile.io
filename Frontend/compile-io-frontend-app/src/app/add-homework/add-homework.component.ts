import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-add-homework',
  templateUrl: './add-homework.component.html',
  styleUrls: ['./add-homework.component.css']
})
export class AddHomeworkComponent implements OnInit {

  @Input() class: string;
  name: string;

  constructor() { }

  submit(name: string, time: number, visible: boolean, file: File) {
    console.log(name + ", " + time + ", " + visible + ", " + file);
  }
  ngOnInit() {
  }

}