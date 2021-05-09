import { Injectable } from '@angular/core';
import { Subject } from '../model/Subject';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class SubjectService {
  constructor(private http: HttpClient) {}

  getAllSubjects(): Observable<Subject[]> {
    return this.http.get<Subject[]>('http://localhost:8080/api/subjects');
  }

  getSubscribedSubjects(): Observable<Subject[]> {
    return this.http.get<Subject[]>('http://localhost:8080/api/subscribed');
  }

  subscribeToSubject(subject: Subject) {}

  unsubscribeFromSubject(subject: Subject) {}

  upsertSubject(subject: Subject) {
    if (subject.id) {
    } else {
    }
  }

  addSubject(subject: Subject) {}

  deleteSubject(id: number) {}
}
