import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../service/auth/authentication.service';
import { User } from '../../model/User';

@Component({
  selector: 'app-uni-assignment-tracker',
  templateUrl: './uni-assignment-tracker.component.html',
  styleUrls: ['./uni-assignment-tracker.component.css'],
})
export class UniAssignmentTrackerComponent {

  // mui generated code
  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
    map((result) => result.matches),
    shareReplay()
  );

  currentUser: User;

  constructor(
    private breakpointObserver: BreakpointObserver,
    private router: Router,
    private authenticationService: AuthenticationService
  ) {
    // we need to be able to tell if we are logged in
    this.authenticationService.currentUser.subscribe((x) => (this.currentUser = x));
  }

  logout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

  login() {
    this.router.navigate(['/login']);
  }
}
