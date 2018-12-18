import { Component, OnInit, Input } from '@angular/core';
import { UploadService } from '../upload/upload.service';
//import { ClassInfoComponent } from '../class-info/class-info.component';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {
  @Input() username: string;
  classes: string[] = [];
  selectedClass: string = null;
  homeworks: string[] = [];

  constructor(private uploadService: UploadService) {
    this.getClasses();
  }

  getClasses() {
    //this.uploadService.getClasses().then(result => {
    //  result.forEach(element => {
    //    this.classes.push(element.toString());
    //  });
    //}, error => {
    //  console.log(error);
    //});
    this.classes.push("csse120");
    this.classes.push("csse220");
  }

  selectClass(givenClass: string) {
    if (this.selectedClass == givenClass) {
      this.selectedClass = '';
      this.homeworks = [];
    } else {
      this.selectedClass = givenClass;
      this.homeworks.push("Hwk1");
      this.homeworks.push("Hwk2");
    }
    
  }

  ngOnInit() {
  }

}
