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
  professorList: string[] = [];
  newCourse: Course;
  constructor(private courseService: CourseService, private professorService: ProfessorService) {
  }

  submitForm(form: any) {
    console.log(form);
    this.submit();
  }

  addProfessor(profUsername: string) {
    if (profUsername != "") {
      this.professorList.push(profUsername);
    }
  }

  removeProfessor(profUsername: string) {
    if (profUsername != "") {
      this.professorList.splice(this.professorList.indexOf(profUsername), 1);
    }
  }




  submit() {
    this.newCourse.professors = this.professorList;
    var addProf = true;
      this.newCourse.professors.forEach ( professor => {
        if(professor === this.prof.userName) {
          addProf = false;
        }
      });
      if(addProf) {
        this.newCourse.professors.push(this.prof.userName);
      }
    if (this.courseInfo.id == "-1") {      
      this.newCourse.sections = [];
      this.newCourse.id = "-1";

      this.courseService.createCourse(this.newCourse).subscribe({
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
    window.location.reload();
  }


  ngOnInit() {
    this.newCourse = new Course();
    this.newCourse.professors = [];
    if (this.courseInfo.id != '-1') {
      this.newCourse.courseName = this.courseInfo.courseName;
      this.newCourse.professors = this.courseInfo.professors;
      this.professorList = this.newCourse.professors;
      this.newCourse.description = this.courseInfo.description;
      this.newCourse.id = this.courseInfo.id; 
      this.newCourse.sections = this.courseInfo.sections;
      //this.newCourse.crn = this.courseInfo.crn;
      //this.newCourse.sectionNumber = this.courseInfo.sectionNumber;
      this.newCourse.professors = this.courseInfo.professors;
      //this.newCourse.students = this.courseInfo.students;
    }
  }

}

