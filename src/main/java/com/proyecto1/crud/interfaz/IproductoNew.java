package com.proyecto1.crud.interfaz;

import java.util.List;
import java.util.Optional;
import com.proyecto1.crud.dto.ProductoDto;

public interface IproductoNew {
    ProductoDto crearProductoservice(String nombre, String descripcion, String tipo, double precio, double peso);
    List<ProductoDto> obtenerTodosservice();
    Optional<ProductoDto> obtenerPorIdservice(int id);
    Optional<ProductoDto> actualizarProductoService(int id, String nombre, String descripcion, String tipo, double precio, double peso);
    boolean eliminarProductoService(int id);

    List<ProductoDto> obtenerTodosMySql();
}
