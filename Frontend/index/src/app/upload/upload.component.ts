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
  constructor(private http: HttpClient) {
  }

  // upload() {
  //   this.uploadService.upload(this.file)
  //   .then(result => {
  //       console.log(result);
  //   }, error => {
  //       console.log(error);
  //   });
  //   //   this.makeFileRequest( hostname + '/upload', [], this.filesToUpload).then((result) => {
  //   //       console.log(result);
  //   //   }, (error) => {
  //   //       console.error(error);
  //   //   });
  // }

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

//   makeFileRequest(url: string, params: Array<string>, files: Array<File>) {
//       return new Promise((resolve, reject) => {
//           const formData: any = new FormData();
//           const xhr = new XMLHttpRequest();
//           for (let i = 0; i < files.length; i++) {
//               formData.append('uploads[]', files[i], files[i].name);
//           }
//           xhr.onreadystatechange = function () {
//               if (xhr.readyState === 4) {
//                   if (xhr.status === 200) {
//                       resolve(JSON.parse(xhr.response));
//                   } else {
//                       reject(xhr.response);
//                   }
//               }
//           };
//           xhr.open('POST', url, true);
//           xhr.send(formData);
//       });
//   }

  // constructor(private http: HttpClient) { }

  // uploadFile() {
  //   if (!isNullOrUndefined(this.file)) {
  //     console.log(this.file.size);
  //     console.log(this.file.type);
  //     console.log(this.file);
  //     const formData: FormData = new FormData();
  //     formData.append('file', this.file);
  //     const req = new HttpRequest('POST', "http://localhost:4000/post_request", formData, {
  //       headers: new HttpHeaders({
  //         "Content-Type": "application/json"
  //       }),
  //       reportProgress: true,
  //       withCredentials: true
  //     });

  //     this.http.request(req).subscribe(event => {
  //       if (event instanceof HttpResponse) {
  //         return;
  //       }
  //     });
  //   } else {
  //     console.log("BAD!");
  //   }
  // }
  // Instantiate an AbstractControl from a user specified configuration
  createForm() {
    
    this.form = new FormBuilder().group({
      file_upload: null
    });
  }

  // Check for changes in files inputs via a DOMString reprsenting the name of an event
  // fileChange(event: any) {
  //   // Instantiate an object to read the file content
  //   let reader = new FileReader();
  //   // when the load event is fired and the file not empty
  //   if(event.target.files && event.target.files.length > 0) {
  //     // Fill file variable with the file content
  //     this.file = event.target.files[0];
  //   }
  // }

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

  ngOnInit() {
    this.file = null;
    this.createForm;
  }

}
