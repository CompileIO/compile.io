import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit, Input } from '@angular/core';
import { CourseService } from '../services/course.service';
import { AssignmentService } from '../services/assignment.service';
import { Assignment } from '../../models/assignment';
import { Time } from '@angular/common';
import { Section } from 'src/models/section';
import { Course } from 'src/models/course';


@Component({
  selector: 'app-change-homework',
  templateUrl: './change-homework.component.html',
  styleUrls: ['./change-homework.component.css']
})
export class ChangeHomeworkComponent implements OnInit {
  @Input() username: string;
  @Input() selectedCourse: Course;
  @Input() selectedSection: Section;
  @Input() assignmentInfo: Assignment;
  givenStartDate: any;
  givenEndDate: any;
  addedAssignments: Assignment[];
  newAssignment: Assignment;
  file: File;
  
  constructor(private assignmentService: AssignmentService) {
  }

  submitForm(form: any): void {
    console.log('Form Data: ');
    this.newAssignment.startDate = form.startDate;
    this.newAssignment.endDate = form.endDate;
    console.log(this.newAssignment);
    this.submit();
  }

  fileUploadFunction(event: any) {
    this.file = event.target.files[0];
    console.log("File was changed to this: " + this.file);
  }

  sendFile(assignment: Assignment) {
    console.log("This is the filepath sent to server: " + assignment.filePath);
      this.assignmentService.uploadFile(this.file, assignment.filePath).subscribe({
        next: x => {
          console.log(x)
        },
        // error: err => {
        //   console.log("ADDING FILE ERROR: " + err)
        // },
        complete: () => console.log("Added File")
      });
  }

  submit() {
    this.newAssignment.createdByUsername = this.username;
    this.newAssignment.sectionIds = [];
    if(this.assignmentInfo.availableToOtherSections) {
      this.newAssignment.courseId = this.selectedCourse.id;
      this.selectedCourse.sections.forEach( section => {
        this.newAssignment.sectionIds.push(section.id);
      });
    } else {
      this.newAssignment.courseId = null;
      this.newAssignment.sectionIds.push(this.selectedSection.id);
    }

    if (this.assignmentInfo.id == "-1") {
      this.assignmentService.createAssignment(this.newAssignment).subscribe({
        next: x => {
          this.addedAssignments = x;
          
        },
        error: err => {
          console.log("ADDING HWK ERROR: " + err)
        },
        complete: () => {
          this.addedAssignments.forEach(assignment => {
            console.log("filepath in the complete: "+ assignment.filePath);
            this.sendFile(assignment);
          });
          this.newAssignment = new Assignment()
          console.log("Added Homework Complete")
        }
      });
    }
    else {
      this.newAssignment.id = this.assignmentInfo.id;
      this.assignmentService.updateAssignment(this.newAssignment).subscribe({
        next: x => {
          this.addedAssignments = x;
        },
        error: err => {
          console.log("UPDATING HWK ERROR: " + err)
        },
        complete: () => {
          this.addedAssignments.forEach(  assignment => {
            this.sendFile(assignment);
          });
          this.newAssignment = new Assignment()
          console.log("Updated Homework Complete")
        }
      });
    }
    //window.location.reload();
  }
  updateSize(givenSize: number) {
    this.newAssignment.size = givenSize * 1000 * 1000;
  }

  ngOnInit() {
    this.newAssignment = new Assignment();
    if(this.assignmentInfo.id != '-1'){
      // Why can't we just say this.newAssignment = this.assignmentInfo
      this.newAssignment = this.assignmentInfo;
      // this.newAssignment.assignmentName = this.assignmentInfo.assignmentName;
      // this.newAssignment.endDate = this.assignmentInfo.endDate;
      // this.newAssignment.endTime = this.assignmentInfo.endTime;
      // this.newAssignment.startDate = this.assignmentInfo.startDate;
      // this.newAssignment.startTime = this.assignmentInfo.startTime;
      // this.newAssignment.size = this.assignmentInfo.size;
      // this.newAssignment.language = this.assignmentInfo.language;
      // this.newAssignment.timeout = this.assignmentInfo.timeout;
      // this.newAssignment.tries = this.assignmentInfo.tries;
      // this.newAssignment.createdByUsername = this.userName;

      this.givenStartDate = this.newAssignment.startDate.toString().substring(0, this.newAssignment.startDate.toString().indexOf("T"));
      this.givenEndDate = this.newAssignment.endDate.toString().substring(0, this.newAssignment.endDate.toString().indexOf("T"));
      console.log(this.givenStartDate);
    }
  }

}
