import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Project } from '../model/Project';
import { Observable } from 'rxjs';
import { Subject } from '../model/Subject';

@Injectable()
export class ProjectService {
  private api = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getProject(id: number): Observable<Project>{
    return this.http.get<Project>(this.api + '/projects/' + id)
  }

  createProject(subject: Subject, project: Project) {
    return this.http.post(this.api + '/subjects/' + subject.id + '/projects', project)
  }

  updateProject(project: Project) {
    return this.http.patch(this.api + '/projects/' + project.id, project)
  }

  deleteProject(id: number) {
    return this.http.delete(this.api + '/projects/' + id)
  }
}
