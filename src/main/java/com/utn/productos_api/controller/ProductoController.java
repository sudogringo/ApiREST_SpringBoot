package com.utn.productos_api.controller;

import com.utn.productos_api.dto.ActualizarStockDTO;
import com.utn.productos_api.dto.ProductoDTO;
import com.utn.productos_api.dto.ProductoResponseDTO;
import com.utn.productos_api.model.Categoria;
import com.utn.productos_api.model.Producto;
import com.utn.productos_api.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
@Tag(name = "Productos", description = "API para la gestion de productos")
public class ProductoController {
    private final ProductoService productoService;
    private final ModelMapper modelMapper;

    public ProductoController(ProductoService productoService, ModelMapper modelMapper) {
        this.productoService = productoService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Crea un nuevo producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida")
    })
    @PostMapping
    public ResponseEntity<ProductoResponseDTO> crearProducto(@Valid @RequestBody ProductoDTO productoDTO) {
        Producto producto = modelMapper.map(productoDTO, Producto.class);
        Producto nuevoProducto = productoService.crearProducto(producto);
        ProductoResponseDTO response = modelMapper.map(nuevoProducto, ProductoResponseDTO.class);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene todos los productos")
    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> obtenerTodos() {
        List<Producto> productos = productoService.obtenerTodos();
        List<ProductoResponseDTO> response = productos.stream()
                .map(producto -> modelMapper.map(producto, ProductoResponseDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un producto por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto obtenido exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> obtenerPorId(@PathVariable Long id) {
        Producto producto = productoService.obtenerPorId(id)
                .orElseThrow(() -> new com.utn.productos_api.exception.ProductoNotFoundException("Producto no encontrado con id: " + id));
        ProductoResponseDTO response = modelMapper.map(producto, ProductoResponseDTO.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene productos por categoria")
    @ApiResponse(responseCode = "200", description = "Lista de productos obtenida exitosamente")
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<List<ProductoResponseDTO>> obtenerPorCategoria(@PathVariable Categoria categoria) {
        List<Producto> productos = productoService.obtenerPorCategoria(categoria);
        List<ProductoResponseDTO> response = productos.stream()
                .map(producto -> modelMapper.map(producto, ProductoResponseDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Actualiza un producto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Producto actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> actualizarProducto(@PathVariable Long id, @Valid @RequestBody ProductoDTO productoDTO) {
        Producto productoActualizado = modelMapper.map(productoDTO, Producto.class);
        Producto producto = productoService.actualizarProducto(id, productoActualizado);
        ProductoResponseDTO response = modelMapper.map(producto, ProductoResponseDTO.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Actualiza el stock de un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stock actualizado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud invalida"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @PatchMapping("/{id}/stock")
    public ResponseEntity<ProductoResponseDTO> actualizarStock(@PathVariable Long id, @Valid @RequestBody ActualizarStockDTO actualizarStockDTO) {
        Producto producto = productoService.actualizarStock(id, actualizarStockDTO.getStock());
        ProductoResponseDTO response = modelMapper.map(producto, ProductoResponseDTO.class);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un producto por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Producto eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
