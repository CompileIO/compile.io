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
  selectedHomework: Assignment = null;
  change: boolean = false;
  Assignments: Assignment[] = [];

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
      next: x => {this.Assignments = x},
      error: err => console.log("GET HWK INFO ERROR: " + err),
      complete: () => assignments => this.Assignments = assignments
    });  
  }

  selectHomework(hwkID: string) {
    var i = 0;
    for(i = 0; i < this.Assignments.length; i++) {
      if (this.Assignments[i].id == hwkID) {
        this.selectedHomework = this.Assignments[i];
        this.changeChange(false)
      }
    }
  }

  newHomework() {
    this.selectedHomework = new Assignment();
    this.selectedHomework.id = "-1";

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
