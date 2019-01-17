import { ModuleWithProviders} from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserPageComponent } from '../user-page/user-page.component';

import { LoginComponent } from '../login/login.component';
import { AuthGuard } from '../authguard.service';

export const ROUTES: Routes = [
  { path: 'student/:username',  component: UserPageComponent , canActivate: [AuthGuard]},
  { path: 'faculty/:username',  component: UserPageComponent , canActivate: [AuthGuard]},
  { path: 'instructor/:username',  component: UserPageComponent , canActivate: [AuthGuard]},
  // { path: 'course/:courseName',  component: CoursePageComponent , canActivate: [AuthGuard]},
  // { path: 'course/:courseName/:showStudents',  component: CoursePageComponent , canActivate: [AuthGuard]},
  // { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent}
  // { path: 'group', component: GroupGridPageComponent, canActivate: [AuthGuard]},
  // { path: 'group/create', component: GroupFormPageComponent, canActivate: [AuthGuard]},
  // { path: 'group/:groupName', component: GroupPageComponent, canActivate: [AuthGuard]}
];

// TODO: Store term in local storage. If we can load a term from local storage, use that term
//       If not then use the term that the current date falls in.
// TODO: Rename grids to components

export const ROUTING: ModuleWithProviders = RouterModule.forRoot(ROUTES, {onSameUrlNavigation: 'reload'});
