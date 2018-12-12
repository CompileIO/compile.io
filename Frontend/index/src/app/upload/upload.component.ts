import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from "@angular/forms";
import { HttpClient, } from '@angular/common/http';
import { UploadService } from './upload.service';


@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css'],
  providers: [UploadService],
})
export class UploadComponent implements OnInit {
  // file: File;

  form: FormGroup;
  file: File;
  MAX_FILE_SIZE: number;

  constructor(private http: HttpClient, private uploadService: UploadService) {
    this.MAX_FILE_SIZE = 250000000;
  }


  fileChangeEvent(event: any) {
      //Instantiate an object to read the file content
    let reader = new FileReader();
    // when the load event is fired and the file not empty
    if(event.target.files && event.target.files.length > 0) {
      // Fill file variable with the file content
      this.file = event.target.files[0];
      console.log("This is the file: "+ this.file.name);
    }
  }

  createForm() {
    this.form = new FormBuilder().group({
      file_upload: null
    });
  }

  
  // Upload the file to the API
  upload() {
    console.log(this.file.size);
    if (this.file.size > this.MAX_FILE_SIZE) {
      console.log("File is too large!");
    } else {
      this.uploadService.upload(this.file)
        .then(result => {
          console.log(result);
        }, error => {
          console.log(error);
        });
    }
    
  }

  run() {
    this.uploadService.runDocker().then(result => {
    }, error => {
      console.log(error);
    });
  }
  
  ngOnInit() {
    this.file = null;
    this.createForm;
  }

}
