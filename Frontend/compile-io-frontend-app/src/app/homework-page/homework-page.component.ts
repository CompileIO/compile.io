import { Component, OnInit, Input } from '@angular/core';
import { AssignmentService } from '../services/assignment.service';
import { TestService } from '../services/test.service';
import {Assignment} from '../../models/assignment';

@Component({
  selector: 'app-homework-page',
  templateUrl: './homework-page.component.html',
  styleUrls: ['./homework-page.component.css']
})
export class HomeworkPageComponent implements OnInit {
  @Input() username: string;
  @Input() givenClass: string;
  @Input() homework: string;
  file: File;
  MAX_FILE_SIZE: number;
  fileReady: boolean;
  uploading: boolean;
  error: string;
  results: string[] = [];
  newAssignment: Assignment = new Assignment();

  constructor(private assignmentService: AssignmentService, private testService: TestService) {
    this.MAX_FILE_SIZE = 50000000;
    this.fileReady = false;
    this.uploading = false;
    this.file = null;
    this.error = '';
    this.getResults();
  }

  fileUpload(event: any) {
    console.log("THIS IS THE FILE FROM file upload: " + event.target.files[0]);
    if (event.target.files[0].size < this.MAX_FILE_SIZE) {
      this.file = event.target.files[0];
    } else {
      alert("File is too large!");
    }
  }

  submit(name: string,
    timeout: number,
    language: string,
    size: number,
    tries: number,
    startDate: Date,
    endDate: Date) {
    this.newAssignment.courseName = this.givenClass
    this.newAssignment.assignmentName = name
    this.newAssignment.timeout = timeout
    this.newAssignment.language = language
    this.newAssignment.size = size
    this.newAssignment.tries = tries
    // this.newAssignment.file = this.file
    this.newAssignment.startDate = startDate
    this.newAssignment.endDate = endDate

  // upload() {
    if (this.file !== null) {
      this.assignmentService.createAssignment(this.newAssignment).subscribe({
        next: x => {
          console.log(x)
        },
        error: err => {
          console.log("UPLOADING FILE ERROR: " + err)
        },
        complete: () => console.log("Uploaded file")
      });
    }
  // }
}

  run() {
    this.testService.runDocker().subscribe({
      next: x => console.log(x),
      error: err => {
        console.log("RUNNING DOCKER ERROR: " + err),
        this.error = err
      },
      complete: () => {
        console.log("Ran docker"),
        this.getResults();
      }
    });
  }

  getResults() {
    this.testService.getResults(this.givenClass, this.homework).subscribe({
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
