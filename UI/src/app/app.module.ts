import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UniAssignmentTrackerComponent } from './uni-assignment-tracker/uni-assignment-tracker.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MySubjectsListComponent } from './my-subjects-list/my-subjects-list.component';
import { MyAssignmentsTableComponent } from './my-assignments-table/my-assignments-table.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { AllSubjectsTableComponent } from './all-subjects-list/all-subjects-table.component';
import { MatCardModule } from '@angular/material/card';
import { AppRoutingModule } from './app-routing.module';
import { LoginComponent } from './login/login.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { ReactiveFormsModule } from '@angular/forms';
import { JwtInterceptor } from './auth/jwt-interceptor';
import { ErrorInterceptor } from './auth/error-interceptor';
import { AdminComponent } from './admin/admin.component';
import { SubjectService } from './service/subject.service';

@NgModule({
  declarations: [
    AppComponent,
    UniAssignmentTrackerComponent,
    MySubjectsListComponent,
    AllSubjectsTableComponent,
    MyAssignmentsTableComponent,
    LoginComponent,
    AdminComponent,
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
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
    SubjectService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
