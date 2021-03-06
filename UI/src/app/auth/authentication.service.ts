import { jwtToUser, User } from '../model/User';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(
      JSON.parse(localStorage.getItem('currentUser'))
    );
    this.currentUser = this.currentUserSubject.asObservable();
  }

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  public get isAuthenticated(): boolean {
    return !!this.currentUserValue;
  }

  private processJwt(data: any): User {
    const user = jwtToUser(data.token);
    // store user details and jwt token in local storage to keep user logged in between page refreshes
    localStorage.setItem('currentUser', JSON.stringify(user));
    this.currentUserSubject.next(user);
    return user;
  }

  login(username: string, password: string): Observable<User> {
    return this.http.post<any>('http://localhost:8080/api/login', { username, password }).pipe(
      map((data) => {
        return this.processJwt(data);
      })
    );
  }

  register(username: string, password: string) {
    return this.http.post<any>('http://localhost:8080/api/register', { username, password }).pipe(
      map((data) => {
        return this.processJwt(data);
      }),
    );
  }

  logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
    this.currentUserSubject.next(null);
  }
}
