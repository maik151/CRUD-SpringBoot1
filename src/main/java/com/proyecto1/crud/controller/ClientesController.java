package com.proyecto1.crud.controller;

import com.proyecto1.crud.dto.ClientesDto;
import com.proyecto1.crud.dto.ProductoDto;
import com.proyecto1.crud.interfaz.IClientesService;
import com.proyecto1.crud.interfaz.IproductoNew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes-services")
public class ClientesController {
    
    private final IClientesService service;

    public ClientesController(IClientesService service) {
        this.service = service;
    }


    // Obtener todos los clientes
    @GetMapping
    public ResponseEntity<List<ClientesDto>> Get_ObtenerClientes() {
        List<ClientesDto> clientes = service.ObtenerClientes();
        return ResponseEntity.ok(clientes);
    }

    // Obtener cliente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ClientesDto> Get_ClientePorId(@PathVariable int id) {
        ClientesDto cliente = service.ObtenerClientePorId(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Crear cliente
    @PostMapping
    public ResponseEntity<ClientesDto> Post_CrearCliente(@RequestBody ClientesDto nuevoCliente) {
        ClientesDto clienteCreado = service.crearCliente(nuevoCliente);
        if (clienteCreado != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // Actualizar cliente
    @PutMapping("/{id}")
    public ResponseEntity<?> Put_ActualizarCliente(@PathVariable int id, @RequestBody ClientesDto clienteActualizado) {
        clienteActualizado.setId(id);
        boolean actualizado = service.actualizarCliente(clienteActualizado);
        if (actualizado) {
            return ResponseEntity.ok("Cliente actualizado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }
    }

    // Eliminar cliente
    @DeleteMapping("/{id}")
    public ResponseEntity<?> Delete_EliminarCliente(@PathVariable int id) {
        boolean eliminado = service.eliminarCliente(id);
        if (eliminado) {
            return ResponseEntity.ok("Cliente eliminado correctamente");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
        }
    }

    
}
