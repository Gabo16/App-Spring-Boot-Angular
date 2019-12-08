import { Component } from '@angular/core';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  titulo = "CLIENTES APP"
  toggled = false;

  constructor(private _authService: AuthService) { }

  public logout(): void {
    this._authService.logout();
  }
}
