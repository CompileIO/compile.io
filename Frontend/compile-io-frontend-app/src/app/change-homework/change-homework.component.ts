import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit, Input } from '@angular/core';
import { CourseService } from '../services/course.service';
import { AssignmentService } from '../services/assignment.service';
import { Assignment } from '../../models/assignment';
import { Time } from '@angular/common';

@Component({
  selector: 'app-change-homework',
  templateUrl: './change-homework.component.html',
  styleUrls: ['./change-homework.component.css']
})
export class ChangeHomeworkComponent implements OnInit {
  @Input() userName: string;
  @Input() className: string;
  @Input() hwkName: string;
  @Input() newHwk: boolean;
  name: string;
  timeout: string;
  language: string;
  assignmentInfo: FormData;
  Assignments: Assignment[];
  newAssignment: Assignment = new Assignment();
  file: File;
  // editing: boolean = false;
  // editingTodo: Assignment = new Assignment();

  constructor(private courseService: CourseService, private assignmentService: AssignmentService) {

  }

  fileUploadFunction(event: any) {
    this.file = event.target.files[0];
    // this.newAssignment.file = event.target.files[0];
    console.log("File was changed to this: " + this.file);
    this.assignmentService.uploadFile(this.file).subscribe({
      next: x => {
        console.log(x)
      },
      error: err => {
        console.log("ADDING FILE ERROR: " + err)
      },
      complete: () => console.log("Added File")
    });

  }

  getHomework() {
    this.courseService.getHomeworkInfo(this.className, this.hwkName).subscribe({
      next: x => this.newAssignment = x,
      error: err => console.log("GET HWK INFO ERROR: " + err),
      complete: () => console.log("got Hwk Info")
    });
    this.name = this.assignmentInfo.get("name").toString();
    this.timeout = this.assignmentInfo.get("timeout").toString();
    this.language = this.assignmentInfo.get("language").toString();
  }

  submit() {
    this.newAssignment.courseName = this.className;
    if (this.newHwk) {
      this.assignmentService.createAssignment(this.newAssignment).subscribe({
        next: x => {
          console.log(x)
        },
        error: err => {
          console.log("ADDING HWK ERROR: " + err)
        },
        complete: () => {
          this.newAssignment = new Assignment()
          console.log("Added Homework Complete")
        }
      });
    }
    else {
      this.assignmentService.updateAssignment(this.newAssignment).subscribe({
        next: x => {
          console.log(x)
        },
        error: err => {
          console.log("UPDATING HWK ERROR: " + err)
        },
        complete: () => {
          this.newAssignment = new Assignment()
          console.log("Updated Homework Complete")
        }
      });
    }
  }

  getAssignments(): void {
    this.assignmentService.getAssignments().subscribe({
      complete: () => assignments => this.Assignments = assignments
    });
  }

  ngOnInit() {
    this.getAssignments();
    console.log("newHwk: " + this.newHwk);
    if (this.newHwk !== undefined &&
      this.newHwk !== null &&
      this.newHwk != true) {
      this.getHomework();
    }
  }

}
