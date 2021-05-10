import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Subject } from '../model/Subject';
import { assignmentToRequirement, examToRequirement, Requirement } from '../model/Requirement';
import { flatMap, map } from 'rxjs/internal/operators';
import { SubjectService } from './subject.service';
import { flatten } from '@angular/compiler';

@Injectable()
export class RequirementService {
  constructor(private http: HttpClient, private subjectService: SubjectService) {}

  getRequirements(): Observable<Requirement[][][]> {
    return this.subjectService.getSubscribedSubjects().pipe(
      map((data) =>
        data.map((value) => [
          value.exams.map(examToRequirement),
          value.assignments.map(assignmentToRequirement),
        ])
      ),
      (source) => {
        console.log(source);
        return source;
      }
    );
  }
}
