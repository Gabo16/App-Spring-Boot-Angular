import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CanActivate } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class AthenticatedGuard implements CanActivate {
  constructor(
    private router: Router,
    private _authService: AuthService
  ) { }
  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {
    if (this._authService.isAuthenticated()) {
      this.router.navigate(['/clientes']);
      return false;
    } else {
      return true;
    }
  }
}
