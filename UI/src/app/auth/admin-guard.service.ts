import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthenticationService } from './authentication.service';

@Injectable({ providedIn: 'root' })
export class AdminGuardService implements CanActivate {
  constructor(public authenticationService: AuthenticationService, public router: Router) {}

  canActivate(): boolean {
    const user = this.authenticationService.currentUserValue;
    if (user && user.roles && user.roles.includes('ROLE_ADMIN')) {
      return true;
    }

    this.router.navigate(['/login']);
    return false;
  }
}
