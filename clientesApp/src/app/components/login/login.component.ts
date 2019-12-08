import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Usuario } from '../../model/usuario';
import Swal from 'sweetalert2';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styles: []
})
export class LoginComponent implements OnInit {

  formLogin: FormGroup;
  usuario: Usuario;

  constructor(
    private _authService: AuthService,
    private router: Router
  ) {
    this.usuario = new Usuario();
    this.formLogin = new FormGroup({
      username: new FormControl(null, Validators.required),
      password: new FormControl(null, Validators.required)
    });
  }

  ngOnInit() {
    // if (this._authService.isAuthenticated()) {
    //   this.router.navigate(['/']);
    // }
  }

  public login() {
    this.usuario = { ...this.formLogin.value };
    this._authService.login(this.usuario).subscribe(res => {
      this._authService.guardarUsuario(res.access_token);
      this._authService.guardarToken(res.access_token);
      this.router.navigate(['/clientes']);
      let usuario = this._authService.usuario;
      Swal.fire('Login', `Hola ${usuario.username}, has iniciado sesión con éxito!`, 'success')
    }, e => {
      if (e.status == 400) Swal.fire('Error', 'Credenciales Incorrectas', 'error');
    });
  }

}
