package com.proyecto1.crud.conexion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class ConexionMySql {
    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String pass;

    private Connection cnn;
        
    public Connection Conectar() throws SQLException{
        if (cnn == null || cnn.isClosed()) {
            cnn = DriverManager.getConnection(url, user, pass);
        }
        return cnn;
    }
    public void cerrarConexion(){
        try{
            if(cnn != null && !cnn.isClosed()){
                cnn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}


