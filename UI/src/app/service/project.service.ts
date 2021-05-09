import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Project } from '../model/Project';

@Injectable()
export class ProjectService {
  constructor(private http: HttpClient) {}

  createProject(project: Project) {}

  updateProject(project: Project) {}

  deleteProject(id: number) {}
}
