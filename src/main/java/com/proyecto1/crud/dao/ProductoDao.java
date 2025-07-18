package com.proyecto1.crud.dao;

import com.proyecto1.crud.storage.ProductoTecnologicos.Producto;
import com.proyecto1.crud.storage.ProductoTecnologicos;
import com.proyecto1.crud.dto.ProductoDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProductoDao {

    private ProductoDto convertirADto(Producto producto) {
        return new ProductoDto(
            producto.getId(),
            producto.getNombre(),
            producto.getDescripcion(),
            producto.getTipo(),
            producto.getPrecio(),
            producto.getPeso()
        );
    }

    public List<ProductoDto> obtenerTodos() {
        List<Producto> productos = ProductoTecnologicos.obtenerTodos();
        return productos.stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    public Optional<ProductoDto> obtenerPorId(int id) {
        Optional<Producto> producto = ProductoTecnologicos.obtenerPorId(id);
        return producto.map(this::convertirADto);
    }

    public ProductoDto crearProducto(String nombre, String descripcion, String tipo, double precio, double peso) {
        Producto nuevo = ProductoTecnologicos.crearProducto(nombre, descripcion, tipo, precio, peso);
        return convertirADto(nuevo);
    }

    public Optional<ProductoDto> actualizarProducto(int id, String nombre, String descripcion, String tipo, double precio, double peso) {
        Optional<Producto> actualizado = ProductoTecnologicos.actualizarProducto(id, nombre, descripcion, tipo, precio, peso);
        return actualizado.map(this::convertirADto);
    }

    public boolean eliminarProducto(int id) {
        return ProductoTecnologicos.eliminarProducto(id);
    }
}
