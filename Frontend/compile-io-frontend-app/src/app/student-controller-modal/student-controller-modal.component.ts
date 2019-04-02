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
  studentsToDelete: Student[];
  newStudent: Student;
  constructor() { }

  displaySectionStuff() {
    console.log(this.sectionInfo);
  }
  addNewStudent(temp1: string, temp2: string) {
    this.newStudent = new Student();
    this.newStudent.userName = temp1;
    this.newStudent.name = temp2;
    console.log(this.sectionInfo);
    this.sectionInfo.students.push(this.newStudent);
    console.log(this.newStudent);
  }

  ngOnInit() {
  }

}
