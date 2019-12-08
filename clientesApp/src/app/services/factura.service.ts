import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Producto } from '../model/producto';
import { Factura } from '../model/factura';

@Injectable({
  providedIn: 'root'
})
export class FacturaService {

  url: string = "http://localhost:8080/api/v1/facturas";

  constructor(private http: HttpClient) { }

  public getFactura(id: number): Observable<any> {
    return this.http.get(`${this.url}/${id}`);
  }

  public delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.url}/${id}`);
  }

  public filtrarProductos(term: string): Observable<Producto[]> {
    return this.http.get<Producto[]>(`${this.url}/filtrado-productos/${term}`);
  }

  public create(factura: Factura): Observable<Factura> {
    return this.http.post<Factura>(this.url, factura);
  }

}
