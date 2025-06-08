package com.proyecto1.crud.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductoTecnologicos {

    // Simulando la base de datos con un ArrayList
    private static final List<Producto> productos = new ArrayList<>();
    // Contador para asignar IDs únicos a los productos
    private static int currentId = 1;

    // Clase interna para representar un producto
    public static class Producto {
        private int id;
        private String nombre;
        private String descripcion;
        private String tipo;
        private double precio;
        private double peso;

        public Producto(int id, String nombre, String descripcion, String tipo, double precio, double peso) {
            this.id = id;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.tipo = tipo;
            this.precio = precio;
            this.peso = peso;
        }

        // Getters y setters
        public int getId() { return id; }
        public void setId(int id) { this.id = id; }

        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public String getDescripcion() { return descripcion; }
        public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

        public String getTipo() { return tipo; }
        public void setTipo(String tipo) { this.tipo = tipo; }

        public double getPrecio() { return precio; }
        public void setPrecio(double precio) { this.precio = precio; }

        public double getPeso() { return peso; }
        public void setPeso(double peso) { this.peso = peso; }
    }

    // Métodos CRUD en la misma clase

    // Crear
    public static Producto crearProducto(String nombre, String descripcion, String tipo, double precio, double peso) {
        Producto nuevo = new Producto(currentId++, nombre, descripcion, tipo, precio, peso);
        productos.add(nuevo);
        return nuevo;
    }

    // Obtener todos
    public static List<Producto> obtenerTodos() {
        return productos;
    }

    // Obtener por ID
    public static Optional<Producto> obtenerPorId(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return Optional.of(p);
            }
        }
    return Optional.empty();
    }

    // Actualizar
    public static Optional<Producto> actualizarProducto(int id, String nombre, String descripcion, String tipo, double precio, double peso) {
        Optional<Producto> producto = obtenerPorId(id);
        producto.ifPresent(p -> {
            p.setNombre(nombre);
            p.setDescripcion(descripcion);
            p.setTipo(tipo);
            p.setPrecio(precio);
            p.setPeso(peso);
        });
        return producto;
    }

    // Eliminar
    public static boolean eliminarProducto(int id) {
    for (int i = 0; i < productos.size(); i++) {
        if (productos.get(i).getId() == id) {
            productos.remove(i);
            return true;
        }
    }
    return false;
    }
}