package com.example.demosistemaVentas.Entidades;
import java.io.Serializable;
import com.example.demosistemaVentas.Otros.Estado;
import com.example.demosistemaVentas.Otros.TipoEnvio;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "pedido")
public class Pedido extends BaseEntidad {
    private String fecha;
    private Estado estado;
    private Date horaEstimadaEntrega;
    private Double total;
    private TipoEnvio tipoEnvio;

    // RELACION ONE TO ONE UNIDIRECCIONAL (factura-Pedido)
    @OneToOne(cascade = CascadeType.ALL, optional = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "factura_id")
    private Factura factura;

    // RELACION ONE TO MANY UNIDIRECCIONAL (DatellePedido-Pedido)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval  = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "pedido_id")
    @Builder.Default
    private List<DetallePedido> Pedidos = new ArrayList<>();
    public void agregarDetalle(DetallePedido detalle) {
        Pedidos.add(detalle);
    }
}