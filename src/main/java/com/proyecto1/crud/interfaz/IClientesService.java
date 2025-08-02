package com.proyecto1.crud.interfaz;
import java.util.List;

import com.proyecto1.crud.dto.ClientesDto;

public interface IClientesService {
    List<ClientesDto> ObtenerClientes();
    ClientesDto ObtenerClientePorId(int id);
    public boolean actualizarCliente(ClientesDto cliente);
    public boolean eliminarCliente(int id);
    public ClientesDto crearCliente(ClientesDto cliente);
}
