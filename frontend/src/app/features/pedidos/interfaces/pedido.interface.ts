export interface Producto {
  idProducto: string;
  nombre: string;
  descripcion: string;
  precio: number;
  cantidad: number;
}

export interface Cliente {
  idCliente: string;
  nombre: string;
  apellido: string;
  correoElectronico: string;
  numeroTelefono: string;
  direccion: string;
}

export interface Pedido {
  idPedido: string;
  cliente: Cliente;
  productos: Producto[];
  total: number;
  fechaCreacion: string; // Puedes usar Date si prefieres manejarlo como objeto Date
  fechaEntrega: string; // Lo mismo aquí
}

export enum EstadoPedido {
  Pendiente = 'Pendiente',
  EnProceso = 'En Proceso',
  Entregado = 'Entregado',
}
