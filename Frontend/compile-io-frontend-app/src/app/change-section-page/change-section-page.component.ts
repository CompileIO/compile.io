import { Component, OnInit, Input } from '@angular/core';
import { Section } from '../../models/section';
import { SectionService } from '../services/section.service';
import { Course } from '../../models/course';
import { CourseService } from '../services/course.service';

@Component({
  selector: 'app-change-section-page',
  templateUrl: './change-section-page.component.html',
  styleUrls: ['./change-section-page.component.css']
})
export class ChangeSectionPageComponent implements OnInit {

  @Input() sectionInfo: Section;
  @Input() courseInfo: Course;
  @Input() username: string;
  newSection: Section;
  changingStudents: boolean;
  constructor(private sectionService: SectionService,
              private courseService: CourseService) { this.changingStudents = false}

  submitForm(form: any) {
    console.log(this.newSection);
    this.submit();
  }

  openCloseStudentModal() {
    this.changingStudents = !this.changingStudents;

  }

  submit() {
    this.newSection.id = this.sectionInfo.id;
    this.newSection.courseId = this.courseInfo.id;
    console.log(this.newSection.id);
    if (this.sectionInfo.id == "-1") {
      this.sectionService.createSection(this.newSection).subscribe({
        next: x => {
          console.log(x);
          if (this.courseInfo.sections == null) {
            this.courseInfo.sections = [];
          }
          this.courseInfo.sections.push(x);
        },
        error: err => {
          console.log("CREATING SECTION ERROR:" + err);
        },
        complete: () => {
        }
      });
    } else {
     
      this.sectionService.updateSection(this.newSection).subscribe({
        next: x => {
          console.log(x)
        },
        error: err => {
          console.log("UPDATING SECTION ERROR: " + err)
        },
        complete: () => {
          this.newSection = new Section();
          console.log("Updated Section Complete");
        }
      });
    }
    window.location.reload();
  }

  ngOnInit() {
    this.newSection = new Section();
    this.newSection.id = this.sectionInfo.id;
    this.newSection.studentUsernames = [];
    if (this.sectionInfo.id != "-1") {
      this.newSection.assignments = this.sectionInfo.assignments;
      this.newSection.description = this.sectionInfo.description;
      this.newSection.id = this.sectionInfo.id;
      this.newSection.sectionNumber = this.sectionInfo.sectionNumber;
      this.newSection.studentUsernames = this.sectionInfo.studentUsernames;
      this.newSection.term = this.sectionInfo.term;
      this.newSection.useCourseDescription = this.sectionInfo.useCourseDescription;
      this.newSection.year = this.sectionInfo.year;
    } 
  }

}
