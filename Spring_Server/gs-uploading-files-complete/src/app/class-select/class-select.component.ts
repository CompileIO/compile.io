import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpRequest, HttpResponse, HttpHeaders } from '@angular/common/http';
import { isUndefined } from 'util';

import { ClassButtonComponent } from './class-button/class-button.component';

var hostname = "http://137.112.104.112:4000"
// var hostname = "http://localhost:4000"

@Component({
  selector: 'app-class-select',
  templateUrl: './class-select.component.html',
  styleUrls: ['./class-select.component.css']
})
export class ClassSelectComponent implements OnInit {
  selectedClass: string = "";
  classButtons: ClassButtonComponent[] = [];

  constructor(private http: HttpClient) {
    const req = new HttpRequest('GET',  hostname + "/get_request", {
      headers: new HttpHeaders({
        "Content-Type": "application/json"
      }),
      reportProgress: true,
      withCredentials: true
    });
    this.http.request(req).subscribe(event => {
      if (event instanceof HttpResponse) {
        console.log("HEARD BACK!");
        var i = 0;
        this.classButtons = [];
        while (!isUndefined(event.body[i])) {
          this.classButtons.push(new ClassButtonComponent(event.body[i].className));
          i++;
        } 
        //classes = String[](event.body);
        //var items = event.body;
        //classes = items.map(function (item) {
        //  return item['className'];
        //});
        //if (isNullOrUndefined(classes)) {
        //  classes = [""]
        //}
        
      }
    });
  }

  ifNoSelectedClass(): boolean {
    if (this.selectedClass == "") {
      return true;
    }
    return false;
  }

  ifSelectedClass(): boolean {
    if (this.selectedClass == "") {
      return false;
    }
    return true;
  }

  selectClass(classToSelect: string) {
    this.selectedClass = classToSelect;
}
  unSelect() {
    this.selectedClass = "";
  }


ngOnInit() {
  }

}
