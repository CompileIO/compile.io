import { Component, OnInit, Input } from '@angular/core';
import { CourseService } from '../services/course.service';
import { AssignmentService } from '../services/assignment.service';

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
  constructor(private courseService: CourseService, private assignmentService: AssignmentService) {
    
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
    //this.file = this.assignmentInfo.get("file");
  }
  submit(name: string,
        timeout: number,
        visible: boolean,
        language: string,
        size: number,
        tries: number,
        startDate: Date,
        endDate: Date) {

    console.log("THIS IS THE FILE FROM SUBMIT: " + this.file);
    this.assignmentInfo = new FormData();
    this.assignmentInfo.append("newAssignmentname", name);
    this.assignmentInfo.append("timeout", timeout.toString());
    this.assignmentInfo.append("language", language);
    this.assignmentInfo.append("size", size.toString());
    this.assignmentInfo.append("tries", tries.toString());
    this.assignmentInfo.append("coursename", this.className);
      this.assignmentInfo.append("file", this.file);
    if (this.newHwk) {
      this.assignmentService.addAssignment(this.assignmentInfo, startDate, endDate).subscribe({
        next: x => {
          console.log(x)
        },
        error: err => {
          console.log("ADDING HWK ERROR: " + err)
        },
        complete: () => console.log("Added Hwk")
      });
    } else {
      this.assignmentInfo.append("oldAssignmentName", this.hwkName);
      this.assignmentService.updateAssignment(this.assignmentInfo, startDate, endDate).subscribe({
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

  ngOnInit() {
    console.log("newHwk: " + this.newHwk);
    if (this.newHwk !== undefined &&
      this.newHwk !== null &&
      this.newHwk != true) {
      this.getHomework();
    }
  }

}
