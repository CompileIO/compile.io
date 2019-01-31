import { Component, OnInit, Input } from '@angular/core';
import { CourseService } from '../services/course.service';

@Component({
  selector: 'app-change-homework',
  templateUrl: './change-homework.component.html',
  styleUrls: ['./change-homework.component.css']
})
export class ChangeHomeworkComponent implements OnInit {

  @Input() username: string;
  @Input() givenClass: string;
  @Input() homework: string;
  name: string;
  timeout: string;
  visible: boolean;
  language: string;
  file: File;
  homeworkInfo: FormData;
  constructor(private courseService: CourseService, private testService: TestService) {}

  getHomework() {
    this.courseService.getHomeworkInfo(this.givenClass, this.homework).subscribe({
      next: x => this.homeworkInfo = x,
      error: err => console.log("GET CLASSES ERROR: " + err),
      complete: () => console.log("got classes")
    });
    this.name = this.homeworkInfo.get("name").toString();
    this.timeout = this.homeworkInfo.get("timeout").toString();
    //this.visible = this.homeworkInfo.get("visible");
    this.language = this.homeworkInfo.get("language").toString();
    //this.file = this.homeworkInfo.get("file");
  }

  submit(name: string, time: number, visible: boolean, file: File, language: string, size: number) {
    this.testService.uploadTest(file, language.toLowerCase(), time, name, this.givenClass, this.username, size).subscribe({
      next: x => {
        console.log(x)
      },
      error: err => {
        console.log("UPLOADING FILE ERROR: " + err)
      },
      complete: () => console.log("Uploaded file")
    });
  }

  ngOnInit() {
    this.getHomework();
  }

}
