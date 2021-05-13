import { Component, OnInit } from '@angular/core';
import { Subject } from '../../model/Subject';
import { Assignment } from '../../model/Assignment';
import { flatten } from '@angular/compiler';
import { SubjectService } from '../../service/subject.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-edit-subject',
  templateUrl: './edit-subject.component.html',
  styleUrls: ['./edit-subject.component.css']
})
export class EditSubjectComponent implements OnInit {

  subject: Subject;

  constructor(private subjectService: SubjectService,
              private route: ActivatedRoute,
              private router: Router) {
    this.route.params.subscribe(param => {
      subjectService.getSubject(param.id).subscribe(subject => this.subject = subject);
    });
  }

  ngOnInit(): void {

  }

  filteredAssignments(subject: Subject): Assignment[] {
    if (!subject.assignments) {
      return [];
    }

    let projectAssignments = flatten(subject.projects.map(project => project.assignments));
    return subject.assignments.filter(assignment => {
      return !projectAssignments.some(projectAssignment => projectAssignment.id === assignment.id);
    });
  }

  save() {
    this.subjectService.updateSubject(this.subject).subscribe(_ => this.router.navigate(['/']));
  }

  delete() {
    this.subjectService.deleteSubject(this.subject.id).subscribe(_ => this.router.navigate(['/']));
  }

}
