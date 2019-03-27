import { Component, OnInit, Input } from '@angular/core';
import { Section } from '../../models/section';
import { Course } from '../../models/course';


@Component({
  selector: 'app-section',
  templateUrl: './section.component.html',
  styleUrls: ['./section.component.css']
})
export class SectionComponent implements OnInit {

  @Input() sectionInfo: Section;
  @Input() courseInfo: Course;

  constructor() { }

  ngOnInit() {
  }

}
