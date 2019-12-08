import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, CanActivate, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class RoleGuardGuard implements CanActivate {

  constructor(
    private router: Router,
    private _authService: AuthService
  ) { }
  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {
    const role = next.data['role'] as string;
    if (this._authService.hasRole(role)) {
      return true;
    } else {
      Swal.fire('Acceso denegado', `No tienes acceso`, 'warning');
      this.router.navigate(['/clientes']);
      return false;
    }
  }

}
