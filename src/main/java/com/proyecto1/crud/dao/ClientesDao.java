package com.proyecto1.crud.dao;
import com.proyecto1.crud.conexion.ConexionMySql;
import com.proyecto1.crud.dto.ClientesDto;
import com.proyecto1.crud.dto.ProductoDto;
import com.proyecto1.crud.storage.ProductoTecnologicos.Producto;
import java.sql.Statement;
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
public class ClientesDao {

    @Autowired
    private ConexionMySql conexionMySql;

    
    //Metodo de Obtener todos los clientes
    public List<ClientesDto> ObtenerClientes() throws SQLException {

        List<ClientesDto> clientes = new ArrayList<>();
        String query = "SELECT * FROM clientes";

        try (var conn = conexionMySql.Conectar();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ClientesDto cliente = new ClientesDto();
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setCorreo(rs.getString("correo"));
                cliente.setTelefono(rs.getString("telefono"));
                cliente.setDireccion(rs.getString("direccion"));

                clientes.add(cliente);
            }

        } catch (SQLException e) {
            throw e;
        }

        return clientes;
    }

    // Método para obtener un cliente por su ID
    public ClientesDto ObtenerClientePorId(int id) throws SQLException {
        String query = "SELECT * FROM clientes WHERE id = ?";

        try (var conn = conexionMySql.Conectar();
            PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ClientesDto cliente = new ClientesDto();
                    cliente.setId(rs.getInt("id"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellido(rs.getString("apellido"));
                    cliente.setCorreo(rs.getString("correo"));
                    cliente.setTelefono(rs.getString("telefono"));
                    cliente.setDireccion(rs.getString("direccion"));

                    return cliente;
                } else {
                    return null; // No se encontró el cliente
                }
            }

        } catch (SQLException e) {
            throw e;
        }
}

    //Funcion de Actualizar cliente
    public boolean actualizarCliente(ClientesDto cliente) throws SQLException {
        String query = "UPDATE clientes SET nombre = ?, apellido = ?, correo = ?, telefono = ?, direccion = ? WHERE id = ?";

        try (var conn = conexionMySql.Conectar()) {
            conn.setAutoCommit(false); // Iniciar transacción

            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, cliente.getNombre());
                ps.setString(2, cliente.getApellido());
                ps.setString(3, cliente.getCorreo());
                ps.setString(4, cliente.getTelefono());
                ps.setString(5, cliente.getDireccion());
                ps.setInt(6, cliente.getId());

                int filasAfectadas = ps.executeUpdate();

                conn.commit(); // Confirmar cambios si no hay error

                return filasAfectadas > 0;

            } catch (SQLException ex) {
                conn.rollback(); // Revertir cambios en caso de error
                throw ex;
            } finally {
                conn.setAutoCommit(true); // Restaurar comportamiento por defecto
            }
        }
    }

    //Funcion de Eliminar cliente
    public boolean eliminarClientePorId(int id) throws SQLException {
    String query = "DELETE FROM clientes WHERE id = ?";

        try (var conn = conexionMySql.Conectar()) {
            conn.setAutoCommit(false); // Iniciar transacción

            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, id);

                int filasAfectadas = ps.executeUpdate();

                conn.commit(); // Confirmar si fue exitoso

                return filasAfectadas > 0;

            } catch (SQLException ex) {
                conn.rollback(); // Revertir en caso de error
                throw ex;
            } finally {
                conn.setAutoCommit(true); // Restaurar valor por defecto
            }
        }
    }

    //Funciuon de Crear cliente
    public int insertarCliente(ClientesDto cliente) throws SQLException {
    String query = "INSERT INTO clientes (nombre, apellido, correo, telefono, direccion) VALUES (?, ?, ?, ?, ?)";

    try (var conn = conexionMySql.Conectar()) {
        conn.setAutoCommit(false); // Iniciar transacción

        try (PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

                ps.setString(1, cliente.getNombre());
                ps.setString(2, cliente.getApellido());
                ps.setString(3, cliente.getCorreo());
                ps.setString(4, cliente.getTelefono());
                ps.setString(5, cliente.getDireccion());

                int filas = ps.executeUpdate();

                if (filas == 0) {
                    throw new SQLException("No se insertó ningún cliente.");
                }

                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        int idGenerado = rs.getInt(1);
                        conn.commit();
                        return idGenerado;
                    } else {
                        throw new SQLException("No se obtuvo el ID generado.");
                    }
                }

            } catch (SQLException ex) {
                conn.rollback();
                throw ex;
            } finally {
                conn.setAutoCommit(true);
            }
        }
    }


}


