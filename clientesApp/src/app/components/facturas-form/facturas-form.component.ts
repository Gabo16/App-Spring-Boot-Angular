import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Factura } from '../../model/factura';
import { ClienteService } from '../../services/cliente.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { flatMap, map } from 'rxjs/operators';
import { Producto } from '../../model/producto';
import { FacturaService } from '../../services/factura.service';;
import { MatAutocompleteSelectedEvent } from '@angular/material/autocomplete';
import { ItemFactura } from '../../model/item-factura';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-facturas-form',
  templateUrl: './facturas-form.component.html',
  styles: []
})
export class FacturasFormComponent implements OnInit {

  factura: Factura;
  formFactura: FormGroup;
  autocompleteControl = new FormControl();
  productosFiltrados: Observable<Producto[]>;

  constructor(
    private _clienteServices: ClienteService,
    private activatedRoute: ActivatedRoute,
    private _facturaService: FacturaService,
    private router: Router
  ) {
    this.factura = new Factura();
  }

  ngOnInit() {
    this.cargarFactura();
    this.productosFiltrados = this.autocompleteControl.valueChanges.pipe(
      map(value => typeof value === 'string' ? value : value.nombre),
      flatMap(value => value ? this._filter(value) : [])
    );
  }

  public cargarFactura(): void {
    this.activatedRoute.paramMap.subscribe(params => {
      let id = Number(params.get('clienteId'));
      if (id) {
        this._clienteServices.getCliente(id).subscribe(res => {
          this.factura.cliente = res.cliente;
          this.formFactura = new FormGroup({
            descripcion: new FormControl(null, Validators.required),
            observacion: new FormControl(null),
            cliente: new FormControl({ value: `${res.cliente.nombre}  ${res.cliente.apellido}`, disabled: true }, Validators.required)
          });
        })
      }
    });
  }

  private _filter(value: string): Observable<Producto[]> {
    const filterValue = value.toLowerCase();

    return this._facturaService.filtrarProductos(filterValue);
  }

  public mostrarNombre(producto?: Producto): string | undefined {
    return producto ? producto.nombre : undefined;
  }

  public seleccionarProducto(event: MatAutocompleteSelectedEvent): void {
    let producto = event.option.value as Producto;


    if (this.existeItem(producto.id)) {
      this.incrementarCantidad(producto.id);
    } else {
      let nuevoItem = new ItemFactura();
      nuevoItem.producto = producto;
      this.factura.items.push(nuevoItem);
    }
    this.autocompleteControl.setValue('');
    event.option.focus();
    event.option.deselect();
  }

  public actualizarCantidad(id: number, event: any): void {
    let cantidad: number = event.target.value as number;
    if (cantidad == 0) return this.deleteItem(id);
    this.factura.items = this.factura.items.map((item: ItemFactura) => {
      if (id === item.producto.id) {
        item.cantidad = cantidad
      }
      return item;
    });
  }

  public existeItem(id: number): boolean {
    let existe = false;
    this.factura.items.forEach((item: ItemFactura) => {
      if (id === item.producto.id) {
        existe = true;
      }
    });
    return existe;
  }

  public incrementarCantidad(id: number): void {
    this.factura.items = this.factura.items.map((item: ItemFactura) => {
      if (id === item.producto.id) {
        ++item.cantidad;
      }
      return item;
    });
  }

  public deleteItem(id: number): void {
    this.factura.items = this.factura.items.filter((item: ItemFactura) => id !== item.producto.id);
  }

  public create(): void {
    if (this.formFactura.get('descripcion').invalid) Swal.fire('Error Factura', 'la descripción es requerida', 'error');
    if (this.factura.items.length == 0) Swal.fire('Error Factura', 'No has agregado productos a la factura', 'error');

    if (this.formFactura.valid && this.factura.items.length > 0) {
      this.factura.descripcion = this.formFactura.get('descripcion').value;
      this.factura.observacion = this.formFactura.get('observacion').value;
      this._facturaService.create(this.factura).subscribe(factura => {
        Swal.fire('Nueva Factura', 'Factura creada con exito!', 'success');
        this.router.navigate(['/clientes']);
      });
    }

  }

}
