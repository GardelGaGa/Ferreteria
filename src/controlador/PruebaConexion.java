/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.sun.jdi.connect.spi.Connection;

/**
 *
 * @author alary
 */
public class PruebaConexion {
    public static void main(String[] args) {
        Conexion conexionDB = new Conexion();
        Connection conexion = (Connection) conexionDB.conectar();
        
        if (conexion != null) {
            System.out.println("Conexión establecida correctamente.");
        } else {
            System.err.println("Error: No se pudo establecer la conexión.");
        }
    }
}
