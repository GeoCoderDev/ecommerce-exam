import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Pedido } from '../../interfaces/pedido.interface';
import { PedidoService } from '../../services/pedido.service.ts.service';
import { FechaFormatoPipe } from '../../../../pipes/fecha-formato.pipe';
import { EstadoPedidoPipe } from '../../../../pipes/estado-pedido.pipe';

@Component({
  selector: 'app-tabla-pedidos',
  standalone: true,
  imports: [CommonModule, FechaFormatoPipe, EstadoPedidoPipe],
  providers: [PedidoService],
  templateUrl: './tabla-pedidos.component.html',
  styleUrls: ['./tabla-pedidos.component.css'],
})
export class TablaPedidosComponent implements OnInit {
  pedidos: Pedido[] = [];
  pedidosPaginados: Pedido[] = [];
  paginaActual = 1;
  itemsPorPagina = 5;
  totalItems = 0;
  private pedidoService = inject(PedidoService);

  ngOnInit(): void {
    this.pedidoService.getPedidos().subscribe((pedidos: Pedido[]) => {
      this.pedidos = pedidos;
      this.totalItems = pedidos.length;
      this.actualizarPaginacion();
    });
  }

  get totalPaginas() {
    return Math.ceil(this.totalItems / this.itemsPorPagina);
  }

  cambiarPagina(pagina: number) {
    this.paginaActual = pagina;
    this.actualizarPaginacion();
  }

  actualizarPaginacion() {
    const inicio = (this.paginaActual - 1) * this.itemsPorPagina;
    const fin = inicio + this.itemsPorPagina;
    this.pedidosPaginados = this.pedidos.slice(inicio, fin);
  }

  getMinimo() {
    return Math.min(this.paginaActual * this.itemsPorPagina, this.totalItems);
  }
}