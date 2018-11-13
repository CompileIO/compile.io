import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { UploadComponent } from './upload.component';
const routes: Routes = [
{path: 'api/uploads', component: UploadComponent},
];
@NgModule({
imports: [RouterModule.forChild(routes), CommonModule],
exports: [RouterModule]
})
export class UploadModule { }