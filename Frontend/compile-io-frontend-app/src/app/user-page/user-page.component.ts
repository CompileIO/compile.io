import { Component, OnInit, Input } from '@angular/core';
import { AuthenticationService } from '../services/authentication.service';
import { SectionService } from '../services/section.service';
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
  studentToAdd: Student;
  profInfo: Professor;
  studentInfo: Student;

  constructor(private authenticationService: AuthenticationService,
    private courseService: CourseService,
    private sectionService: SectionService,
    private professorService: ProfessorService,
    private studentService: StudentService) {
  }

  isProfessor(): void {
    var addProf = true;
    if (this.group === "PROFESSOR" || this.group === "ADMIN") {
      this.professorService.getProfessorbyUsername(this.username).subscribe({
        next: professors => {
          if(professors.length == 0) {
            this.profToAdd = new Professor();
            this.profToAdd.name = this.name;
            this.profToAdd.userName = this.username;
            console.log("Adding Professor");
            this.professorService.createProfessor(this.profToAdd).subscribe({
              next: x => { console.log(x) },
              error: err => { console.log("ADDING PROFESSOR ERROR: " + err) },
              complete: () => { console.log("Added Professor Complete"); this.getUserInfo(); }
            });
          } else {
            
            if (professors[0].userName === this.username) {
              addProf = false;
              if (professors[0].name === null && professors[0].name === undefined) {
                professors[0].name = this.name;
                this.professorService.updateProfessor(professors[0]).subscribe({
                  next: x => { console.log(x) },
                  error: err => { console.log("UPDATING PROFESSOR ERROR: " + err) },
                  complete: () => { console.log("Updating Professor Complete"); this.getUserInfo();}
                });
              }
            }
            this.getUserInfo();
          }
          
        },
        error: err => console.log("GET PROFESSOR ERROR: " + err),
        complete: () => {
        }

      });
    }
  }

  isStudent(): void {
    var addStudent = true;
    if (this.group === "STUDENT") {
      this.studentService.getStudentbyUsername(this.username).subscribe({
        next: student => {
          if (student.userName === this.username) {
            addStudent = false;
            if (student.name === null || student.name === undefined) {
              student.name = this.name;
              this.studentService.updateStudent(student).subscribe({
                next: x => { console.log(x) },
                error: err => { console.log("UPDATING STUDENT ERROR: " + err) },
                complete: () => { console.log("Updating Student Complete"); this.getUserInfo();}
              });
            }
            
          }
        },
        error: err => console.log("GET STUDENT ERROR: " + err),
        complete: () => {
          if (addStudent) {
            this.studentToAdd = new Student();
            this.studentToAdd.name = this.name;
            this.studentToAdd.userName = this.username;
            console.log("Adding Student");
            this.studentService.createStudent(this.studentToAdd).subscribe({
              next: x => { console.log(x) },
              error: err => { console.log("ADDING STUDENT ERROR: " + err) },
              complete: () => { console.log("Added Student Complete"); this.getUserInfo(); }
            });
          }
          this.getUserInfo();
        }

      });
    }
  }

  getUserInfo(): void {
    if (this.group === "PROFESSOR" || this.group === "ADMIN") {
      this.professorService.getProfessorbyUsername(this.username).subscribe({
        next: profs => { this.profInfo = profs[0];
          if (this.profInfo.courses == null || this.profInfo.courses == undefined) { 
            this.profInfo.courses = []; 
          } else {
            this.Courses = this.profInfo.courses; 
          }
          
        }
      });
    } else {
      this.studentService.getStudentbyUsername(this.username).subscribe({
        //HERE JOSH
        next: stud => { this.studentInfo = stud; if (this.studentInfo.sectionIds == null) { this.studentInfo.sectionIds = []; }this.studentInfo.sectionIds.forEach(sectionId => {
          this.sectionService.getSection(sectionId).subscribe({
            next: section => {this.Sections.push(section)}
          })
        }) }
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
    this.selectedAssignment = null;
    this.selectedSection = null;
    if (this.selectedCourse == givenCourse) {
      this.selectedCourse = null;
      this.Sections = [];
    } else {
      this.selectedCourse = givenCourse;
      this.Sections = givenCourse.sections;
    }
    console.log(givenCourse);
  }

  newCourse() {
    this.selectAssignment = null;
    this.selectedSection = null;
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
    console.log(this.selectedAssignment);
  }

  newAssignment() {
    this.selectedAssignment = new Assignment();
    this.selectedAssignment.id = "-1";
  }
  newSection() {
    this.selectedAssignment = null;
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
    this.selectedAssignment = null;
    if (this.selectedSection == section) {
      this.selectedSection = null;
      this.selectedAssignment = null;
      this.Assignments = [];
    } else {
      this.selectedAssignment = null;
      this.selectedSection = section;
      this.courseService.getCourse(section.courseId).subscribe({
        next: course => this.selectedCourse = course
      });
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
    this.isStudent();
    // this.getUserInfo();

    //The above is very inefficient too many gets on database and can be consolidated getUserInfo is not necessary
  }

}
