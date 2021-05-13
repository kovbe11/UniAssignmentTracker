import { AfterViewInit, Component, ViewChild } from '@angular/core';
import { animate, state, style, transition, trigger } from '@angular/animations';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Requirement } from '../../model/Requirement';
import { RequirementService } from '../../service/requirement.service';
import { flatten } from '@angular/compiler';

@Component({
  selector: 'app-my-assignments-table',
  styleUrls: ['my-requirements-table.component.css'],
  templateUrl: 'my-requirements-table.component.html',
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class MyRequirementsTableComponent implements AfterViewInit {
  dataSource = new MatTableDataSource<Requirement>([]);
  columnsToDisplay = ['label', 'due-date', 'type'];
  expandedRequirement: Requirement | null;

  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(private requirementService: RequirementService) {}

  ngAfterViewInit() {
    this.requirementService.getRequirements().subscribe((data) => {
      this.dataSource.data = data || [];
    });
    this.dataSource.paginator = this.paginator;
  }
}
