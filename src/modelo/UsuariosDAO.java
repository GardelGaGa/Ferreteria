package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class UsuariosDAO {

    private Connection conexion;

    public UsuariosDAO(Connection conexion) {
        this.conexion = conexion;

    }

    public void iniciarConexion() {
        try {
            if (conexion == null || conexion.isClosed()) {
                conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ferreteria", "root", "RobinPanzon1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean verificarUsuario(String nombreUsuario, String contraseña) throws SQLException {
        String query = "SELECT * FROM usuarios WHERE NombreUsuario = ? AND Contraseña = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contraseña);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public void insertarUsuario(Usuario usuario) throws SQLException {
        try {
            PreparedStatement statement = conexion.prepareStatement("INSERT INTO usuarios (nombre, apellido, cargo, nombreusuario, contraseña, correoelectronico, fechacontratacion, salario) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getCargo());
            statement.setString(4, usuario.getNombreUsuario());
            statement.setString(5, usuario.getContraseña());
            statement.setString(6, usuario.getCorreoElectronico());
            // Convertir la fecha de tipo Date a String
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fechaContratacionString = dateFormat.format(usuario.getFechaContratacion());
            statement.setString(7, fechaContratacionString);
            statement.setDouble(8, usuario.getSalario());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet obtenerUsuarios() throws SQLException {
        try {
            PreparedStatement statement = conexion.prepareStatement("SELECT * FROM usuarios");
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void actualizarUsuario(Usuario usuario) throws SQLException {
        try {
            PreparedStatement statement = conexion.prepareStatement("UPDATE usuarios SET nombre = ?, apellido = ?, cargo = ?, nombreusuario = ?, contraseña = ?, correo_electronico = ?, fechacontratacion = ?, salario = ? WHERE idusuario = ?");
            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getApellido());
            statement.setString(3, usuario.getCargo());
            statement.setString(4, usuario.getNombreUsuario());
            statement.setString(5, usuario.getContraseña());
            statement.setString(6, usuario.getCorreoElectronico());
            // Convertir la fecha de tipo Date a String
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fechaContratacionString = dateFormat.format(usuario.getFechaContratacion());
            statement.setString(7, fechaContratacionString);
            statement.setDouble(8, usuario.getSalario());
            statement.setInt(9, usuario.getIdUsuario());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarUsuario(int idUsuario) throws SQLException {
        try {
            PreparedStatement statement = conexion.prepareStatement("DELETE FROM usuarios WHERE idusuario = ?");
            statement.setInt(1, idUsuario);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String obtenerCargoUsuario(int idUsuario) throws SQLException {
        String sql = "SELECT cargo FROM usuarios WHERE idusuario = ?";
        try (PreparedStatement ps = conexion.prepareStatement(sql)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("cargo");
            } else {
                System.out.println("Usuario no encontrado para ID: " + idUsuario);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el cargo del usuario: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public String obtenerNombreUsuarioActual(int idUsuario) throws SQLException {
        String nombreUsuario = null;
        String query = "SELECT nombreusuario FROM usuarios WHERE idusuario = ?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, idUsuario);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    nombreUsuario = resultSet.getString("nombreusuario");
                }
            }
        }
        return nombreUsuario;
    }

    public int obtenerIdUsuarioActual(String nombreUsuario) throws SQLException {
        String query = "SELECT idusuario FROM usuarios WHERE NombreUsuario = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, nombreUsuario);
            System.out.println("Ejecutando consulta: " + stmt); // Impresión de depuración
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int idUsuario = rs.getInt("idusuario");
                    System.out.println("ID de usuario obtenido: " + idUsuario); // Impresión de depuración
                    return idUsuario;
                } else {
                    System.out.println("Usuario no encontrado"); // Impresión de depuración
                    return -1; // Usuario no encontrado
                }
            }
        }
        // Si no se encuentra el usuario, devolver -1
    }

    public String getNombreUsuario(String username) {
        try {
            String query = "SELECT nombre FROM usuarios WHERE username = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("nombre");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String obtenerCargoPorNombreUsuario(String nombreUsuario) throws SQLException {
        String query = "SELECT cargo FROM usuarios WHERE nombreusuario = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, nombreUsuario);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("cargo");
                }
            }
        }
        return null;  // Devuelve null si no se encuentra el usuario
    }

}
