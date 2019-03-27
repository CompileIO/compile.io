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
  results: String[];
  error: string;
  running: boolean;
  sDate: string;
  eDate: string;

  constructor(private codeService: CodeService) {
    this.file = null;
    this.error = '';
    this.results = null;
    this.running = false;
    // this.getResults();
  }

  fileUploadFunction(event: any) {
    console.log("THIS IS THE FILE FROM file upload: " + event.target.files[0]);
    if (event.target.files[0].size < this.assignmentInfo.size) {
      this.file = event.target.files[0];
    } else {
      alert("File is too large!");
    }
    if (this.file !== null) {
      console.log("Should run upload file")
      this.codeService.uploadFile(this.file, this.assignmentInfo.courseName, this.assignmentInfo.assignmentName, this.username).subscribe({
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
    this.running = true;
    this.codeService.uploadCode(this.assignmentInfo.language, this.assignmentInfo.timeout,  this.assignmentInfo.id,this.username).subscribe({
      next: x => {console.log("This is the result: " + x); this.results = x},
      error: err => {
        console.log("RUNNING DOCKER ERROR: " + err),
        this.error = err
      },
      complete: () => {
        console.log("Ran docker")
        this.running = false;
        // this.getResults();
      }
    });
  }

  clearResult() {
    this.results = null;
  }

  onTime(): boolean {
    var today = new Date();
    //if (today.getFullYear() >= this.assignmentInfo.startDate.getFullYear() &&
    //  today.getFullYear() <= this.assignmentInfo.endDate.getFullYear() &&
    //  today.getMonth() >= this.assignmentInfo.startDate.getMonth() &&
    //  today.getMonth() <= this.assignmentInfo.endDate.getMonth() &&
    //  today.getDay) {

    //}
    //console.log(this.assignmentInfo.startDate);
    var sd = new Date(this.assignmentInfo.startDate);
    var ed = new Date(this.assignmentInfo.endDate);
    //console.log();
    var sth = Number(this.assignmentInfo.startTime.toString().substring(0, this.assignmentInfo.startTime.toString().indexOf(":")));
    sth += sd.getHours() + 5;
    var stm = Number(this.assignmentInfo.startTime.toString().substring(this.assignmentInfo.startTime.toString().indexOf(":") + 1, this.assignmentInfo.startTime.toString().length));
    stm += sd.getMinutes();
    sd.setHours(sth);
    sd.setMinutes(stm);

    var eth = Number(this.assignmentInfo.endTime.toString().substring(0, this.assignmentInfo.endTime.toString().indexOf(":")));
    eth += ed.getHours() + 5;
    var etm = Number(this.assignmentInfo.endTime.toString().substring(this.assignmentInfo.endTime.toString().indexOf(":") + 1, this.assignmentInfo.endTime.toString().length));
    etm += ed.getMinutes();
    ed.setHours(eth);
    ed.setMinutes(etm);

    this.sDate = sd.toString().substring(0,sd.toString().indexOf(":")+3);
    this.eDate = ed.toString().substring(0, ed.toString().indexOf(":") + 3);
    if (today >= sd) {
      if (today <= ed) {
        return true;
      }
    }
    return false;
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
