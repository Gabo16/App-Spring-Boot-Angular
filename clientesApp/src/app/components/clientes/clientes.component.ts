import { Component, OnInit } from '@angular/core';
import { Cliente } from '../../model/cliente';
import { ClienteService } from '../../services/cliente.service';
import Swal from 'sweetalert2';
import { ActivatedRoute, Router } from '@angular/router';
import { ModalService } from '../../services/modal.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-clientes',
  templateUrl: './clientes.component.html',
  styles: []
})
export class ClientesComponent implements OnInit {

  clientes: Cliente[];
  clienteSeleccionado: Cliente;
  paginador: any;
  urlPaginator: string = "/clientes/page";

  constructor(
    private _clientesService: ClienteService,
    private activatedRoute: ActivatedRoute,
    private _modalService: ModalService,
    private _authService: AuthService
  ) { }

  ngOnInit() {
    this.activatedRoute.paramMap.subscribe(param => {
      let page = Number(param.get('page')) || 0;

      this._clientesService.getClientes(page).subscribe(
        response => {
          this.clientes = response.content;
          this.paginador = response;
        },
        err => console.log(err)
      );
    });

    this._modalService.notificarUpload.subscribe(cliente => {
      this.clientes = this.clientes.map(clienteOriginal => {
        if (cliente.id === clienteOriginal.id) clienteOriginal.foto = cliente.foto;
        return clienteOriginal;
      });
    });
  }

  deleteCliente(c: Cliente): void {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger mr-2'
      },
      buttonsStyling: false
    })
    swalWithBootstrapButtons.fire({
      title: '¿Estas seguro?',
      text: `¿Quieres eliminar al cliente ${c.nombre} ${c.apellido}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this._clientesService.deleteCliente(c.id).subscribe(
          res => {
            this.clientes = this.clientes.filter(cliente => cliente !== c);
            swalWithBootstrapButtons.fire(
              'Eliminado!',
              `Cliente ${c.nombre} ${c.apellido} eliminado con exito`,
              'success'
            )
          },
          err => console.error(err)
        );
      }
    });
  }

  abrirModal(cliente: Cliente) {
    this.clienteSeleccionado = cliente;
    this._modalService.abrirModal();
  }


}
