import { Pipe, PipeTransform } from '@angular/core';
import { EstadoPedido } from '../features/pedidos/interfaces/pedido.interface';
@Pipe({
  name: 'estadoPedido',
  standalone: true,
})
export class EstadoPedidoPipe implements PipeTransform {

  transform(fechaCreacion: string | Date, fechaEntrega: string | Date): EstadoPedido {
    const ahora = new Date();

    // Convertimos las fechas si son strings
    const fechaCreacionDate = typeof fechaCreacion === 'string' ? new Date(fechaCreacion) : fechaCreacion;
    const fechaEntregaDate = typeof fechaEntrega === 'string' ? new Date(fechaEntrega) : fechaEntrega;

    const tiempoRestante = fechaEntregaDate.getTime() - ahora.getTime();
    const unDia = 24 * 60 * 60 * 1000; // Milisegundos en un día

    // Definimos los estados según la diferencia de tiempo
    if (tiempoRestante > unDia) {
      return EstadoPedido.Pendiente;
    } else if (tiempoRestante <= unDia && tiempoRestante > 0) {
      return EstadoPedido.EnProceso;
    } else {
      return EstadoPedido.Entregado;
    }
  }
}
