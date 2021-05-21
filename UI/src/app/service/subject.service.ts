import { Injectable } from '@angular/core';
import { Subject } from '../model/Subject';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class SubjectService {
  constructor(private http: HttpClient) {}

  private api = 'http://localhost:8080/api';

  getAllSubjects(): Observable<Subject[]> {
    return this.http.get<Subject[]>(this.api + '/subjects');
  }

  getSubscribedSubjects(): Observable<Subject[]> {
    return this.http.get<Subject[]>(this.api + '/subjects/subscribed');
  }

  subscribeToSubject(subject: Subject): Observable<any> {
    return this.http.post(this.api + '/subjects/subscribe/' + subject.id, '');
  }

  unsubscribeFromSubject(subject: Subject): Observable<any> {
    return this.http.post(this.api + '/subjects/unsubscribe/' + subject.id, '');
  }

  getSubject(id: number): Observable<Subject>{
    return this.http.get<Subject>(this.api + '/subjects/' + id)
  }

  updateSubject(subject: Subject) {
    return this.http.patch(this.api + '/subjects/' + subject.id, {
      id: subject.id,
      name: subject.name,
      description: subject.description,
      scoring: subject.scoring,
      officialCredit: subject.officialCredit,
      experiencedCredit: subject.experiencedCredit,
      assignments: [],
      projects: [],
      exams: []
    });
  }

  deleteSubject(id: number) {
    return this.http.delete(this.api + '/subjects/' + id);
  }

  createSubject(subject: Subject) {
    subject.id = 0;
    return this.http.post(this.api + '/subjects', subject);
  }
}
