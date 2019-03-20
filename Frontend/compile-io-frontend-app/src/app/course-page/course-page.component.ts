import { Component, OnInit, Input } from '@angular/core';
import { CourseService } from '../services/course.service';
import {Course} from '../../models/course';

@Component({
  selector: 'app-course-page',
  templateUrl: './course-page.component.html',
  styleUrls: ['./course-page.component.css']
})
export class CoursePageComponent implements OnInit {
  @Input() courseInfo: Course;

  constructor() { }

  ngOnInit() {
  }
}
