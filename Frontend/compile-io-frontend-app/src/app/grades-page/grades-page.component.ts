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

  constructor() { }

  selectCode(assign: Assignment) {
    this.studentInfo.codes.forEach(cd => {
      if (cd.assignmentId = assign.id) {
        this.selectedCode = cd;
      }
    });
  }

  unselectCode() {
    this.selectedCode = null;
  }

  returnCode(assign: Assignment): Code {
    this.studentInfo.codes.forEach(cd => {
      if (cd.assignmentId = assign.id) {
        return cd;
      }
    });
    return null;
  }

  ngOnInit() {
    this.selectedCode = null;
  }

}
