package com.utn.productos_api.dto;

import com.utn.productos_api.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoResponseDTO {
    @Schema(description = "ID del producto", example = "1")
    private Long id;
    @Schema(description = "Nombre del producto", example = "Laptop")
    private String nombre;
    @Schema(description = "Descripcion del producto", example = "Laptop de ultima generacion")
    private String descripcion;
    @Schema(description = "Precio del producto", example = "1200.50")
    private Double precio;
    @Schema(description = "Stock disponible del producto", example = "50")
    private Integer stock;
    @Schema(description = "Categoria del producto", example = "ELECTRONICA")
    private Categoria categoria;
}
