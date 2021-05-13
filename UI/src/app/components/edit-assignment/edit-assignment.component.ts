import { Component, Input, OnInit } from '@angular/core';
import { Project } from '../../model/Project';
import { Subject } from '../../model/Subject';
import { Assignment } from '../../model/Assignment';

@Component({
  selector: 'app-edit-assignment',
  templateUrl: './edit-assignment.component.html',
  styleUrls: ['./edit-assignment.component.css']
})
export class EditAssignmentComponent implements OnInit {

  @Input() assignment: Assignment
  @Input() parentSubject: Subject

  constructor() { }

  ngOnInit(): void {
  }

}
