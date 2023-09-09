package com.example.demosistemaVentas.Entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "domicilio")
public class Domicilio extends BaseEntidad{
    private String calle;
    private int numero;
    private String localidad;

    // RELACION ONE TO MANY UNIDIRECCIONAL (Domicilio-Pedido)
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "domicilio_id")
    @Builder.Default
    private List<Pedido> pedidosDe = new ArrayList<>();

        // RELACION ONE TO MANY UNIDIRECCIONAL (Domicilio-Cliente)
        @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
        @JoinColumn(name = "cliente_id")
        private Cliente cliente;
    public void PedidosDe(Pedido pedidos) {
        pedidosDe.add(pedidos);

    }

    }

