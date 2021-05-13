import { Component, Input, OnInit } from '@angular/core';
import { Project } from '../../model/Project';
import { Subject } from '../../model/Subject';
import { Exam } from '../../model/Exam';

@Component({
  selector: 'app-edit-exam',
  templateUrl: './edit-exam.component.html',
  styleUrls: ['./edit-exam.component.css']
})
export class EditExamComponent implements OnInit {

  @Input() exam: Exam
  @Input() parentSubject: Subject

  constructor() { }

  ngOnInit(): void {
  }

}
