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
  name: string;
  timeout: string;
  language: string;
  @Input() assignmentInfo: Assignment;
  Assignments: Assignment[];
  newAssignment: Assignment = new Assignment();
  file: File;
  // editing: boolean = false;
  // editingTodo: Assignment = new Assignment();

  constructor(private courseService: CourseService, private assignmentService: AssignmentService) {

  }

  fileUploadFunction(event: any) {
    this.file = event.target.files[0];
    console.log("File was changed to this: " + this.file);
    this.assignmentService.uploadFile(this.file, this.className, this.newAssignment.assignmentName, this.userName).subscribe({
      next: x => {
        console.log(x)
      },
      error: err => {
        console.log("ADDING FILE ERROR: " + err)
      },
      complete: () => console.log("Added File")
    });

  }

  submit() {
    this.newAssignment.courseName = this.className;
    this.newAssignment.createdByUsername = this.userName;
    console.log(this.newAssignment.startTime);
    if (this.assignmentInfo.id == "-1") {
      
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
  updateSize(givenSize: number) {
    this.newAssignment.size = givenSize * 1000 * 1000;
  }

  readyToSubmit(): boolean {
    if (this.assignmentInfo.id == '-1') {
      if (this.newAssignment.assignmentName == undefined ||
        this.newAssignment.endDate == undefined ||
        this.newAssignment.endTime == undefined ||
        this.newAssignment.startDate == undefined ||
        this.newAssignment.startDate == undefined ||
        this.newAssignment.size == undefined ||
        this.newAssignment.language == undefined ||
        this.newAssignment.timeout == undefined ||
        this.newAssignment.tries == undefined ||
        this.file == undefined) {
        return false;
      }
    }
    return true;
  }

  ngOnInit() {
  }

}
