import { Component, Input, OnInit } from '@angular/core';
import { Subject } from '../../../model/Subject';
import { Exam } from '../../../model/Exam';
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ExamService } from '../../../service/exam.service';
import { Project } from '../../../model/Project';

@Component({
  selector: 'app-edit-exam',
  templateUrl: './edit-exam.component.html',
  styleUrls: ['./edit-exam.component.css']
})
export class EditExamComponent implements OnInit {


  private PLACE_HOLDER: Exam = {
    dueDate: new Date(),
    experiences: "exam experiences",
    scoring: "exam scoring",
    resit: false,
    label: "exam label"
  }

  private EMPTY: Exam = {
    dueDate: null,
    experiences: "",
    scoring: "",
    resit: false,
    label: ""
  }

  @Input() exam: Exam = this.EMPTY;
  @Input() parentSubject: Subject;
  @Input() isNew: boolean;


  constructor(private examService: ExamService,
              private route: ActivatedRoute,
              private router: Router,
              private snackBar: MatSnackBar) {
    if(this.exam){
      return
    }
    this.route.params.subscribe(param => {
      examService.getExam(param.id).subscribe(exam => {
        this.exam = exam;
      }, error => {
          console.log('This project doesn\'t exist!');
          router.navigate(['/']);
      });
    });
  }

  ngOnInit(): void {
    if(this.isNew){
      this.exam = this.PLACE_HOLDER; // placeholder
    }
  }

  save() {
    this.examService.updateExam(this.exam).subscribe(_ => {
      this.snackBar.open('Updated successfully', 'Close', { duration: 3000 });
    }, error => this.snackBar.open('Error!', 'Close', { duration: 3000 }));
  }

  create() {
    if (!this.parentSubject) {
      this.snackBar.open('Please choose a subject!', 'Close', { duration: 3000 });
      return;
    }
    this.examService.createExam(this.parentSubject, this.exam).subscribe((response: any) => {
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
    this.examService.deleteExam(this.exam.id).subscribe(res => {
      this.snackBar.open("Deleted successfully!", "Close",{duration: 3000})
    }, error => this.snackBar.open("Error!", "Close",{duration: 3000}))
  }
}
