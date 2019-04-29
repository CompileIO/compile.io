import { Component, OnInit, Input } from '@angular/core';
import { Section } from '../../models/section';
import { Student } from '../../models/student';
import { StudentService } from '../services/student.service';

@Component({
  selector: 'app-student-controller-modal',
  templateUrl: './student-controller-modal.component.html',
  styleUrls: ['./student-controller-modal.component.css']
})
export class StudentControllerModalComponent implements OnInit {

  @Input() sectionInfo: Section;
  studentInfo: Student[];
  studentUsernamesToDelete: String[];
  selectedStudent: Student = null;
  constructor(private studentService: StudentService) { }

  addNewStudent(username: string) {
    this.sectionInfo.studentUsernames.push(username);
    console.log(username);
  }

  removeStudent(username: string) {
    this.sectionInfo.studentUsernames.splice(this.sectionInfo.studentUsernames.indexOf(username), 1);
  }

  selectStudent(student: string) {
    this.studentService.getStudentbyUsername(student).subscribe({
      next: stud => this.selectedStudent = stud
    });
  }

  unselectStudent() {
    this.selectedStudent = null;
  }

exportGrades() {
  console.log("please write this josh");
}

  ngOnInit() {
  }

}
