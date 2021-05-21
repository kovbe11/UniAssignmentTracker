import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Assignment } from '../model/Assignment';
import { Observable } from 'rxjs';
import { Subject } from '../model/Subject';
import { Exam } from '../model/Exam';
import { Project } from '../model/Project';

@Injectable()
export class AssignmentService {
  private api = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {
  }

  getAssignment(id: number): Observable<Assignment> {
    return this.http.get<Assignment>(this.api + '/assignments/' + id);
  }

  createAssignment(subject: Subject, assignment: Assignment) {
    return this.http.post(this.api + '/subjects/' + subject.id + '/assignments', assignment);
  }

  createProjectAssignment(subject: Subject, project: Project, assignment: Assignment) {
    return this.http.post(this.api + '/subjects/' + subject.id + '/projects/' + project.id + '/assignments', assignment);
  }

  updateAssignment(assignment: Assignment) {
    return this.http.patch(this.api + '/assignments/' + assignment.id, assignment);
  }

  deleteAssignment(id: number) {
    return this.http.delete(this.api + '/assignments/' + id);
  }
}
