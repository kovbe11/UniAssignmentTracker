import { Component, OnInit } from '@angular/core';
import { Subject } from '../../../model/Subject';
import { SubjectService } from '../../../service/subject.service';
import { Project } from '../../../model/Project';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent implements OnInit {
  allSubjects: Subject[];
  parentSubject: Subject;
  parentProject: Project

  constructor(private subjectService: SubjectService) {}

  ngOnInit(): void {
    this.subjectService.getAllSubjects().subscribe((data) => {
      this.allSubjects = data || [];
    });
  }
}
