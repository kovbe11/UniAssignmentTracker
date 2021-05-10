import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllSubjectsTableComponent } from './all-subjects-list/all-subjects-table.component';
import { MySubjectsListComponent } from './my-subjects-list/my-subjects-list.component';
import { MyRequirementsTableComponent } from './my-assignments-table/my-requirements-table.component';
import { AuthGuardService } from './auth/auth-guard.service';
import { UniAssignmentTrackerComponent } from './uni-assignment-tracker/uni-assignment-tracker.component';
import { LoginComponent } from './login/login.component';
import { AdminComponent } from './admin/admin.component';
import { AdminGuardService } from './auth/admin-guard.service';

const routes: Routes = [
  {
    path: '',
    component: UniAssignmentTrackerComponent,
    children: [
      {
        path: '',
        component: AllSubjectsTableComponent,
      },
      {
        path: 'mySubjects',
        component: MySubjectsListComponent,
        canActivate: [AuthGuardService],
      },
      {
        path: 'myAssignments',
        component: MyRequirementsTableComponent,
        canActivate: [AuthGuardService],
      },
      {
        path: 'admin',
        component: AdminComponent,
        canActivate: [AdminGuardService],
      },
    ],
  },
  { path: 'login', component: LoginComponent },
  { path: '**', redirectTo: '' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
