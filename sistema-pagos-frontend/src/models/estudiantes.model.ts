export interface Estudiante {
    id: number;
    codigo: string;
    nombre: string;
    apellidos: string;
    programaId: string;
    foto: string;
}

export interface Pago{
    id: number;
    fecha: string;
    cantidad: number;
    type: string;
    status: string;
    file: string;
    estudiante: Estudiante;
}

export enum TypePago{
    EFECTIVO = 0, CHEQUE = 1, TRANSFERENCIA = 2, DEPOSITO = 3
}

export enum StatusPago{
    CREADO = 0, VALIDADO = 1, RECHAZADO = 2
}