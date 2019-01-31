import { Component, OnInit, Input } from '@angular/core';
import { TestService } from '../services/test.service';


@Component({
  selector: 'app-add-homework',
  templateUrl: './add-homework.component.html',
  styleUrls: ['./add-homework.component.css']
})
export class AddHomeworkComponent implements OnInit {
  @Input() username: string;
  @Input() class: string;

  constructor(private testService: TestService) {}

  submit(name: string, time: number, visible: boolean, file: File, language: string, size: number) {
    
    // console.log(name + ", " + time + ", " + visible + ", " + file);
    // console.log(this.username + "  " + this.class);
    // console.log(dropDown)
    this.testService.uploadTest(file, language.toLowerCase(), time, name, this.class, this.username, size).subscribe({
      next: x => {
        console.log(x)
      },
      error: err => {
        console.log("UPLOADING FILE ERROR: " + err)
      },
      complete: () => console.log("Uploaded file")
    });
  }
  ngOnInit() {
  }

}
