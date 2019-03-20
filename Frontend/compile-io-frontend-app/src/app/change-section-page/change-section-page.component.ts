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
  constructor() { }

  ngOnInit() {
  }

}
