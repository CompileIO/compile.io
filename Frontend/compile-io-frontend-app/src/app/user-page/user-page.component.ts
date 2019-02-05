import { Component, OnInit, Input } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { AssignmentService } from '../services/assignment.service';
import { CourseService } from '../services/course.service';

@Component({
  selector: 'app-user-page',
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css']
})
export class UserPageComponent implements OnInit {
  @Input() username: string;
  @Input() group: string;
  classes: string[] = [];
  selectedClass: string = null;
  homeworks: string[] = [];
  selectedHomework: string = null;
  change: boolean = false;

  constructor(private authenticationService: AuthenticationService,
              private courseService:CourseService,
              private assignmentService:AssignmentService) {
    this.getCourses();
  }

  getCourses() {
    this.courseService.getCourses().subscribe({
      next: x => this.classes = x.map(element => element.toString()),
      error: err => console.log("GET CLASSES ERROR: " + err),
      complete: () => console.log("got classes")
    });
  }

  selectClass(givenClass: string) {
    if (this.selectedClass == givenClass) {
      this.selectedClass = null;
      this.homeworks = [];
      this.selectedHomework = null;
    } else {
      this.selectedClass = givenClass;
      this.selectedHomework = null;
      this.assignmentService.getAssignments(this.selectedClass).subscribe({
        next: x => this.homeworks = x.map(element => element.toString()),
        error: err => console.log("GET HOMEWORKS ERROR: " + err),
        complete: () => console.log("got homeworks")
      });
    }
  }

  selectHomework(givenHwk: string) {
    this.selectedHomework = givenHwk;
  }

  changeChange(bool: boolean) {
    this.change = bool;
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
