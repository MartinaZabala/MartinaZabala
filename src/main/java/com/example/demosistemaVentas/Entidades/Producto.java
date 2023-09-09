package com.example.demosistemaVentas.Entidades;

import java.io.Serializable;
import com.example.demosistemaVentas.Otros.TipoProducto;
import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Producto")
public class Producto extends BaseEntidad {
    private TipoProducto tipo;
    private int tiempoEstimadoCocina;
    private String denominacion;
    private Double precioVenta;
    private Double precioCompra;
    private int StockActual;
    private int StockMinimo;
    private String unidadMedida;
    private String foto;
    private String receta;

}