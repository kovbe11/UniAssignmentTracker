import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AllSubjectsTableComponent} from './all-subjects-list/all-subjects-table.component';
import {MySubjectsListComponent} from './my-subjects-list/my-subjects-list.component';
import {MyAssignmentsTableComponent} from './my-assignments-table/my-assignments-table.component';
import {AuthGuardService as AuthGuard} from './auth/auth-guard.service';
import {UniAssignmentTrackerComponent} from './uni-assignment-tracker/uni-assignment-tracker.component';
import {LoginComponent} from './login/login.component';

const routes: Routes = [
  {
    path: '', component: UniAssignmentTrackerComponent,
    children: [
      {
        path: '',
        component: AllSubjectsTableComponent
      },
      {
        path: 'mySubjects',
        component: MySubjectsListComponent,
        // canActivate: [AuthGuard]
      },
      {
        path: 'myAssignments',
        component: MyAssignmentsTableComponent,
        canActivate: [AuthGuard]
      }
    ]
  },
  {path: 'login', component: LoginComponent},
  {path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
