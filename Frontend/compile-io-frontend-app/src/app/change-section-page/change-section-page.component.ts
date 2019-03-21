import { Component, OnInit, Input } from '@angular/core';
import { Section } from '../../models/section';
import { SectionService } from '../services/section.service';
import { Course } from '../../models/course';

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
  constructor(private sectionService: SectionService) { }

  submitForm(form: any) {
    console.log(form);
    this.submit();
  }

  submit() {
    if (this.sectionInfo.id == "-1") {
      this.sectionService.createSection(this.newSection).subscribe({
        next: x => {
          console.log(x);
        },
        error: err => {
          console.log("CREATING SECTION ERROR:" + err);
        },
        complete: () => {
          this.newSection = new Section();
          console.log("Creating Section Complete");
        }
      });
    } else {
      this.newSection.id = this.sectionInfo.id;
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
  }

  ngOnInit() {
    this.newSection = new Section();
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
