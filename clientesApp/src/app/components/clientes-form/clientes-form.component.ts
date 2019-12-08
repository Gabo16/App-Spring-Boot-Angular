import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Cliente } from '../../model/cliente';
import { ClienteService } from '../../services/cliente.service';
import { Router, ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-clientes-form',
  templateUrl: './clientes-form.component.html',
  styles: []
})
export class ClilentesFormComponent implements OnInit {

  clienteForm: FormGroup;
  cliente: Cliente;
  actualizar: boolean = false;

  constructor(
    private _clienteService: ClienteService,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {
    this.cliente = new Cliente();
    this.clienteForm = new FormGroup({
      id: new FormControl(null),
      nombre: new FormControl(null, [Validators.required, Validators.minLength(3)]),
      apellido: new FormControl(null, [Validators.required, Validators.minLength(3)]),
      email: new FormControl(null, [Validators.required, Validators.email]),
      facturas: new FormControl(null)
    });
  }

  ngOnInit() {
    this.cargarCliente();
  }

  cargarCliente(): void {
    this.activatedRoute.params.subscribe(params => {
      let id = params['id'];
      if (id) {
        this._clienteService.getCliente(id).subscribe(c => {
          this.actualizar = true;
          this.cliente.id = c.cliente.id;
          this.cliente.nombre = c.cliente.nombre;
          this.cliente.apellido = c.cliente.apellido;
          this.cliente.email = c.cliente.email;
          this.clienteForm.setValue({ ...this.cliente });
        }, err => {
          Swal.fire(`Error ${err.status}`, err.error.message, 'error');
        });
      }
    });
  }

  saveCliente(): void {
    this.cliente = { ...this.clienteForm.value };
    this._clienteService.saveCliente(this.cliente).subscribe(
      cliente => {
        this.clienteForm.reset();
        Swal.fire('Cliente Agregado', `Cliente ${this.cliente.nombre}, agregado con exito!`, 'success');
        this.router.navigate(['/clientes']);
      },
      e => {
        if (e.error.errors) {
          Swal.fire('Error Agregando Cliente', `${e.error.errors.join(`, <br>`)}`, 'error');
        }
      }
    );
  }

  updateCliente(): void {
    this.cliente = { ...this.clienteForm.value };
    this._clienteService.updateCliente(this.cliente).subscribe(
      cliente => {
        this.clienteForm.reset();
        Swal.fire('Cliente Actualizado', `Cliente ${this.cliente.nombre}, actualizado con exito!`, 'success');
        this.router.navigate(['/clientes']);
      },
      e => {
        if (e.error.errors) {
          Swal.fire('Error Actualizando Cliente', `${e.error.errors.join(`, <br>`)}`, 'error');
        }

      }
    );
  }

}
