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
@Table(name = "rubro")
public class Rubro extends BaseEntidad{
    private String denominacion;
    // RELACION ONE TO MANY UNIDIRECCIONAL(Rubro-Producto)
    // El Cascadeo propaga las operaciones y orphanRemoval asegura que se elimine la relacionada
// OJO COMO LA CARGA ES POR DEFECTO LAZY SI NO PONGO EAGER ME DA ERROR PORQUE CIERRA LA SESIÓN
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    @JoinColumn(name = "rubro_id")

    // OJO ES IMPORTANTE COLOCAR ESTA ANOTACIÓN SINO ME DA ERROR

    @Builder.Default
    private List<Producto> productos = new ArrayList<>();
    public void agregarProducto(Producto produ) {
        productos.add(produ);
    }
}
