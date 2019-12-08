import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClientesComponent } from './components/clientes/clientes.component';
import { ClilentesFormComponent } from './components/clientes-form/clientes-form.component';
import { LoginComponent } from './components/login/login.component';
import { AthenticatedGuard } from './guards/athenticated.guard';
import { NoAthenticatedGuard } from './guards/no-athenticated.guard';
import { RoleGuardGuard } from './guards/role-guard.guard';
import { FacturaDetailComponent } from './components/factura-detail/factura-detail.component';
import { FacturasFormComponent } from './components/facturas-form/facturas-form.component';
import { ProductosComponent } from './components/productos/productos.component';
import { ProductoFormComponent } from './components/producto-form/producto-form.component';

const routes: Routes = [
  { path: 'productos', component: ProductosComponent },
  { path: 'productos/form', component: ProductoFormComponent, canActivate: [NoAthenticatedGuard, RoleGuardGuard], data: { role: 'ROLE_ADMIN' } },
  { path: 'productos/form/:id', component: ProductoFormComponent, canActivate: [NoAthenticatedGuard, RoleGuardGuard], data: { role: 'ROLE_ADMIN' } },
  { path: 'productos/page/:page', component: ProductosComponent },
  { path: 'clientes', component: ClientesComponent, canActivate: [NoAthenticatedGuard] },
  { path: 'clientes/page/:page', component: ClientesComponent, canActivate: [NoAthenticatedGuard] },
  { path: 'clientes/form', component: ClilentesFormComponent, canActivate: [NoAthenticatedGuard, RoleGuardGuard], data: { role: 'ROLE_ADMIN' } },
  { path: 'clientes/form/:id', component: ClilentesFormComponent, canActivate: [NoAthenticatedGuard, RoleGuardGuard], data: { role: 'ROLE_ADMIN' } },
  { path: 'facturas/form/:clienteId', component: FacturasFormComponent, canActivate: [NoAthenticatedGuard, RoleGuardGuard], data: { role: 'ROLE_USER' } },
  { path: 'facturas/:id', component: FacturaDetailComponent, canActivate: [NoAthenticatedGuard, RoleGuardGuard], data: { role: 'ROLE_USER' } },

  { path: 'login', component: LoginComponent, canActivate: [AthenticatedGuard] },
  { path: '**', component: ClientesComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
