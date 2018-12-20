import { Component, OnInit, Input } from '@angular/core';
import { UploadService } from '../upload/upload.service';

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

  constructor(private uploadService: UploadService) {
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
        this.upload();
      }
    }
  }

  upload() {
    this.uploading = true;
    if (this.file !== null) {
      this.uploadService.upload(this.file)
        .then(result => {
          console.log(result);
          this.uploading = false;
          this.fileReady = true;
        }, error => {
          console.log(error);
          this.uploading = false;
          this.error = error;
        });
    }
  }

  run() {
    this.uploadService.runDocker().then(result => {
      console.log(result);
    }, error => {
      this.error = error;
      console.log(error);
      });
    this.getResults();
  }

  getResults() {
    this.uploadService.getResults(this.givenClass, this.homework).then(result => {
      console.log(result);
      this.results = [];
      result.forEach(element => {
        this.results.push(element.toString());
      });
    }, error => {
      this.error = error;
      console.log(error);
    });
  }


  ngOnInit() {
  }

}
