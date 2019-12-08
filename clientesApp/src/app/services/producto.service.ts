import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { Producto } from '../model/producto';
import { catchError } from 'rxjs/operators';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  url: string = "http://localhost:8080/api/v1/productos";

  constructor(
    private http: HttpClient,
    private router: Router
  ) { }

  public getProductos(page: number): Observable<any> {
    return this.http.get(`${this.url}?page=${page}`);
  }

  public getProducto(id: number): Observable<any> {
    return this.http.get(`${this.url}/${id}`);
  }

  public save(producto: Producto): Observable<any> {
    return this.http.post(this.url, producto);
  }

  public update(producto: Producto): Observable<any> {
    return this.http.put(`${this.url}/${producto.id}`, producto);
  }

  public delete(id: number): Observable<any> {
    return this.http.delete(`${this.url}/${id}`);
  }

}
