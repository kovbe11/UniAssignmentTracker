import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UniAssignmentTrackerComponent } from './components/uni-assignment-tracker/uni-assignment-tracker.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MyRequirementsTableComponent } from './components/my-requirements-table/my-requirements-table.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { SubjectsTableComponent } from './components/subjects-table/subjects-table.component';
import { MatCardModule } from '@angular/material/card';
import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './components/login/login.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule } from '@angular/forms';
import { JwtInterceptor } from './auth/jwt-interceptor';
import { ErrorInterceptor } from './auth/error-interceptor';
import { AdminComponent } from './components/admin/admin.component';
import { SubjectService } from './service/subject.service';
import { RequirementService } from './service/requirement.service';
import { ProjectService } from './service/project.service';
import { ExamService } from './service/exam.service';
import { AssignmentService } from './service/assignment.service';
import { ExamComponent } from './components/exam/exam.component';
import { ProjectComponent } from './components/project/project.component';
import { AssignmentComponent } from './components/assignment/assignment.component';
import { SubjectComponent } from './components/subject/subject.component';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { RegisterComponent } from './components/register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    UniAssignmentTrackerComponent,
    SubjectsTableComponent,
    MyRequirementsTableComponent,
    LoginComponent,
    AdminComponent,
    ExamComponent,
    ProjectComponent,
    AssignmentComponent,
    SubjectComponent,
    RegisterComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule,
    MatTableModule,
    MatCardModule,
    MatPaginatorModule,
    AppRoutingModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatSlideToggleModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    SubjectService,
    RequirementService,
    ProjectService,
    ExamService,
    AssignmentService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
