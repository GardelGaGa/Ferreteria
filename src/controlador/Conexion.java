/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static final String URL = "jdbc:mysql://localhost:3306/ferreteria";
    public static final String USER = "root";
    public static final String CLAVE = "RobinPanzon1";

    public Connection conectar() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, CLAVE);
            System.out.println("Conectado");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return con;
    }
}
