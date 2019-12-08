import { Component, OnInit } from '@angular/core';
import { ProductoService } from '../../services/producto.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Producto } from '../../model/producto';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-producto-form',
  templateUrl: './producto-form.component.html',
  styles: []
})
export class ProductoFormComponent implements OnInit {

  productoForm: FormGroup;
  producto: Producto;

  constructor(
    private _productoService: ProductoService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {
    this.productoForm = new FormGroup({
      id: new FormControl(null),
      nombre: new FormControl(null, Validators.required),
      precio: new FormControl(null, Validators.required)
    });
  }

  ngOnInit() {
    this.producto = new Producto();
    this.cargarProducto();
  }

  cargarProducto() {
    this.activatedRoute.params.subscribe(params => {
      let id = params.id;
      if (id) {
        this._productoService.getProducto(id).subscribe(res => {
          this.producto.id = res.producto.id;
          this.producto.nombre = res.producto.nombre;
          this.producto.precio = res.producto.precio;
          this.productoForm.setValue({ ...this.producto });
        }, err => {
          Swal.fire(`Error ${err.status}`, err.error.message, 'error');
        });
      }
    });
  }

  saveProducto() {
    this.producto = { ...this.productoForm.value };
    this._productoService.save(this.producto).subscribe(
      producto => {
        this.productoForm.reset();
        Swal.fire('Producto Agregado', `Producto ${this.producto.nombre}, agregado con exito!`, 'success');
        this.router.navigate(['/productos']);
      },
      e => {
        if (e.error.errors) {
          Swal.fire('Error Agregando Producto', `${e.error.errors.join(`, <br>`)}`, 'error');
        }
      }
    );
  }

  updateProducto() {
    this.producto = { ...this.productoForm.value };
    this._productoService.update(this.producto).subscribe(
      producto => {
        this.productoForm.reset();
        Swal.fire('Producto Actualizado', `Producto ${this.producto.nombre}, actualizado con exito!`, 'success');
        this.router.navigate(['/productos']);
      },
      e => {
        if (e.error.errors) {
          Swal.fire('Error Actualizado Producto', `${e.error.errors.join(`, <br>`)}`, 'error');
        }
      }
    );
  }
}
