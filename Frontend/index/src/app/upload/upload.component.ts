import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from "@angular/forms";
import { isNullOrUndefined } from 'util';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders, HttpParams } from '@angular/common/http';
import { UploadService } from './upload.service';
//const hostname = 'http://137.112.104.112:4000';
// var hostname = "http://localhost:4000"


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
  constructor(private http: HttpClient, private uploadService: UploadService) {
  }


  fileChangeEvent(event: any) {
      // this.file = fileInput.target.files;
      // console.log("This is the file"+ this.file.name);

      //Instantiate an object to read the file content
    let reader = new FileReader();
    // when the load event is fired and the file not empty
    if(event.target.files && event.target.files.length > 0) {
      // Fill file variable with the file content
      this.file = event.target.files[0];
      console.log("This is the file"+ this.file.name);
    }
  }

  createForm() {
    
    this.form = new FormBuilder().group({
      file_upload: null
    });
  }

  
  // Upload the file to the API
  upload() {
    // Instantiate a FormData to store form fields and encode the file
    let body = new FormData();
    // Add file content to prepare the request
    body.append("file", this.file);
    // Launch post request
    console.log("This is the body"+ body.get("file"));
    this.http.post('http://localhost:8080/', body)
    .subscribe(
      // Admire results
      (data) => {console.log(data)},
      // Or errors :-(
      error => console.log("in uploads ERROR" + error),
      // tell us if it's finished
      () => { console.log("completed") }
    );
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
