import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {UniAssignmentTrackerComponent} from './uni-assignment-tracker/uni-assignment-tracker.component';
import {LayoutModule} from '@angular/cdk/layout';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatIconModule} from '@angular/material/icon';
import {MatListModule} from '@angular/material/list';
import {MySubjectsListComponent} from './my-subjects-list/my-subjects-list.component';
import {MyAssignmentsTableComponent} from './my-assignments-table/my-assignments-table.component';
import { MatTableModule } from '@angular/material/table';
import {MatPaginatorModule} from '@angular/material/paginator';
import {AllSubjectsTableComponent} from './all-subjects-list/all-subjects-table.component';
import {MatCardModule} from '@angular/material/card';
import {AppRoutingModule} from './app-routing.module';
import { LoginComponent } from './login/login.component';

// const routes: Routes = ROUTES;

@NgModule({
  declarations: [
    AppComponent,
    UniAssignmentTrackerComponent,
    MySubjectsListComponent,
    AllSubjectsTableComponent,
    MyAssignmentsTableComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
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
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
