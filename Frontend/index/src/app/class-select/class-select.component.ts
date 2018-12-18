import { Component, OnInit } from '@angular/core';
import { ClassButtonComponent } from './class-button/class-button.component';

@Component({
  selector: 'app-class-select',
  templateUrl: './class-select.component.html',
  styleUrls: ['./class-select.component.css']
})
export class ClassSelectComponent implements OnInit {
  selectedClass: String = '';
  classButtons: ClassButtonComponent[] = [];

  constructor() {
  }

  addClasses(classStuff: ClassButtonComponent[]) {
    this.classButtons = classStuff
  }

  ifNoSelectedClass(): boolean {
    console.log(this.classButtons);
    if (this.selectedClass === '') {
      return true;
    }
    return false;
  }

  ifSelectedClass(): boolean {
    if (this.selectedClass === '') {
      return false;
    }
    return true;
  }

  selectClass(classToSelect: string) {
    this.selectedClass = classToSelect;
}
  unSelect() {
    this.selectedClass = '';
  }


ngOnInit() {
  }

}
