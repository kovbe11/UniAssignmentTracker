import { AfterViewInit, Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { AuthenticationService } from '../../auth/authentication.service';
import { Router } from '@angular/router';
import { SubjectService } from '../../service/subject.service';
import { Subject } from '../../model/Subject';
import { MatSlideToggleChange } from '@angular/material/slide-toggle';

@Component({
  selector: 'app-all-subjects-table',
  templateUrl: 'subjects-table.component.html',
  styleUrls: ['subjects-table.component.css']
})
export class SubjectsTableComponent implements AfterViewInit {
  displayedColumns: string[] = ['name', 'projects', 'exams', 'assignments', 'officialCredit', 'experiencedCredit', 'subscription'];
  dataSource = new MatTableDataSource<Subject>([]);

  selectedSubject: Subject | undefined = undefined;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router,
    private subjectService: SubjectService) {
  }

  ngAfterViewInit() {
    this.subjectService.getAllSubjects().subscribe((data) => {
      this.dataSource.data = data || [];
    });
    this.dataSource.paginator = this.paginator;
    this.dataSource.filterPredicate = ((data: Subject, filter) => {
      if (filter === 'true') {
        return data.subscribed;
      }
      return true;
    });
  }

  subscribe(row: any) {
    if (!this.authenticationService.isAuthenticated) {
      this.router.navigate(['/login']);
    }
    this.subjectService.subscribeToSubject(row).subscribe((_) => {
      row.subscribed = true;
    });
  }

  unsubscribe(row: any) {
    this.subjectService.unsubscribeFromSubject(row).subscribe((_) => {
      row.subscribed = false;
    });
  }

  onFilterChanged(event: MatSlideToggleChange) {
    if (event.checked) {
      this.dataSource.filter = 'true';
      return;
    }
    this.dataSource.filter = 'false';
  }
}
