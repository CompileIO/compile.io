import { Component, OnInit } from '@angular/core';
import { isNullOrUndefined } from 'util';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};


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
      console.log(this.file.name);
      console.log(this.file.type);
      console.log(this.file);
      //this.http.post("http://compile-io.com/post_request", this.file, httpOptions);
      const formData: FormData = new FormData();
      formData.append('file', this.file, this.file.name);
      //http://localhost:4000/post_request
        const req = new HttpRequest('POST', "http://example.com/post_request", formData, {
        reportProgress: true
      });
      //const httpOptions = {
      //  headers: new HttpHeaders({
      //      "Origin": "http://localhost:4200"
      //  })
      //};
      //this.http.options(httpOptions);
      this.http.request(req).subscribe(event => {
        if (event instanceof HttpResponse) {
          return;
        }
      });
    } else {
      console.log("BAD!")
    };
  }

  ngOnInit() {
  }

}
