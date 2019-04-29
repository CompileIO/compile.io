import { Component, OnInit, Input } from '@angular/core';
import { CodeService } from '../services/code.service';
import {Assignment} from '../../models/assignment';
import {Code} from '../../models/code';
import {Section} from '../../models/section';
import { saveAs as importedSaveAs } from 'file-saver';
import { forEach } from '@angular/router/src/utils/collection';

declare var require: any

@Component({
  selector: 'app-homework-page',
  templateUrl: './homework-page.component.html',
  styleUrls: ['./homework-page.component.css']
})
export class HomeworkPageComponent implements OnInit {
  @Input() username: string;
  @Input() assignmentInfo: Assignment;
  file: File;
  results: String;
  error: string;
  running: boolean;
  sDate: string;
  eDate: string;
  code: Code;
  FileSaver: any = require('file-saver');

  constructor(private codeService: CodeService) {
    this.file = null;
    this.error = '';
    this.results = null;
    this.running = false;
    
    
  }

  fileUploadFunction(event: any) {
    console.log("THIS IS THE FILE FROM file upload: " + event.target.files[0]);
    if (event.target.files[0].size < this.assignmentInfo.size) {
      this.file = event.target.files[0];
    } else {
      alert("File is too large!");
    }
    
  }

  serveFile(){
    this.codeService.serveFile(this.code.fileName, this.code.codePath).subscribe(response => {
      const blobDownloaded = new Blob([response], { type: 'text/csv; charset=utf-8' });
      this.file = this.blobToFile(blobDownloaded, this.code.fileName);
    });
  }
  

  downloadFileButtonPress() {
    this.downloadFileToComputer(this.file, this.file.name);
  }

  downloadFileToComputer(file: File, fileName: String) {
    this.FileSaver.saveAs(file, fileName);
  }

  blobToFile = (theBlob: Blob, fileName:string): File => {
    var b: any = theBlob;
    //A Blob() is almost a File() - it's just missing the two properties below which we will add
    b.lastModifiedDate = new Date();
    b.name = fileName;
    //Cast to a File() type
    return <File>theBlob;
  }

  runCode(code:Code) {
    this.running = true;
    console.log("Should run upload file")
      this.codeService.runCode(this.file, code.id).subscribe({
        next: x => {
          console.log(x);
          this.code = x;
          if (this.code.testResponses == null) {
            this.code.testResponses = [];
          }
          if (this.code.unitResponses == null) {
            this.code.unitResponses = [];
          }
          
        },
        error: err => {
          console.log("RUNNING CODE ERROR: " + err);
        },
        complete: () => {
          this.running = false;
          console.log("Ran code against test complete");
          this.results = this.parseString(this.code.testResponses);
          this.codeService.updateCode(this.code).subscribe({
            next: x => {
              this.code = x;
            }
          });
        }
      });
  }

  parseString(result: String[]): string {
    let finalString: string;
    let index = this.code.submissionAttempts - 1;

    //get the string that should be displayed. 
    let start = result[index].indexOf("---");
    let last = result[index].lastIndexOf("-");
    finalString = result[index].slice(start, last);
    

    //Find number of tests
    start = finalString.indexOf("(") + 1;
    last = finalString.indexOf(" tests");
    let tempString: string;
    tempString = finalString.slice(start, last);
    let numTests: Number = Number.parseInt(tempString);

    //Find number of successes
    start = finalString.indexOf("tests, ") + 7;
    last = finalString.indexOf(" successes");
    tempString = finalString.slice(start, last);
    let numSuccesses: Number = Number.parseInt(tempString);

    //Set grade
    this.code.grade = numTests.toString() + "/" + numSuccesses.toString();

    //Find all test results
    last = result[index].indexOf(" > ");
    start = result[index].lastIndexOf("\n", last);
    last = result[index].indexOf(" > ", last + 3);
    if (last == -1) {
      last = result[index].indexOf("-", start);
    }
    last = result[index].lastIndexOf("\n", last);
    tempString = result[0].slice(start, last);
    this.code.unitResponses[index] = [];
    this.code.unitResponses[index].push(tempString);

    let i = result[index].indexOf(" > ", last);
    while (i != -1) {
      start = result[index].lastIndexOf("\n", i);
      last = result[index].indexOf(" > ", i + 3);
      if (last == -1) {
        last = result[index].indexOf("-", start);
      }
      last = result[index].lastIndexOf("\n", last);
      tempString = result[index].slice(start, last);
      this.code.unitResponses[index].push(tempString);
      i = result[index].indexOf(" > ", last);
    }

    this.code.testResponses[index] = finalString;

    return finalString;
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
        if(this.code != null || this.code != undefined) {
          this.serveFile();
        }
      }
    });
  }

  belowMaxSubmissionAttempts(): boolean {
    if(this.code != null || this.code != undefined) {
      // this.serveFile();
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
    var i = 1;
    if(i === 1) {
      console.log("Getting codes");
      this.getCodesForAssignment();
      i++;
    }
    
  }

}
