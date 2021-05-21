import { Component, Input, OnInit } from '@angular/core';
import { Project } from '../../../model/Project';
import { Router } from '@angular/router';

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  @Input() project: Project
  @Input() editable: boolean


  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }

  edit() {
    if (!this.editable) {
      return;
    }
    this.router.navigate(['/project/' + this.project.id]);
  }
}
