package com.example.demosistemaVentas.Entidades;

import com.example.demosistemaVentas.Otros.FormaPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Factura")
public class Factura extends BaseEntidad{
    private Date fecha;
    private int numero;
    private double descuento;
    private double total;
    private FormaPago formaPago;

}
