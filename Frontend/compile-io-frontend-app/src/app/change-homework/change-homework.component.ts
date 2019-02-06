import { Component, OnInit, Input } from '@angular/core';
import { CourseService } from '../services/course.service';
import { TestService } from '../services/test.service';

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
  hwkInfo: FormData;
  constructor(private courseService: CourseService, private testService: TestService) {
    
  }

  getHomework() {
    var stuff = [];
    this.courseService.getHomeworkInfo(this.className, this.hwkName).subscribe({
      next: x => this.hwkInfo = x,
      error: err => console.log("GET HWK INFO ERROR: " + err),
      complete: () => console.log("got Hwk Info")
    });
    this.name = this.hwkInfo.get("name").toString();
    this.timeout = this.hwkInfo.get("timeout").toString();
    //this.visible = this.hwkInfo.get("visible");
    this.language = this.hwkInfo.get("language").toString();
    //this.file = this.hwkInfo.get("file");
  }
  submit(name: string,
        timeout: number,
        visible: boolean,
        language: string,
        size: number,
        tries: number) {

    console.log("THIS IS THE FILE FROM SUBMIT: " + this.file);
    this.hwkInfo = new FormData();
    this.hwkInfo.append("name", name);
    this.hwkInfo.append("timeout", timeout.toString());
    this.hwkInfo.append("language", language);
    this.hwkInfo.append("size", size.toString());
    this.hwkInfo.append("tries", tries.toString());
    if (this.newHwk) {
      this.testService.addHwk(this.className, name, this.hwkInfo, this.file, visible, startDate, endDate).subscribe({
        next: x => {
          console.log(x)
        },
        error: err => {
          console.log("ADDING HWK ERROR: " + err)
        },
        complete: () => console.log("Added Hwk")
      });
    } else {
      this.testService.updateHwk(this.className, this.hwkName, name, this.hwkInfo, this.file, visible, startDate, endDate).subscribe({
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
