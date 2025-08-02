package com.proyecto1.crud.implementss;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.proyecto1.crud.dao.ClientesDao;
import com.proyecto1.crud.dto.ClientesDto;
import com.proyecto1.crud.interfaz.IClientesService;

@Service
public class ImpClientes implements IClientesService {
    private final ClientesDao clienteDao;

    public ImpClientes(ClientesDao clienteDao) {
        this.clienteDao = clienteDao;
    }

    @Override
    public List<ClientesDto> ObtenerClientes() {
        try {
            return clienteDao.ObtenerClientes();
        } catch (SQLException e) {
            // Manejo de excepciones
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public ClientesDto ObtenerClientePorId(int id) {
        try {
            return clienteDao.ObtenerClientePorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean actualizarCliente(ClientesDto cliente) {
        try {
            return clienteDao.actualizarCliente(cliente);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarCliente(int id) {
        try {
            return clienteDao.eliminarClientePorId(id);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ClientesDto crearCliente(ClientesDto cliente) {
        try {
            int idGenerado = clienteDao.insertarCliente(cliente);
            cliente.setId(idGenerado);
            return cliente;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
