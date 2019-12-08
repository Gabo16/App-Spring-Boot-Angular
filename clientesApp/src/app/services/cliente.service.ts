import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpEvent } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { Cliente } from '../model/cliente';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';




@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  url: string = "http://localhost:8080/api/v1/clientes";

  constructor(
    private _http: HttpClient,
    private router: Router
  ) { }

  public getClientes(page: number): Observable<any> {
    return this._http.get(`${this.url}/page/${page}`).pipe(
      map((c: any) => {
        (c.content).map(cliente => {
          // cliente.nombre = cliente.nombre.toUpperCase()
        });
        return c;
      })
    );
  }

  public saveCliente(c: Cliente): Observable<any> {
    return this._http.post(this.url, c).pipe(
      catchError(e => {

        if (e.status == 400 && e.error.message) {
          console.log(e.error.message);
          Swal.fire('Error al guardar cliente', e.error.message, 'error');
          return throwError(e);
        } else if (e.error.message) {
          console.log(e.error.message);
        }
        return throwError(e);
      })
    );
  }

  public getCliente(id: number): Observable<any> {
    return this._http.get(`${this.url}/cliente/${id}`).pipe(
      catchError(e => {
        return throwError(e);
      })
    );
  }

  public updateCliente(c: Cliente): Observable<any> {
    return this._http.put(`${this.url}/${c.id}`, c).pipe(
      catchError(e => {
        return throwError(e);
      })
    );
  }

  public deleteCliente(id: number): Observable<any> {
    return this._http.delete(`${this.url}/${id}`).pipe(
      catchError(e => {
        if (e.error.message) {
          console.log(e.error.message);
        }
        return throwError(e);
      })
    );
  }

  public subirArchivo(archivo: File, id): Observable<HttpEvent<{}>> {
    let formData = new FormData();
    formData.append("archivo", archivo);
    formData.append("id", id);

    const req = new HttpRequest('POST', `${this.url}/uploads`, formData, {
      reportProgress: true
    });

    return this._http.request(req);
  }
}
