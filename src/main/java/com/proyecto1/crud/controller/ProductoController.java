package com.proyecto1.crud.controller;

import com.proyecto1.crud.dto.ProductoDto;
import com.proyecto1.crud.interfaz.IproductoNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final IproductoNew service;
    @Autowired
    public ProductoController(IproductoNew service) {
        this.service = service;
    }

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<ProductoDto>> obtenerTodos() {
        List<ProductoDto> productos = service.obtenerTodosservice();
        return ResponseEntity.ok(productos);
    }

    // Obtener producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> obtenerPorId(@PathVariable int id) {
        Optional<ProductoDto> producto = service.obtenerPorIdservice(id);
        return producto.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<ProductoDto> crearProducto(@RequestBody ProductoDto dto) {
        ProductoDto creado = service.crearProductoservice(dto.getNombre(), dto.getDescripcion(), dto.getTipo(), dto.getPrecio(), dto.getPeso());
        return ResponseEntity.ok(creado);
    }

    // Actualizar un producto existente
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ProductoDto> actualizarProducto(@PathVariable int id, @RequestBody ProductoDto dto) {
        Optional<ProductoDto> actualizado = service.actualizarProductoService(id, dto.getNombre(), dto.getDescripcion(),dto.getTipo(), dto.getPrecio(), dto.getPeso());
        return actualizado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    // Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable int id) {
        boolean eliminado = service.eliminarProductoService(id);
        if (eliminado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Obtener todos los productos desde SQL
    @GetMapping("/mysql")
    public ResponseEntity<List<ProductoDto>> obtenerTodosMySql() {
        List<ProductoDto> productos = service.obtenerTodosMySql();
        return ResponseEntity.ok(productos);
    }
}
