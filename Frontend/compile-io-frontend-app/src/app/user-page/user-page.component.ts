import { Component, OnInit, Input } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { AssignmentService } from '../services/assignment.service';
import { CourseService } from '../services/course.service';
import { ProfessorService } from '../services/professor.service';
import { StudentService } from '../services/student.service';
import {Assignment} from '../../models/assignment';
import { Course } from 'src/models/course';
import {Professor} from '../../models/professor';
import { Student } from 'src/models/student';
// const jwtDecode = require('jwt-decode');

@Component({
  selector: 'app-user-page',
  // decode = jwtDecode;
  templateUrl: './user-page.component.html',
  styleUrls: ['./user-page.component.css'],
})
export class UserPageComponent implements OnInit {
  @Input() username: string;
  @Input() name: string;
  @Input() group: string;
  Courses: Course[] = [];
  selectedCourse: Course = null;
  selectedAssignment: Assignment = null;
  change: boolean = false;
  courseChange: boolean = false;
  Assignments: Assignment[] = [];

  constructor(private authenticationService: AuthenticationService,
              private courseService:CourseService,
              private assignmentService:AssignmentService,
              private professorService:ProfessorService,
              private studentService:StudentService) {
    this.getCourses();
  }

  getCourses() : void {
    this.courseService.getCourses().subscribe({
      next: x => {this.Courses = x},
      error: err => console.log("GET COURSES ERROR: " + err),
      complete: () => courses => this.Courses = courses
    });
  }

  selectCourse(givenCourse: Course) {
    if (this.selectedCourse == givenCourse) {
      this.selectedCourse = null;
      this.Assignments = [];
      this.selectedAssignment = null;
    } else {
      this.selectedCourse = givenCourse;
      this.selectedAssignment = null;
      this.getAssignmentsForSpecificCourse(givenCourse.courseName);
    }
  }

  // selectCourse(courseID: string) {
  //   var i = 0;
  //   for(i = 0; i < this.Courses.length; i++) {
  //     if (this.Courses[i].id == courseID) {
  //       this.selectedCourse = this.Courses[i];
  //     }
  //   }
  // }

  newCourse() {
    this.selectedCourse = new Course();
    this.selectedCourse.id = "-1";
  }

  // getAssignments(): void {
  //   this.assignmentService.getAssignments().subscribe({
  //     complete: () => assignments => this.Assignments = assignments
  //   });  
  // }

  getAssignmentsForSpecificCourse(courseName: string): void {
    this.assignmentService.getAssignmentsForSpecificCourse(courseName).subscribe({
      next: x => {this.Assignments = x},
      error: err => console.log("GET HWK INFO ERROR: " + err),
      complete: () => assignments => this.Assignments = assignments
    });  
  }

  selectAssignment(assignmentID: string) {
    var i = 0;
    for(i = 0; i < this.Assignments.length; i++) {
      if (this.Assignments[i].id == assignmentID) {
        this.selectedAssignment = this.Assignments[i];
        this.changeChange(false)
      }
    }
  }

  newAssignment() {
    this.selectedAssignment = new Assignment();
    this.selectedAssignment.id = "-1";

  }

  changeChange(bool: boolean) {
    this.change = bool;
  }

  logout() {
    this.username = null;
    this.selectedCourse = null;
    this.Courses = [];
    this.Assignments = [];
    this.selectedAssignment = null;
    this.authenticationService.logout();
    window.location.reload();
  }

  ngOnInit() {
    // const token = window.sessionStorage.token;
    // const decoded = this.decode(token);
    this.getCourses();
  }

}
