import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SubjectsTableComponent } from './components/subjects-table/subjects-table.component';
import { MyRequirementsTableComponent } from './components/my-requirements-table/my-requirements-table.component';
import { AuthGuardService } from './auth/auth-guard.service';
import { UniAssignmentTrackerComponent } from './components/uni-assignment-tracker/uni-assignment-tracker.component';
import { LoginComponent } from './components/login/login.component';
import { AdminComponent } from './components/editors/admin/admin.component';
import { AdminGuardService } from './auth/admin-guard.service';
import { RegisterComponent } from './components/register/register.component';
import { EditSubjectComponent } from './components/editors/edit-subject/edit-subject.component';
import { EditExamComponent } from './components/editors/edit-exam/edit-exam.component';
import { EditProjectComponent } from './components/editors/edit-project/edit-project.component';
import { EditAssignmentComponent } from './components/editors/edit-assignment/edit-assignment.component';

const routes: Routes = [
  {
    path: '',
    component: UniAssignmentTrackerComponent,
    children: [
      {
        path: '',
        component: SubjectsTableComponent
      },
      {
        path: 'myRequirements',
        component: MyRequirementsTableComponent,
        canActivate: [AuthGuardService]
      },
      {
        path: 'admin',
        component: AdminComponent,
        canActivate: [AdminGuardService]
      },
      {
        path: 'subject/:id',
        component: EditSubjectComponent,
        canActivate: [AdminGuardService]
      },
      {
        path: 'exam/:id',
        component: EditExamComponent,
        canActivate: [AdminGuardService]
      },
      {
        path: 'project/:id',
        component: EditProjectComponent,
        canActivate: [AdminGuardService]
      },
      {
        path: 'assignments/:id',
        component: EditAssignmentComponent,
        canActivate: [AdminGuardService]
      }
    ]
  },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
