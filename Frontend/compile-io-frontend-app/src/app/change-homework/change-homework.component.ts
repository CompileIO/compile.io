import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit, Input} from '@angular/core';
import { CourseService } from '../services/course.service';
import { AssignmentService } from '../services/assignment.service';
import {Assignment} from '../../models/assignment';

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
  file: File;
  assignmentInfo: FormData;
  Assignments: Assignment[];
  newAssignment: Assignment = new Assignment();
  editing: boolean = false;
  editingTodo: Assignment = new Assignment();

  constructor(private courseService: CourseService, private assignmentService: AssignmentService) {
    
  }

  fileUpload(event: any) {
    this.file = event.target.files[0];
  }

  getHomework() {
    var stuff = [];
    this.courseService.getHomeworkInfo(this.className, this.hwkName).subscribe({
      next: x => this.assignmentInfo = x,
      error: err => console.log("GET HWK INFO ERROR: " + err),
      complete: () => console.log("got Hwk Info")
    });
    this.name = this.assignmentInfo.get("name").toString();
    this.timeout = this.assignmentInfo.get("timeout").toString();
    //this.visible = this.assignmentInfo.get("visible");
    this.language = this.assignmentInfo.get("language").toString();
  }

  submit(name: string,
        timeout: number,
        language: string,
        size: number,
        tries: number,
        startDate: Date,
        endDate: Date) {
        this.newAssignment.courseName = this.name
        this.newAssignment.oldAssignmentName = this.className
        this.newAssignment.timeout = timeout
        this.newAssignment.language = language
        this.newAssignment.size = size
        this.newAssignment.tries = tries
        this.newAssignment.file = this.file
        this.newAssignment.startDate = startDate
        this.newAssignment.endDate = endDate

        console.log("These are the submitted values: \n name:" + name + "\n" + 
        "Timeout: " +  timeout.toString() + "\n" + 
        "language" +  language + "\n" +  
        "Size" + size.toString() + "\n" + 
        "tries" +  tries.toString() + "\n" + 
        "startDate"+  startDate.toString() + "\n" + 
        "endDate"+ endDate.toString());
    if (this.newHwk) {
      this.assignmentService.createAssignment(this.newAssignment).subscribe({
        next: x => {
          console.log(x)
        },
        error: err => {
          console.log("ADDING HWK ERROR: " + err)
        },
        complete: () => console.log("Added Hwk")
      });
    } else {
      this.newAssignment.newAssignmentName = name
      this.assignmentService.updateAssignment(this.newAssignment).subscribe({
        next: x => {
          console.log(x)
        },
        error: err => {
          console.log("UPDATING HWK ERROR: " + err)
        },
        complete: () => console.log("Updated Hwk")
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
