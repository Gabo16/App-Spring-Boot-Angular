import { Injectable } from '@angular/core';
import { Usuario } from '../model/usuario';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private _usuario: Usuario;
  private _token: string;
  private url: string = "http://localhost:8080/oauth/token";

  constructor(private http: HttpClient) { }

  public get usuario(): Usuario {
    if (this._usuario != null) {
      return this._usuario;
    } else if (this._usuario == null && sessionStorage.getItem('usuario') != null) {
      this._usuario = JSON.parse(sessionStorage.getItem('usuario')) as Usuario;
      return this._usuario;
    }
    return new Usuario();
  }

  public get token(): string {
    if (this._token != null) {
      return this._token;
    } else if (this._token == null && sessionStorage.getItem('token') != null) {
      this._token = sessionStorage.getItem('token');
      return this._token;
    }
    return null;
  }

  public hasRole(role: string): boolean {
    if (this.usuario.roles.includes(role)) return true;
    return false;
  }

  public isAuthenticated(): boolean {
    if (this.token != null && this.usuario.username != undefined) return true;
    return false;
  }

  public login(usuario: Usuario): Observable<any> {
    const credentials = btoa('angularapp' + ':' + '12345');
    const httpHeaders = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization': 'Basic ' + credentials
    });

    let params = new URLSearchParams();
    params.set('grant_type', 'password');
    params.set('username', usuario.username);
    params.set('password', usuario.password);
    return this.http.post<any>(this.url, params.toString(), { headers: httpHeaders });
  }

  public logout(): void {
    this._token = null;
    this._usuario = null;
    sessionStorage.clear();
  }

  public guardarUsuario(access_token: string): void {
    let payload = this.obtenerPayload(access_token);
    this._usuario = new Usuario();
    this._usuario.nombre = payload.nombre;
    this._usuario.apellido = payload.apellido;
    this._usuario.email = payload.email;
    this._usuario.username = payload.user_name;
    this._usuario.roles = payload.authorities;
    sessionStorage.setItem('usuario', JSON.stringify(this._usuario));

  }
  public guardarToken(access_token: string): void {
    this._token = access_token;
    sessionStorage.setItem('token', this._token);

  }

  public obtenerPayload(access_token: string): any {
    if (access_token != null) return JSON.parse(atob(access_token.split('.')[1]));
    return null;
  }
}
