package com.proyecto1.crud.implementss;

import com.proyecto1.crud.dto.ProductoDto;
import com.proyecto1.crud.interfaz.IproductoNew;
import com.proyecto1.crud.dao.ProductoDao;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ImpProducto implements IproductoNew {

    private final ProductoDao productoDao;

    public ImpProducto(ProductoDao productoDao) {
        this.productoDao = productoDao;
    }

    @Override
    public List<ProductoDto> obtenerTodosMySql(){
        try {
            return productoDao.obtenerTodosMySql();
        } catch (SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();
            return List.of();
        }
    }


    @Override
    public List<ProductoDto> obtenerTodosservice() {
        return productoDao.obtenerTodos();
    }

    @Override
    public Optional<ProductoDto> obtenerPorIdservice(int id) {
        return productoDao.obtenerPorId(id);
    }

    @Override
    public ProductoDto crearProductoservice(String nombre, String descripcion, String tipo, double precio, double peso) {
        return productoDao.crearProducto(nombre, descripcion, tipo, precio, peso);
    }

    @Override
    public Optional<ProductoDto> actualizarProductoService(int id, String nombre, String descripcion, String tipo, double precio, double peso) {
        return productoDao.actualizarProducto(id, nombre, descripcion, tipo, precio, peso);
    }

    @Override
    public boolean eliminarProductoService(int id) {
        return productoDao.eliminarProducto(id);
    }
}
