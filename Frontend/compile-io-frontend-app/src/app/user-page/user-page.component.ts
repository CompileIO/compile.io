import { Component, OnInit, Input } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { AssignmentService } from '../services/assignment.service';
import { CourseService } from '../services/course.service';
import { ProfessorService } from '../services/professor.service';
import { StudentService } from '../services/student.service';
import { Assignment } from '../../models/assignment';
import { Student } from '../../models/student';
import { Section } from '../../models/section';
import { Course } from 'src/models/course';
import { Professor } from '../../models/professor';
import { ɵangular_packages_forms_forms_f } from '@angular/forms';
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
  selectedSection: Section = null;
  change: boolean = false;
  Assignments: Assignment[] = [];
  Sections: Section[] = [];
  profToAdd: Professor;
  profInfo: Professor;
  studentInfo: Student;

  constructor(private authenticationService: AuthenticationService,
    private courseService: CourseService,
    private assignmentService: AssignmentService,
    private professorService: ProfessorService,
    private studentService: StudentService) {
  }

  isProfessor(): void {
    var addProf = true;
    if (this.group === "PROFESSOR" || this.group === "ADMIN") {
      this.professorService.getProfessors().subscribe({
        next: professors => {
          for (var i = 0; i < professors.length; i++) {
            if (professors[i].userName === this.username) {
              addProf = false;
              break;
            }
          }
        },
        error: err => console.log("GET PROFESSOR ERROR: " + err),
        complete: () => {
          if (addProf) {
            this.profToAdd = new Professor();
            this.profToAdd.name = this.name;
            this.profToAdd.userName = this.username;
            console.log("Adding Professor");
            this.professorService.createProfessor(this.profToAdd).subscribe({
              next: x => { console.log(x) },
              error: err => { console.log("ADDING PROFESSOR ERROR: " + err) },
              complete: () => { console.log("Added Professor Complete") }
            });
          }
        }

      });
    }
  }

  getUserInfo(): void {
    if (this.group === "PROFESSOR" || this.group === "ADMIN") {
      this.professorService.getProfessorbyUsername(this.username).subscribe({
        next: prof => { this.profInfo = prof; this.Courses = this.profInfo.courses; }
      });
    } else {
      this.studentService.getStudentbyUsername(this.username).subscribe({
        next: stud => { this.studentInfo = stud; this.Sections = this.studentInfo.sections; }
      });
    }
  }
    
  //getCourses(): void {
  //  this.courseService.getCourses().subscribe({
  //    next: x => { this.Courses = x; console.log(x); },
  //    error: err => console.log("GET COURSES ERROR: " + err),
  //    complete: () => courses => this.Courses = courses
  //  });
  //}

  selectCourse(givenCourse: Course) {
    if (this.selectedCourse == givenCourse) {
      this.selectedCourse = null;
      this.Sections = [];
      this.selectedSection = null;
    } else {
      this.selectedCourse = givenCourse;
      this.selectedSection = null;
      this.Sections = givenCourse.sections;
    }
  }

  newCourse() {
    this.selectedCourse = new Course();
    this.selectedCourse.id = "-1";
  }

  //getAssignmentsForSpecificCourse(courseName: string): void {
  //  this.assignmentService.getAssignmentsForSpecificCourse(courseName).subscribe({
  //    next: x => { this.Assignments = x },
  //    error: err => console.log("GET HWK INFO ERROR: " + err),
  //    complete: () => assignments => this.Assignments = assignments
  //  });
  //}

  selectAssignment(assignment: Assignment) {
    if (this.selectedAssignment == assignment) {
      this.selectedAssignment = null;
    } else {
      this.selectedAssignment = assignment;
    }
  }

  newAssignment() {
    this.selectedAssignment = new Assignment();
    this.selectedAssignment.id = "-1";

  }
  newSection() {
    this.selectedSection = new Section();
    this.selectedSection.id = "-1";
  }
  selectSection(section: Section) {
    //var i = 0;
    //for (i = 0; i < this.Sections.length; i++) {
    //  if (this.Sections[i].id == sectionID) {
    //    this.selectedSection = this.Sections[i];
    //    this.changeChange(false)
    //  }
    //}
    if (this.selectedSection == section) {
      this.selectedSection = null;
      this.Assignments = [];
      this.selectedAssignment = null;
    } else {
      this.selectedSection = section;
      this.selectedAssignment = null;
      this.Assignments = section.assignments;
    }
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
    //this.getCourses();
    this.isProfessor();
    this.getUserInfo();
  }

}
