package com.proyecto1.crud.dao;

import com.proyecto1.crud.storage.ProductoTecnologicos.Producto;
import com.proyecto1.crud.storage.ProductoTecnologicos;
import com.proyecto1.crud.conexion.ConexionMySql;
import com.proyecto1.crud.dto.ProductoDto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductoDao {

    //Definimos la conexion a la base de datos
    @Autowired
    private ConexionMySql conexionMySql;

    //Constructor de la conexion
    public ProductoDao(ConexionMySql conexionMySql) {
        this.conexionMySql = conexionMySql;
    }

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

    //Metodo de obtener productos desde la base de datos
    public List<ProductoDto> obtenerTodosMySql() throws SQLException {

        List<ProductoDto> productos = new ArrayList<>();
        String query = "SELECT * FROM productos";

        try (var conn = conexionMySql.Conectar();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ProductoDto producto = new ProductoDto();
                producto.setId(rs.getInt("id"));
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setTipo(rs.getString("tipo"));
                producto.setPrecio(rs.getDouble("precio"));
                producto.setPeso(rs.getDouble("peso"));

                productos.add(producto);
            }

        } catch (SQLException e) {
            throw e;
        }

        return productos;
    }
}

    


