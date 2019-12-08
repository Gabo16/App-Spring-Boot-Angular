import { Component, OnInit } from '@angular/core';
import { Factura } from '../../model/factura';
import { FacturaService } from '../../services/factura.service';
import { ActivatedRoute } from '@angular/router';
import { Cliente } from '../../model/cliente';

@Component({
  selector: 'app-factura-detail',
  templateUrl: './factura-detail.component.html',
  styles: []
})
export class FacturaDetailComponent implements OnInit {

  factura: Factura;

  constructor(
    private _facturaService: FacturaService,
    private activatedRoute: ActivatedRoute
  ) {
    this.activatedRoute.params.subscribe(params => {
      let id = params['id'];
      this._facturaService.getFactura(id).subscribe(res => {
        this.factura = res.factura;
      });
    })
  }

  ngOnInit() {
  }

}
