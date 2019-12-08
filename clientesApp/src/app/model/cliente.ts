import { Factura } from './factura';

export class Cliente {
    id: number;
    nombre: string;
    apellido: string;
    email: string;
    foto: string;
    createAt: string;
    facturas: Array<Factura> = [];
}