import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


import { AppComponent } from './app.component';
import { UploadComponent } from './upload/upload.component';
import { ClassSelectComponent } from './class-select/class-select.component';
import { ClassButtonComponent } from './class-select/class-button/class-button.component';

@NgModule({
  declarations: [
    AppComponent,
    UploadComponent,
    ClassSelectComponent,
    ClassButtonComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
