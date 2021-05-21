import { Component, Input, OnInit } from '@angular/core';
import { Exam } from '../../../model/Exam';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-exam',
  templateUrl: './exam.component.html',
  styleUrls: ['./exam.component.css']
})
export class ExamComponent implements OnInit {

  @Input() exam: Exam
  @Input() basicInfo: boolean
  @Input() editable: boolean

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  edit() {
    if (!this.editable) {
      return;
    }
    this.router.navigate(['/exam/' + this.exam.id]);
  }
}
