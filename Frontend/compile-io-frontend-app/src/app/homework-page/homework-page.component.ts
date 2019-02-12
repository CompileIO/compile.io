import { Component, OnInit, Input } from '@angular/core';
import { CodeService } from '../services/code.service';
import {Assignment} from '../../models/assignment';

@Component({
  selector: 'app-homework-page',
  templateUrl: './homework-page.component.html',
  styleUrls: ['./homework-page.component.css']
})
export class HomeworkPageComponent implements OnInit {
  @Input() username: string;
  @Input() assignmentInfo: Assignment;
  file: File;
  results: string[];
  error: string;

  constructor(private codeService: CodeService) {
    this.file = null;
    this.error = '';
    this.results = [];
    // this.getResults();
  }

  fileUploadFunction(event: any) {
    // console.log("THIS IS THE FILE FROM file upload: " + event.target.files[0]);
    // if (event.target.files[0].size < this.assignmentInfo.size) {
      this.file = event.target.files[0];
    // } else {
    //   alert("File is too large!");
    // }
    if (this.file !== null) {
      console.log("Should run upload file")
      this.codeService.uploadFile(this.file).subscribe({
        next: x => {
          console.log(x)
        },
        error: err => {
          console.log("UPLOADING FILE ERROR: " + err)
        },
        complete: () => console.log("Uploaded file")
      });
    }
  }

  run() {

    this.codeService.uploadCode(this.assignmentInfo.language, this.assignmentInfo.timeout,  this.assignmentInfo.id,this.username).subscribe({
      next: x => console.log(x),
      error: err => {
        console.log("RUNNING DOCKER ERROR: " + err),
        this.error = err
      },
      complete: () => {
        console.log("Ran docker")
        // this.getResults();
      }
    });
  }

  // getResults() {
  //   this.codeService.getResults(this.assignmentInfo.id, this.username).subscribe({
  //     next: x => {
  //       console.log(x),
  //       this.results = x.map(element => element.toString());
  //       },
  //     error: err => {
  //       console.log("GET RESULTS ERROR: " + err),
  //         this.error = err
  //     },
  //     complete: () => console.log("got results")
  //   });
  // }


  ngOnInit() {
  }

}
