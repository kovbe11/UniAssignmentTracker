import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Exam } from '../model/Exam';

@Injectable()
export class ExamService {
  constructor(private http: HttpClient) {}

  createExam(exam: Exam) {}

  updateExam(exam: Exam) {}

  deleteExam(id: number) {}
}
