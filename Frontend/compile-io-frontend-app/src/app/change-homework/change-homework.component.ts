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
  @Input() assignmentInfo: Assignment;
  givenStartDate: any;
  givenEndDate: any;
  //Assignments: Assignment[];
  newAssignment: Assignment;
  file: File;

  constructor(private courseService: CourseService, private assignmentService: AssignmentService) {
    this.newAssignment = new Assignment();
  }

  submitForm(form: any): void {
    console.log('Form Data: ');
    this.newAssignment.startDate = form.startDate;
    this.newAssignment.endDate = form.endDate;
    console.log(this.newAssignment);
    sessionStorage.setItem('course', this.className);
    this.submit();
  }

  fileUploadFunction(event: any) {
    this.file = event.target.files[0];
    console.log("File was changed to this: " + this.file);
  }

  sendFile() {
    if (this.assignmentInfo.id == "-1") {
      this.assignmentService.uploadFile(this.file, this.className, this.newAssignment.assignmentName, this.userName).subscribe({
        next: x => {
          console.log(x)
        },
        error: err => {
          console.log("ADDING FILE ERROR: " + err)
        },
        complete: () => console.log("Added File")
      });
    } else {
      if (this.newAssignment.assignmentName == undefined) {
        this.assignmentService.uploadFile(this.file, this.className, this.newAssignment.assignmentName, this.userName).subscribe({
          next: x => {
            console.log(x)
          },
          error: err => {
            console.log("ADDING FILE ERROR: " + err)
          },
          complete: () => console.log("Added File")
        });
      } else {
        this.assignmentService.uploadFile(this.file, this.className, this.assignmentInfo.assignmentName, this.userName).subscribe({
          next: x => {
            console.log(x)
          },
          error: err => {
            console.log("ADDING FILE ERROR: " + err)
          },
          complete: () => console.log("Added File")
        });
      }
      
    }
    
  }

  submit() {
    this.newAssignment.courseName = this.className;
    this.newAssignment.createdByUsername = this.userName;
    //console.log(this.newAssignment.startTime);

    if (this.file != null) {
      this.sendFile();
    }

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
      this.newAssignment.id = this.assignmentInfo.id;
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
    window.location.reload();
  }
  updateSize(givenSize: number) {
    this.newAssignment.size = givenSize * 1000 * 1000;
  }

  ngOnInit() {
    this.newAssignment = new Assignment();
    if(this.assignmentInfo.id != '-1'){
      this.newAssignment.assignmentName = this.assignmentInfo.assignmentName;
      this.newAssignment.endDate = this.assignmentInfo.endDate;
      this.newAssignment.endTime = this.assignmentInfo.endTime;
      this.newAssignment.startDate = this.assignmentInfo.startDate;
      this.newAssignment.startTime = this.assignmentInfo.startTime;
      this.newAssignment.size = this.assignmentInfo.size;
      this.newAssignment.language = this.assignmentInfo.language;
      this.newAssignment.timeout = this.assignmentInfo.timeout;
      this.newAssignment.tries = this.assignmentInfo.tries;
      this.givenStartDate = this.newAssignment.startDate.toString().substring(0, this.newAssignment.startDate.toString().indexOf("T"));
      this.givenEndDate = this.newAssignment.endDate.toString().substring(0, this.newAssignment.endDate.toString().indexOf("T"));
      console.log(this.givenStartDate);
    }
  }

}
