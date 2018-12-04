import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { UploadComponent } from './upload.component';
import { FormsModule } from "@angular/forms"; 

const routes: Routes = [
{path: 'api/uploads', component: UploadComponent},
];
@NgModule({
imports: [RouterModule.forChild(routes), CommonModule, FormsModule],
exports: [RouterModule]
})
export class UploadModule { }