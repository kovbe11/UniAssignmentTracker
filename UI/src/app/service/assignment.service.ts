import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Assignment } from '../model/Assignment';

@Injectable()
export class AssignmentService {
  constructor(private http: HttpClient) {}

  createAssignment(assignment: Assignment) {}

  updateAssignment(assignment: Assignment) {}

  deleteAssignment(id: number) {}
}
