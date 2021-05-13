import { Component, Input, OnInit } from '@angular/core';
import { Exam } from '../../model/Exam';

@Component({
  selector: 'app-exam',
  templateUrl: './exam.component.html',
  styleUrls: ['./exam.component.css']
})
export class ExamComponent implements OnInit {

  @Input() exam: Exam
  @Input() basicInfo: boolean

  constructor() { }

  ngOnInit(): void {
  }

}
