import { Component, OnInit, Input } from '@angular/core';
import { Section } from '../../models/section';
import { Course } from '../../models/course';
import { Student } from '../../models/student';


@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css']
})
export class SectionComponent implements OnInit {

  @Input() sectionInfo: Section;
  @Input() courseInfo: Course;
  @Input() studentInfo: Student;
  grades: boolean;

  constructor() { }

  gradesBoolean() {
    this.grades = !this.grades;
  }

  ngOnInit() {
    this.grades = false;
  }

}
