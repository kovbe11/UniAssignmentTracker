import { AfterViewInit, Component } from '@angular/core';
import { SubjectService } from '../service/subject.service';
import { Subject } from '../model/Subject';

@Component({
  selector: 'app-my-subjects-list',
  templateUrl: 'my-subjects-list.component.html',
})
export class MySubjectsListComponent implements AfterViewInit {
  // typesOfShoes: string[] = ['Boots', 'Clogs', 'Loafers', 'Moccasins', 'Sneakers'];

  mySubjects: Subject[] = [];

  constructor(private subjectService: SubjectService) {}

  ngAfterViewInit(): void {
    this.subjectService.getSubscribedSubjects().subscribe((data) => {
      this.mySubjects = data;
    });
  }
}
