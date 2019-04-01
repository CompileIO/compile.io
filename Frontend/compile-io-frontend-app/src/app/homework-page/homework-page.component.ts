import { Component, OnInit, Input } from '@angular/core';
import { CodeService } from '../services/code.service';
import {Assignment} from '../../models/assignment';
import {Code} from '../../models/code';
import {Section} from '../../models/section';

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
  code: Code;

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
    
  }

  // serveFile(){
  //   // this.codeService.serveFile(this.code.)
  // }

  runCode(code:Code) {
    this.running = true;
    console.log("Should run upload file")
      this.codeService.runCode(this.file, code.id).subscribe({
        next: x => {
          console.log(x);
          this.code = x;
        },
        error: err => {
          console.log("RUNNING CODE ERROR: " + err);
        },
        complete: () => {
          this.running = false;
          console.log("Ran code against test complete");
          this.results = this.code.testResponses;
        }
      });
  }


  run() {
    
    if(this.code != null && this.code != undefined) {
      this.runCode(this.code);

    } else {
      // this.code.language = this.assignmentInfo.language;
      this.code = new Code();
      this.code.runTime = this.assignmentInfo.timeout;
      this.code.userName = this.username;
      this.code.assignmentId = this.assignmentInfo.id;
      this.codeService.createCode(this.code).subscribe({
        next: x => {console.log("Code added: " + x); 
          this.code = x;
        },
        error: err => {
          console.log("ADD CODE ERROR: " + err),
          this.error = err
        },
        complete: () => {
          console.log("Added Code")
          this.runCode(this.code);
        }
      });
    }
    
  }

  getCodesForAssignment() {
    this.codeService.getCodesForSpecificAssignment(this.assignmentInfo.id, this.username).subscribe({
      next: x => {
        console.log(x);
        this.code = x;
      },
      error: err => {
        console.log("GET CODES FOR ASSIGNMENT ERROR: " + err),
        this.error = err
      },
      complete: () => {
        this.belowMaxSubmissionAttempts();
      }
    });
  }

  belowMaxSubmissionAttempts(): boolean {
    if(this.code != null || this.code != undefined) {
      if(this.code.submissionAttempts >= this.assignmentInfo.tries) {
        return false;
      }
    }
      return true;
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
  ngOnInit() {
    this.getCodesForAssignment();
  }

}
