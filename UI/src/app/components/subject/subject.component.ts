import { Component, Input, OnInit } from '@angular/core';
import { Subject } from '../../model/Subject';
import { Assignment } from '../../model/Assignment';
import { flatten } from '@angular/compiler';

@Component({
  selector: 'app-subject',
  templateUrl: './subject.component.html',
  styleUrls: ['./subject.component.css']
})
export class SubjectComponent implements OnInit {

  @Input() subject: Subject

  constructor() { }

  ngOnInit(): void {
  }

  filteredAssignments(subject: Subject): Assignment[]{
    if(!subject.assignments){
      return []
    }

    let projectAssignments = flatten(subject.projects.map(project => project.assignments))
    return subject.assignments.filter(assignment => {
      return !projectAssignments.some(projectAssignment => projectAssignment.id === assignment.id)
    })
  }

}
