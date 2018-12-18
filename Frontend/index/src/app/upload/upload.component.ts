import { Component, OnInit } from '@angular/core';
import { UploadService } from './upload.service';


@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css'],
  providers: [UploadService],
})
export class UploadComponent implements OnInit {
  // file: File;

  //form: FormGroup;
  file: File;
  ready: boolean;
  MAX_FILE_SIZE: number;

  constructor(private uploadService: UploadService) {
    this.MAX_FILE_SIZE = 250000000;
    this.ready = true;
  }


  fileChangeEvent(event: any) {
      //Instantiate an object to read the file content
    let reader = new FileReader();
    // when the load event is fired and the file not empty
    if(event.target.files && event.target.files.length > 0) {
      // Fill file variable with the file content
      if (event.target.files[0].size > this.MAX_FILE_SIZE) {
        alert("File is too large!");
        this.file = null;
      } else {
        this.file = event.target.files[0];
        this.ready = false;
        console.log("This is the file: " + this.file.name);
      }

    }
  }

  //createForm() {
  //  this.form = new FormBuilder().group({
  //    file_upload: null
  //  });
  //}

  
  // Upload the file to the API
  upload() {
    if (this.file !== null) {
      this.uploadService.upload(this.file)
        .then(result => {
          console.log(result);
        }, error => {
          console.log(error);
        });
    }
  }

  run() {
    this.uploadService.runDocker().then(result => {
    }, error => {
      console.log(error);
    });
  }
  
  ngOnInit() {
    this.file = null;
    //this.createForm;
  }

}
