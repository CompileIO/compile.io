import { Component, OnInit, Input } from '@angular/core';
import { Student } from '../../models/student';
import { Section } from '../../models/section';
import { Code } from '../../models/code';
import { Assignment } from '../../models/assignment';

@Component({
  selector: 'app-grades-page',
  templateUrl: './grades-page.component.html',
  styleUrls: ['./grades-page.component.css']
})
export class GradesPageComponent implements OnInit {

  @Input() studentInfo: Student;
  @Input() sectionInfo: Section;
  selectedCode: Code;
  Codes:Code;

  constructor() { }

  selectCode(assign: Assignment) {
    this.studentInfo.codes.forEach(cd => {
      if (cd.assignmentId == assign.id) {
        console.log(cd);
        this.selectedCode = cd;
      }
    });
  }

  unselectCode() {
    this.selectedCode = null;
  }

  returnCode(assign: Assignment): Code {
    let toReturn: Code;
    let i = 0;
    for (i = 0; i < this.studentInfo.codes.length; i ++) {
      if (this.studentInfo.codes[i] != null && this.studentInfo.codes[i].assignmentId == assign.id) {
        toReturn = this.studentInfo.codes[i];
        return toReturn;
      }
    }
    toReturn = new Code();
    toReturn.grade = "No Grade";
    return toReturn;
  }

  ngOnInit() {
    this.selectedCode = null;
  }

}
