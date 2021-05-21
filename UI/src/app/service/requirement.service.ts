import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { assignmentToRequirement, examToRequirement, Requirement } from '../model/Requirement';
import { map } from 'rxjs/internal/operators';
import { SubjectService } from './subject.service';
import { flatten } from '@angular/compiler';

@Injectable()
export class RequirementService {
  constructor(private http: HttpClient, private subjectService: SubjectService) {
  }

  getRequirements(): Observable<Requirement[]> {
    return this.subjectService.getSubscribedSubjects().pipe(
      map(data => {
        let requirements = data.map(subject => {
          return [subject.exams.map(exam => examToRequirement(exam, subject)),
            subject.assignments.map(assignment => assignmentToRequirement(assignment, subject))];
        });
        return flatten(flatten(requirements));
      })
    );
  }
}
