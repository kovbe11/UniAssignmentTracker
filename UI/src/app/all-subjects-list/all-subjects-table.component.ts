import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { AuthenticationService } from '../auth/authentication.service';
import { Router } from '@angular/router';
import { SubjectService } from '../service/subject.service';
import { Subject } from '../model/Subject';

@Component({
  selector: 'app-all-subjects-table',
  templateUrl: 'all-subjects-table.component.html',
  styleUrls: ['all-subjects-table.component.css'],
})
export class AllSubjectsTableComponent implements AfterViewInit {
  displayedColumns: string[] = ['name', 'projects', 'exams', 'subscription'];
  dataSource = new MatTableDataSource<Subject>([]);

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private authenticationService: AuthenticationService,
    private router: Router,
    private subjectService: SubjectService
  ) {}

  ngAfterViewInit() {
    this.subjectService.getAllSubjects().subscribe((data) => {
      this.dataSource.data = data || [];
    });
    this.dataSource.paginator = this.paginator;
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
}

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
  isSubscribed: boolean;
}

const ELEMENT_DATA: PeriodicElement[] = [
  { position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H', isSubscribed: true },
  { position: 2, name: 'Helium', weight: 4.0026, symbol: 'He', isSubscribed: true },
  { position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li', isSubscribed: true },
  { position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be', isSubscribed: false },
  { position: 5, name: 'Boron', weight: 10.811, symbol: 'B', isSubscribed: true },
  { position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C', isSubscribed: false },
  { position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N', isSubscribed: true },
  { position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O', isSubscribed: true },
  { position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F', isSubscribed: false },
  { position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne', isSubscribed: true },
  { position: 11, name: 'Sodium', weight: 22.9897, symbol: 'Na', isSubscribed: true },
  { position: 12, name: 'Magnesium', weight: 24.305, symbol: 'Mg', isSubscribed: true },
  { position: 13, name: 'Aluminum', weight: 26.9815, symbol: 'Al', isSubscribed: false },
  { position: 14, name: 'Silicon', weight: 28.0855, symbol: 'Si', isSubscribed: true },
  { position: 15, name: 'Phosphorus', weight: 30.9738, symbol: 'P', isSubscribed: true },
  { position: 16, name: 'Sulfur', weight: 32.065, symbol: 'S', isSubscribed: true },
  { position: 17, name: 'Chlorine', weight: 35.453, symbol: 'Cl', isSubscribed: true },
  { position: 18, name: 'Argon', weight: 39.948, symbol: 'Ar', isSubscribed: false },
  { position: 19, name: 'Potassium', weight: 39.0983, symbol: 'K', isSubscribed: false },
  { position: 20, name: 'Calcium', weight: 40.078, symbol: 'Ca', isSubscribed: true },
];
