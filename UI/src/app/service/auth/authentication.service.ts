import { jwtToUser, User } from '../../model/User';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(private http: HttpClient) {
    this.currentUserSubject = new BehaviorSubject<User>(
      //we use the jwt stored in localstorage
      JSON.parse(localStorage.getItem('currentUser'))
    );
    this.currentUser = this.currentUserSubject.asObservable();
  }

  //for convenience
  public get currentUserValue(): User {
    return this.currentUserSubject.value;
  }

  //for convenience
  public get isAuthenticated(): boolean {
    return !!this.currentUserValue;
  }

  private processJwt(data: any): User {
    // convert the jwt to a user
    const user = jwtToUser(data.token);
    // store user details and jwt token in local storage to keep user logged in between page refreshes
    localStorage.setItem('currentUser', JSON.stringify(user));
    // update current user
    this.currentUserSubject.next(user);
    return user;
  }

  login(username: string, password: string): Observable<User> {
    //we try logging in, and let the client handle the errors.
    return this.http.post<any>('http://localhost:8080/api/login', { username, password }).pipe(
      map((data) => {
        return this.processJwt(data);
      })
    );
  }

  register(username: string, password: string) {
    //we try logging in, and let the client handle the errors.
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
