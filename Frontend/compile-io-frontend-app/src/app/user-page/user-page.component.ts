import { Component, OnInit, Input } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { AssignmentService } from '../services/assignment.service';
import { CourseService } from '../services/course.service';
import {Assignment} from '../../models/assignment';

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
  // homeworks: string[] = [];
  selectedHomework: Assignment = null;
  change: boolean = false;
  falseBoolean: boolean = false;
  trueBoolean: boolean = true;
  Assignments: Assignment[];

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
      this.Assignments = [];
      this.selectedHomework = null;
    } else {
      this.selectedClass = givenClass;
      this.selectedHomework = null;
      this.getAssignmentsForSpecificCourse(givenClass);
    }
  }

  // getAssignments(): void {
  //   this.assignmentService.getAssignments().subscribe({
  //     complete: () => assignments => this.Assignments = assignments
  //   });  
  // }

  getAssignmentsForSpecificCourse(assignmentName: string): void {
    this.assignmentService.getAssignmentsForSpecificCourse(assignmentName).subscribe({
      error: err => console.log("GET HWK INFO ERROR: " + err),
      complete: () => assignments => this.Assignments = assignments
    });  
  }

  selectHomework(givenHwk: string) {
    var i = 0;
    for(i = 0; i < this.Assignments.length; i++) {
      if (this.Assignments[i].assignmentName == givenHwk) {
        this.selectedHomework = this.Assignments[i];
      }
    }
  }

  changeChange(bool: boolean) {
    this.change = bool;
  }

  logout() {
    this.username = null;
    this.selectedClass = null;
    this.classes = [];
    this.Assignments = [];
    this.selectedHomework = null;
    this.authenticationService.logout();
    window.location.reload();
  }

  ngOnInit() {
  }

}
