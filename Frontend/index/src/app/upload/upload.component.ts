import { Component, OnInit } from '@angular/core';
import { isNullOrUndefined } from 'util';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {
  // file: File;

  filesToUpload: Array<File>;

  constructor() {
      this.filesToUpload = [];
  }

  upload() {
      this.makeFileRequest("http://localhost:4000/upload", [], this.filesToUpload).then((result) => {
          console.log(result);
      }, (error) => {
          console.error(error);
      });
  }

  fileChangeEvent(fileInput: any){
      this.filesToUpload = <Array<File>> fileInput.target.files;
  }

  makeFileRequest(url: string, params: Array<string>, files: Array<File>) {
      return new Promise((resolve, reject) => {
          var formData: any = new FormData();
          var xhr = new XMLHttpRequest();
          for(var i = 0; i < files.length; i++) {
              formData.append("uploads[]", files[i], files[i].name);
          }
          xhr.onreadystatechange = function () {
              if (xhr.readyState == 4) {
                  if (xhr.status == 200) {
                      resolve(JSON.parse(xhr.response));
                  } else {
                      reject(xhr.response);
                  }
              }
          }
          xhr.open("POST", url, true);
          xhr.send(formData);
      });
  }

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

  ngOnInit() {
  }

}
