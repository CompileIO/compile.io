import { Component, OnInit } from '@angular/core';
import { UploadService } from '../upload/upload.service';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css'],
  providers: [UploadService],
})
export class ResultsComponent implements OnInit {

  constructor(private uploadService: UploadService) { }



  ngOnInit() {
  }

}
