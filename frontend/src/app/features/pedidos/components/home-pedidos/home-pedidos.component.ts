import { Component, inject, OnInit } from '@angular/core';
import { TablaPedidosComponent } from '../tabla-pedidos/tabla-pedidos.component';
import { PedidoService } from '../../services/pedido.service.ts.service';
import { Pedido } from '../../interfaces/pedido.interface';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-home-pedidos',
  standalone: true,
  imports: [TablaPedidosComponent, HttpClientModule],
  providers: [PedidoService],
  templateUrl: './home-pedidos.component.html',
  styleUrl: './home-pedidos.component.css'
})
export class HomePedidosComponent {


}