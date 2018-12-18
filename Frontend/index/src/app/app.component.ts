import { Component } from '@angular/core';
import { ClassSelectComponent } from './class-select/class-select.component';
import { UploadService } from './upload/upload.service';
import { ClassButtonComponent } from './class-select/class-button/class-button.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'index';
  username: string = null;
  className: string = null;
  homework: string = null;
  select: ClassSelectComponent = null;
  uploadService: UploadService;

  constructor(uploadService: UploadService) {
    this.uploadService = uploadService;
    this.buildClassSelect();
  }

  buildClassSelect() {
    var classButtons = [];
    this.uploadService.getClasses().then(result => {
    result.forEach(element => {
      classButtons.push(new ClassButtonComponent(element));
    });
    }, error => {
      console.log(error);
      });
    this.select = new ClassSelectComponent();
    console.log(classButtons);
    this.select.addClasses(classButtons);
    this.username = "Zach";
    }

  checkClassSelect() {
    if (this.username !== null &&
      this.className == null &&
      this.homework == null) {
      return true;
    }
    return false;
  }
}
