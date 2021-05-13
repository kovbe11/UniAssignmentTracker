import { Component, Input, OnInit } from '@angular/core';
import { Project } from '../../model/Project';
import { Subject } from '../../model/Subject';

@Component({
  selector: 'app-edit-project',
  templateUrl: './edit-project.component.html',
  styleUrls: ['./edit-project.component.css']
})
export class EditProjectComponent implements OnInit {

  @Input() project: Project
  @Input() parentSubject: Subject

  constructor() { }

  ngOnInit(): void {
  }

}
