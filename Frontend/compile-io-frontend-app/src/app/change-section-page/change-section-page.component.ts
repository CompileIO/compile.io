import { Component, OnInit, Input } from '@angular/core';
import { Section } from '../../models/section';

@Component({
  selector: 'app-change-section-page',
  templateUrl: './change-section-page.component.html',
  styleUrls: ['./change-section-page.component.css']
})
export class ChangeSectionPageComponent implements OnInit {

  @Input() sectionInfo: Section;
  @Input() username: string;
  newSection: Section;
  constructor() { }

  ngOnInit() {
    if (this.sectionInfo.id != "-1") {
      this.newSection.assignments = this.sectionInfo.assignments;
      this.newSection.description = this.sectionInfo.description;
      this.newSection.id = this.sectionInfo.id;
      this.newSection.sectionNumber = this.sectionInfo.sectionNumber;
      this.newSection.students = this.sectionInfo.students;
      this.newSection.Term = this.sectionInfo.Term;
      this.newSection.useClassDescription = this.sectionInfo.useClassDescription;
      this.newSection.Year = this.sectionInfo.Year;
    }
  }

}
