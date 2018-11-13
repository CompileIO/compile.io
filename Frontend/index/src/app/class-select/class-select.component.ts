import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { isUndefined } from 'util';
import { UploadService } from '../upload/upload.service';

import { ClassButtonComponent } from './class-button/class-button.component';

//const hostname = 'http://137.112.104.112:4000';
// var hostname = "http://localhost:4000"

@Component({
  selector: 'app-class-select',
  templateUrl: './class-select.component.html',
  styleUrls: ['./class-select.component.css']
})
export class ClassSelectComponent implements OnInit {
  selectedClass: String = '';
  classButtons: ClassButtonComponent[] = [];
  classes: String[];

  constructor(private http: HttpClient,
    private uploadService: UploadService) {
    uploadService.getClasses().then(result => {
      result.forEach(element => {
        this.classButtons.push(new ClassButtonComponent(element));
      });
    }, error => {
      console.log(error);
    });
    
    }

  ifNoSelectedClass(): boolean {
    if (this.selectedClass === '') {
      return true;
    }
    return false;
  }

  ifSelectedClass(): boolean {
    if (this.selectedClass === '') {
      return false;
    }
    return true;
  }

  selectClass(classToSelect: string) {
    this.selectedClass = classToSelect;
}
  unSelect() {
    this.selectedClass = '';
  }


ngOnInit() {
  }

}
