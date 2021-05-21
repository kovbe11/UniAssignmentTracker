import { Component, Input, OnInit } from '@angular/core';
import { Subject } from '../../../model/Subject';
import { Assignment } from '../../../model/Assignment';
import { flatten } from '@angular/compiler';
import { SubjectService } from '../../../service/subject.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-edit-subject',
  templateUrl: './edit-subject.component.html',
  styleUrls: ['./edit-subject.component.css']
})
export class EditSubjectComponent implements OnInit {

  private EMPTY_SUBJECT = {
    name: '',
    description: '',
    scoring: '',
    officialCredit: 0,
    experiencedCredit: 0
  };

  private PLACEHOLDER_SUBJECT = {
    name: 'subject name',
    description: 'subject description',
    scoring: 'subject scoring',
    officialCredit: 1,
    experiencedCredit: 1
  };

  subject: Subject = this.EMPTY_SUBJECT;

  @Input() isNew: boolean;

  constructor(private subjectService: SubjectService,
              private route: ActivatedRoute,
              private router: Router,
              private snackBar: MatSnackBar) {
    this.route.params.subscribe(param => {
      subjectService.getSubject(param.id).subscribe(subject => {
        console.log(subject)
        this.subject = subject;
      }, error => {
        if (this.isNew) {
          this.subject = this.PLACEHOLDER_SUBJECT;
        } else {
          console.log('This subject doesn\'t exist!');
          router.navigate(['/']);
        }
      });
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
    this.subjectService.updateSubject(this.subject).subscribe(_ => {
      this.snackBar.open('Updated successfully', 'Close', { duration: 3000 });
    }, error => this.snackBar.open('Error!', 'Close', { duration: 3000 }));
  }

  create() {
    this.subjectService.createSubject(this.subject).subscribe((response: any) => {
      if (response.id) {
        this.snackBar.open(`Created with id ${response.id}`, 'Close', { duration: 3000 });
      } else {
        this.snackBar.open('Error!', 'Close', { duration: 3000 });
      }
    }, error => {
      console.log(error);
      this.snackBar.open('Error!', 'Close', { duration: 3000 });
    });
  }

  delete() {
    this.subjectService.deleteSubject(this.subject.id).subscribe(res => {
      this.snackBar.open("Deleted successfully!", "Close",{duration: 3000})
    }, error => this.snackBar.open("Error!", "Close",{duration: 3000}))
  }

}
