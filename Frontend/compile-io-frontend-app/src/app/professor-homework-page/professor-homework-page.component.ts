import { Component, OnInit, Input } from '@angular/core';
import { TestService } from '../services/test.service';


@Component({
  selector: 'app-professor-homework-page',
  templateUrl: './professor-homework-page.component.html',
  styleUrls: ['./professor-homework-page.component.css']
})
export class ProfessorHomeworkPageComponent implements OnInit {
  @Input() username: string;
  @Input() givenClass: string;
  @Input() homework: string;
  file: File;
  MAX_FILE_SIZE: number;
  fileReady: boolean;
  uploading: boolean;
  error: string;
  results: string[] = [];
  type: string = null;
  runTime: string;

  constructor(private testService: TestService) {
    this.MAX_FILE_SIZE = 50000000;
    this.fileReady = false;
    this.uploading = false;
    this.file = null;
    this.error = '';
  }

  fileUpload(event: any) {
    this.fileReady = false;
    if (event.target.files && event.target.files.length > 0) {
      if (event.target.files[0].size > this.MAX_FILE_SIZE) {
        alert("File is too large!");
        this.file = null;
      } else {
        this.file = event.target.files[0];
        this.runTime = (<HTMLInputElement>document.getElementById("runTimeValue")).value;
        this.upload();
      }
    }
  }

  changeType(t: string) {
    this.type = t;
  }

  upload() {
    if (this.file !== null && this.type !== null && this.type != null) {
      this.uploading = true;
      this.testService.uploadTest(this.username, this.file, this.type, this.runTime, this.givenClass, this.homework).subscribe({
        next: x => {
          console.log(x),
          this.uploading = false,
          this.fileReady = true
        },
        error: err => {
          console.log("UPLOADING FILE ERROR: " + err),
          this.uploading = false,
          this.error = err
        },
        complete: () => console.log("Uploaded file")
      });
    }
  }

  getTests() {
    this.testService.getTests(this.givenClass, this.homework).subscribe({
      next: x => {
        console.log(x),
        this.results = x.map(element => element.toString());
        },
      error: err => {
        console.log("GET RESULTS ERROR: " + err),
          this.error = err
      },
      complete: () => console.log("got results")
    });
  }



  ngOnInit() {
  }

}
