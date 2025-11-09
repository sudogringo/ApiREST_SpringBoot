package com.utn.productos_api.dto;

import com.utn.productos_api.model.Categoria;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    @NotBlank(message = "El nombre no puede estar vacio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;
    @Size(max = 500, message = "La descripcion no puede tener mas de 500 caracteres")
    private String descripcion;
    @NotNull(message = "El precio no puede ser nulo")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    private Double precio;
    @NotNull(message = "El stock no puede ser nulo")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;
    @NotNull(message = "La categoria no puede ser nula")
    private Categoria categoria;
}
