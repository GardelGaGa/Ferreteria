/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;



import modelo.Usuario;
import modelo.UsuariosDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController {
    private UsuariosDAO usuarioDAO;

    public UsuarioController(Connection conexion) {
        this.usuarioDAO = new UsuariosDAO(conexion);
    }

    public void insertarUsuario(Usuario usuario) {
        try {
            usuarioDAO.insertarUsuario(usuario);
            System.out.println("Usuario insertado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al insertar usuario: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

     public List<Usuario> obtenerUsuarios() {
        try {
            ResultSet resultSet = usuarioDAO.obtenerUsuarios();
            List<Usuario> usuarios = new ArrayList<>();
            while (resultSet.next()) {
                Usuario usuario = new Usuario(
                    resultSet.getInt("idUsuario"),
                    resultSet.getString("nombre"),
                    resultSet.getString("apellido"),
                    resultSet.getString("cargo"),
                    resultSet.getString("nombreusuario"),
                    resultSet.getString("contraseña"),
                    resultSet.getString("correoelectronico"),
                    resultSet.getString("telefono"),
                    resultSet.getDate("fechacontratacion"),
                    resultSet.getDouble("salario")
                );
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (SQLException ex) {
            System.out.println("Error al obtener usuarios: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public void actualizarUsuario(Usuario usuario) {
        try {
            usuarioDAO.actualizarUsuario(usuario);
            System.out.println("Usuario actualizado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al actualizar usuario: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void eliminarUsuario(int idUsuario) {
        try {
            usuarioDAO.eliminarUsuario(idUsuario);
            System.out.println("Usuario eliminado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al eliminar usuario: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public String obtenerNombreUsuarioActual(String nombreUsuario) {
        try {
            int idUsuario = usuarioDAO.obtenerIdUsuarioActual(nombreUsuario);
            return usuarioDAO.obtenerNombreUsuarioActual(idUsuario);
        } catch (SQLException ex) {
            System.out.println("Error al obtener el nombre de usuario actual: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public String obtenerCargoPorNombreUsuario(String nombreUsuario) {
        try {
            int idUsuario = usuarioDAO.obtenerIdUsuarioActual(nombreUsuario);
            return usuarioDAO.obtenerCargoUsuario(idUsuario);
        } catch (SQLException ex) {
            System.out.println("Error al obtener el cargo del usuario actual: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
}
