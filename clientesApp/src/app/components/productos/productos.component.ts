import { Component, OnInit } from '@angular/core';
import { ProductoService } from '../../services/producto.service';
import { Producto } from '../../model/producto';
import { AuthService } from '../../services/auth.service';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-productos',
  templateUrl: './productos.component.html',
  styles: []
})
export class ProductosComponent implements OnInit {

  productos: Producto[];
  paginador: any;
  urlPaginator: string = "/productos/page"

  constructor(
    private _productoService: ProductoService,
    private _authService: AuthService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(params => {
      let page = Number(params.get('page')) || 0;
      this._productoService.getProductos(++page).subscribe(res => {
        this.productos = res.content;
        this.paginador = res;
      });
    })

  }

  deleteProducto(producto: Producto) {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger mr-2'
      },
      buttonsStyling: false
    })
    swalWithBootstrapButtons.fire({
      title: '¿Estas seguro?',
      text: `¿Quieres eliminar al cliente ${producto.nombre}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this._productoService.delete(producto.id).subscribe(
          res => {
            this.productos = this.productos.filter(cliente => cliente !== producto);
            swalWithBootstrapButtons.fire(
              'Eliminado!',
              `Cliente ${producto.nombre}eliminado con exito`,
              'success'
            )
          },
          err => console.error(err)
        );
      }
    });
  }

}
