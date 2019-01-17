import { Component, OnInit, Input } from '@angular/core';
import { UploadService } from '../upload/upload.service';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-professor-page',
  templateUrl: './professor-page.component.html',
  styleUrls: ['./professor-page.component.css']
})
export class ProfessorPageComponent implements OnInit {
  @Input() username: string;
  classes: string[] = [];
  selectedClass: string = null;
  homeworks: string[] = [];
  selectedHomework: string = null;

  constructor(private uploadService: UploadService,
    private authenticationService: AuthenticationService) {
    this.getClasses();
  }

  getClasses() {
    this.uploadService.getClasses().subscribe({
      next: x => this.classes = x.map(element => element.toString()),
      error: err => console.log("GET CLASSES ERROR: " + err),
      complete: () => console.log("got classes")
    });
  }

  selectClass(givenClass: string) {
    if (this.selectedClass == givenClass) {
      this.selectedClass = '';
      this.homeworks = [];
    } else {
      this.selectedClass = givenClass;
      this.uploadService.getHomeworks(this.selectedClass).subscribe({
        next: x => this.homeworks = x.map(element => element.toString()),
        error: err => console.log("GET HOMEWORKS ERROR: " + err),
        complete: () => console.log("got homeworks")
      });
    }
  }

  selectHomework(givenHwk: string) {
    this.selectedHomework = givenHwk;
  }

  return() {
    this.selectedHomework = null;
  }

  logout() {
    this.username = null;
    this.selectedClass = null;
    this.classes = [];
    this.homeworks = [];
    this.selectedHomework = null;
    this.authenticationService.logout();
    window.location.reload();
  }

  ngOnInit() {
  }

}
