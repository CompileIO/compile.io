import { Component, OnInit, Input } from '@angular/core';
import { CourseService } from '../services/course.service';
import { ProfessorService } from '../services/professor.service';
import { Course } from '../../models/course';
import { Professor } from 'src/models/professor';

@Component({
  selector: 'app-change-course-page',
  templateUrl: './change-course-page.component.html',
  styleUrls: ['./change-course-page.component.css']
})
export class ChangeCoursePageComponent implements OnInit {
  @Input() username: string;
  @Input() courseInfo: Course;
  @Input() prof: Professor;
  newCourse: Course;
  constructor(private courseService: CourseService, private professorService: ProfessorService) {
  }

  submitForm(form: any) {
    console.log(form);
    this.submit();
  }

  submit() {
    if (this.courseInfo.id == "-1") {
      console.log(this.newCourse.courseName);
      this.newCourse.sections = [];
      this.newCourse.professors = [];
      this.newCourse.id = "-1";
      //add course to professor
      // this.professorService.getProfessorbyUsername(this.username).subscribe({
      //   next: x => {
      //     this.prof = x
      //     this.prof.courses = [];
      //     this.prof.courses.push(this.newCourse);
      //   },
      //   error: err => {
      //     console.log("GET PROFESSOR BY USERNAME ERROR: " + err)
      //   },
      //   complete: () => 
      // this.prof.courses = [];
      this.prof.courses.push(this.newCourse);
        this.professorService.updateProfessor(this.prof).subscribe({
          next: x => { this.newCourse.professors.push(x) },
          error: err => {
            console.log("UPDATING PROFESSOR ERROR: " + err)
          },
          complete: () => this.courseService.createCourse(this.newCourse).subscribe({
            next: x => {
              console.log(x)
            },
            error: err => {
              console.log("ADDING COURSE ERROR: " + err)
            },
            complete: () => {
              this.newCourse = new Course()
              console.log("Added Course Complete")
            }
          })
        });
    }
    else {
      this.newCourse.id = this.courseInfo.id;
      this.courseService.updateCourse(this.newCourse).subscribe({
        next: x => {
          console.log(x)
        },
        error: err => {
          console.log("UPDATING COURSE ERROR: " + err)
        },
        complete: () => {
          this.newCourse = new Course()
          console.log("Updated Course Complete")
        }
      });
    }
    //window.location.reload();
  }


  ngOnInit() {
    this.newCourse = new Course();
    if (this.courseInfo.id != '-1') {
      this.newCourse.courseName = this.courseInfo.courseName;
      this.newCourse.professors = this.courseInfo.professors;
      this.newCourse.description = this.courseInfo.description;
      this.newCourse.id = this.courseInfo.id; 
      //this.newCourse.crn = this.courseInfo.crn;
      //this.newCourse.sectionNumber = this.courseInfo.sectionNumber;
      this.newCourse.professors = this.courseInfo.professors;
      //this.newCourse.students = this.courseInfo.students;
    }
  }

}

