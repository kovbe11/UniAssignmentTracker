import { Component, Input, OnInit } from '@angular/core';
import { Project } from '../../../model/Project';
import { Subject } from '../../../model/Subject';
import { Assignment } from '../../../model/Assignment';
import { ExamService } from '../../../service/exam.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AssignmentService } from '../../../service/assignment.service';

@Component({
  selector: 'app-edit-assignment',
  templateUrl: './edit-assignment.component.html',
  styleUrls: ['./edit-assignment.component.css']
})
export class EditAssignmentComponent implements OnInit {

  private PLACE_HOLDER = {
    optional: false,
    deadline: new Date(),
    description: 'assignment description',
    label: 'assignment label'
  };

  private EMPTY: Assignment = {
    optional: false,
    deadline: new Date(),
    description: '',
    label: ''
  };


  @Input() assignment: Assignment = this.EMPTY;
  @Input() parentSubject: Subject;
  @Input() isNew: boolean;
  @Input() parentProject: Project;

  constructor(private assignmentService: AssignmentService,
              private route: ActivatedRoute,
              private router: Router,
              private snackBar: MatSnackBar) {
    if (this.assignment) {
      return;
    }
    this.route.params.subscribe(param => {
      assignmentService.getAssignment(param.id).subscribe(assignment => this.assignment = assignment, error => {
        console.log('This project doesn\'t exist!');
        router.navigate(['/']);
      });
    });
  }

  ngOnInit(): void {
    if (this.isNew) {
      this.assignment = this.PLACE_HOLDER; // placeholder
    }
  }

  save() {
    this.assignmentService.updateAssignment(this.assignment).subscribe(_ => {
      this.snackBar.open('Updated successfully', 'Close', { duration: 3000 });
    }, error => this.snackBar.open('Error!', 'Close', { duration: 3000 }));
  }

  create() {
    if (!this.parentSubject) {
      this.snackBar.open('Please choose a subject!', 'Close', { duration: 3000 });
      return;
    }
    if (this.parentProject) {
      this.assignmentService.createProjectAssignment(this.parentSubject, this.parentProject, this.assignment)
        .subscribe((response: any) => {
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
    this.assignmentService.createAssignment(this.parentSubject, this.assignment).subscribe((response: any) => {
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
    this.assignmentService.deleteAssignment(this.assignment.id).subscribe(res => {
      this.snackBar.open("Deleted successfully!", "Close",{duration: 3000})
    }, error => this.snackBar.open("Error!", "Close",{duration: 3000}))
  }
}
