import { Component, Input, OnInit } from '@angular/core';
import { Project } from '../../model/Project';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  @Input() project: Project

  constructor() {
  }

  ngOnInit(): void {
  }

}
