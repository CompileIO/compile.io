import { Component, OnInit, Input } from '@angular/core';
import { CourseService } from '../services/course.service';
import {Course} from '../../models/course';
import { Professor } from 'src/models/professor';

@Component({
  selector: 'app-course-page',
  templateUrl: './course-page.component.html',
  styleUrls: ['./course-page.component.css']
})
export class CoursePageComponent implements OnInit {
  @Input() username: string;
  @Input() courseInfo: Course;
  prof: Professor;
  newCourse: Course;
  constructor(private courseService: CourseService) { 
  }

  submitForm(form: any) {
    console.log(form);
  }

  submit() {
    if (this.courseInfo.id == "-1") {
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
    //window.location.reload();
  }

  readyToSubmit(): boolean {
    if (this.courseInfo.id == '-1') {
      if (this.newCourse.courseName== undefined ||
        this.newCourse.crn == undefined ||
        this.newCourse.sectionNumber == undefined ||
        this.newCourse.instructor == undefined
        // this.newCourse.students == undefined
        ) {
        return false;
      }
    }
    return true;
  }

  ngOnInit() {
    this.newCourse = new Course();
    this.prof = new Professor();
    this.prof.userName = this.username;
    this.newCourse.instructor= this.prof;
    if(this.courseInfo.id != '-1'){
      this.newCourse.courseName = this.courseInfo.courseName;
      this.newCourse.crn = this.courseInfo.crn;
      this.newCourse.sectionNumber = this.courseInfo.sectionNumber;

      this.newCourse.instructor = this.courseInfo.instructor;
      this.newCourse.students = this.courseInfo.students;
      
    }
  }

}
