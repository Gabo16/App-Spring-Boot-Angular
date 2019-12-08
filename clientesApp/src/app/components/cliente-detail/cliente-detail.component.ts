import { Component, OnInit, Input } from '@angular/core';
import { Cliente } from '../../model/cliente';
import { ClienteService } from '../../services/cliente.service';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import { HttpEventType, HttpHeaders } from '@angular/common/http';
import { ModalService } from '../../services/modal.service';
import { AuthService } from '../../services/auth.service';
import { Factura } from '../../model/factura';
import { FacturaService } from 'src/app/services/factura.service';

@Component({
  selector: 'detalle-cliente',
  templateUrl: './cliente-detail.component.html',
  styleUrls: ['./cliente-detail.component.css']
})
export class ClienteDetailComponent implements OnInit {
  @Input() cliente: Cliente;
  fotoSeleccionada: File;
  progreso: number;

  constructor(
    private _clienteService: ClienteService,
    private _modalService: ModalService,
    private _authService: AuthService,
    private _facturaService: FacturaService
  ) {
  }

  ngOnInit() {
  }

  seleccionarFoto(event) {
    this.fotoSeleccionada = event.target.files[0];
    this.progreso = 0;
    if (this.fotoSeleccionada.type.indexOf('image') < 0) {
      Swal.fire('Error Upload:', 'El archivo debe ser del tipo imagen', 'error');
      this.fotoSeleccionada = null;
    }
  }

  subirFoto() {
    if (this.fotoSeleccionada) {
      this._clienteService.subirArchivo(this.fotoSeleccionada, this.cliente.id).subscribe(
        event => {
          if (event.type === HttpEventType.UploadProgress) {
            this.progreso = Math.round((event.loaded / event.total) * 100);
          } else if (event.type === HttpEventType.Response) {
            let response: any = event.body;
            this.cliente = response.cliente as Cliente;
          }
          this._modalService.notificarUpload.emit(this.cliente);
          Swal.fire('La imagen se ha subido', 'Imagen actualizada con exito!', 'success');
        },
        e => {
          console.error(e);
        }
      );
    } else {
      Swal.fire('Error Upload:', 'Debes selecionar una imagen', 'error');
    }

  }

  cerrarModal() {
    this._modalService.cerrarModal();
    this.progreso = 0;
    this.fotoSeleccionada = null;
  }

  public delete(factura: Factura): void {
    const swalWithBootstrapButtons = Swal.mixin({
      customClass: {
        confirmButton: 'btn btn-success',
        cancelButton: 'btn btn-danger mr-2'
      },
      buttonsStyling: false
    })
    swalWithBootstrapButtons.fire({
      title: '¿Estas seguro?',
      text: `¿Quieres eliminar la factura ${factura['descripcion']}?`,
      icon: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Si, eliminar!',
      cancelButtonText: 'No, cancelar!',
      reverseButtons: true
    }).then((result) => {
      if (result.value) {
        this._facturaService.delete(factura.id).subscribe(
          res => {
            this.cliente.facturas = this.cliente.facturas.filter(f => f !== factura);
            swalWithBootstrapButtons.fire(
              'Eliminada!',
              `Factura ${factura['descripcion']} eliminada con exito`,
              'success'
            )
          },
          err => console.error(err)
        );
      }
    });
  }



}
