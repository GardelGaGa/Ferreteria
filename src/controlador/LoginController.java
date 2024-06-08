/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.SQLException;
import modelo.UsuariosDAO;
import vista.PuntodeVentaPanel;



public class LoginController {

    private UsuariosDAO usuariosDAO;
    private Connection conexion;
    private int idUsuarioLogueado = -1;
     private String nombreUsuarioLogueado;
    private String cargoUsuarioLogueado;

    public LoginController(Connection conexion) {
        this.conexion = conexion;
        this.usuariosDAO = new UsuariosDAO(conexion);
    }

    public void setIdUsuarioLogueado(int idUsuarioLogueado) {
        this.idUsuarioLogueado = idUsuarioLogueado;
    }

    public boolean iniciarSesion(String nombreUsuario, String contraseña) {
         try {
            boolean esValido = usuariosDAO.verificarUsuario(nombreUsuario, contraseña);
            System.out.println("¿Usuario válido?: " + esValido); // Impresión de depuración
            if (esValido) {
                idUsuarioLogueado = usuariosDAO.obtenerIdUsuarioActual(nombreUsuario);
                nombreUsuarioLogueado = nombreUsuario;
                cargoUsuarioLogueado = usuariosDAO.obtenerCargoUsuario(idUsuarioLogueado);
                System.out.println("ID de usuario logueado: " + idUsuarioLogueado); // Impresión de depuración
                System.out.println("Cargo de usuario logueado: " + cargoUsuarioLogueado); // Impresión de depuración
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public int getIdUsuarioLogueado() {
        return idUsuarioLogueado;
    }

    public String getCargoUsuarioLogueado() {
        return cargoUsuarioLogueado;
    }
    
    public String getNombreUsuarioLogueado() {
        return nombreUsuarioLogueado;
    }

    public boolean login(String nombreUsuario, String contraseña) {
        try {
            if (usuariosDAO.verificarUsuario(nombreUsuario, contraseña)) {
                idUsuarioLogueado = usuariosDAO.obtenerIdUsuarioActual(nombreUsuario); // Obtener y almacenar el ID del usuario logueado
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
