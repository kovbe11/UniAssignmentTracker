import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Exam } from '../model/Exam';
import { Observable, throwError } from 'rxjs';
import { Subject } from '../model/Subject';
import { throwIfEmpty } from 'rxjs/operators';

@Injectable()
export class ExamService {
  private api = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {
  }

  getExam(id: number): Observable<Exam> {
    return this.http.get<Exam>(this.api + '/exams/' + id);
  }

  createExam(subject: Subject, exam: Exam) {
    return this.http.post(this.api + '/subjects/' + subject.id + '/exams', exam);
  }

  updateExam(exam: Exam) {
    return this.http.patch(this.api + '/exams/' + exam.id, exam);
  }

  deleteExam(id: number) {
    return this.http.delete(this.api + '/exams/' + id);
  }
}
