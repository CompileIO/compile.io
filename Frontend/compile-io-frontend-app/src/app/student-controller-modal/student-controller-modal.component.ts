import { Component, OnInit, Input } from '@angular/core';
import { Section } from '../../models/section';
import { Student } from '../../models/student';

@Component({
  selector: 'app-student-controller-modal',
  templateUrl: './student-controller-modal.component.html',
  styleUrls: ['./student-controller-modal.component.css']
})
export class StudentControllerModalComponent implements OnInit {

  @Input() sectionInfo: Section;
  studentUsernamesToDelete: String[];
  constructor() { }

  displaySectionStuff() {
    console.log(this.sectionInfo);
  }
  addNewStudent(username: string) {
    this.sectionInfo.studentUsernames.push(username);
    console.log(username);
  }

  ngOnInit() {
  }

}
