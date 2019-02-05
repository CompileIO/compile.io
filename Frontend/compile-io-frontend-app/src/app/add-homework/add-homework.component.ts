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
  file : File;

  constructor(private testService: TestService) {}
  fileUpload(event: any){
    console.log("THIS IS THE FILE FROM file upload: " +event.target.files[0]);
    this.file = event.target.files[0];
  }

  submit(name: string, time: number, visible: boolean, language: string) {
    console.log("THIS IS THE FILE FROM SUBMIT: " + this.file);
    this.testService.uploadTest(this.file, language.toLowerCase(), time, name, this.class, this.username).subscribe({
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
