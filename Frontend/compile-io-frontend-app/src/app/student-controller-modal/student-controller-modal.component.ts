import { Component, OnInit, Input } from '@angular/core';
import { Section } from '../../models/section';
import { Student } from '../../models/student';
import { Code } from '../../models/code';
import { StudentService } from '../services/student.service';
import { AssignmentService } from '../services/assignment.service';
import { Angular5Csv } from 'angular5-csv/dist/Angular5-csv';
import { Assignment } from 'src/models/assignment';
// import {CSV} from 'angular5-csv'

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
  gradesData: any[];
  constructor(private studentService: StudentService, private assignmentService: AssignmentService) { }

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
    let data = [];
    var requests = 0;
    for (let i = 0; i < this.sectionInfo.studentUsernames.length; i++) {
      let studentIn = new Student;
      requests++;
      this.studentService.getStudentbyUsername(this.sectionInfo.studentUsernames[i]).subscribe({
        next: student => studentIn = student,
        complete: () => {
          requests--;
          for (let j = 0; j < this.sectionInfo.assignments.length; j++) {
              let json;
              if (studentIn.codes[j] != null && studentIn.codes[j].assignmentId == this.sectionInfo.assignments[j].id) {
                json = {
                  studentName: studentIn.name,
                  studentUserName: studentIn.userName,
                  assignmentName: this.sectionInfo.assignments[j].assignmentName,
                  Grade: "=" + studentIn.codes[j].grade + "%"
                }
              }
              else if(studentIn.codes[j] == null){
                json = {
                  studentName: studentIn.name,
                  studentUserName: studentIn.userName,
                  assignmentName: this.sectionInfo.assignments[j].assignmentName,
                  Grade: "Not Attempted"
                }
              }
              data.push(json);
          }
          if(requests == 0){
            console.log("This is data: " + JSON.stringify(data));
            var options = { 
              fieldSeparator: ',',
              showLabels: true, 
              showTitle: true,
              title: this.sectionInfo.year + "_" + this.sectionInfo.sectionNumber + "_" + this.sectionInfo.term + "_" + 'Grade_Report',
              headers: ["Student Name", "Student Username", "Assignment Name", "Grade"]
            };
            new Angular5Csv(data, this.sectionInfo.year + "_" + this.sectionInfo.sectionNumber + "_" + this.sectionInfo.term + "_" + 'Grade_Report', options);
          }
        }
      });
    }
  }

  ngOnInit() {
  }

}
