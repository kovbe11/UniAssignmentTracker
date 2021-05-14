import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthenticationService } from './authentication.service';

@Injectable({ providedIn: 'root' })
export class AuthGuardService implements CanActivate {
  constructor(public authenticationService: AuthenticationService, public router: Router) {}

  canActivate(): boolean {
    if (this.authenticationService.isAuthenticated) {
      return true;
    }

    this.router.navigate(['/login']);
    return true;
  }
}
