import { Component, OnInit } from '@angular/core';
import { isNullOrUndefined } from 'util';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {
  file: File;

  constructor(private http: HttpClient) { }

  uploadFile() {
    if (!isNullOrUndefined(this.file)) {
      console.log(this.file.size);
      console.log(this.file.type);
      console.log(this.file);
      const formData: FormData = new FormData();
      formData.append('file', this.file, this.file.name);
      const req = new HttpRequest('POST', "http://localhost:4000/post_request", formData, {
        headers: new HttpHeaders({
          "Content-Type": "application/json"
        }),
        reportProgress: true,
        withCredentials: true
      });
      
      this.http.request(req).subscribe(event => {
        if (event instanceof HttpResponse) {
          return;
        }
      });
    } else {
      console.log("BAD!");
    }
  }

  ngOnInit() {
  }

}
