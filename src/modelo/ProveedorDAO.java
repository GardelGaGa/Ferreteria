/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author alary
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Proveedor;

public class ProveedorDAO {
    private Connection conexion;

    public ProveedorDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void agregarProveedor(Proveedor proveedor) throws SQLException {
        String sql = "INSERT INTO proveedores (nombreempresa, rfc, direccion, telefono, correoelectronico) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, proveedor.getNombreEmpresa());
            statement.setString(2, proveedor.getDireccion());
            statement.setString(3, proveedor.getTelefono());
            statement.setString(4, proveedor.getCorreoElectronico());
            statement.setString(5, proveedor.getRfc());
            statement.executeUpdate();
        }
    }

    public List<Proveedor> obtenerTodosProveedores() throws SQLException {
        List<Proveedor> proveedores = new ArrayList<>();
        String sql = "SELECT * FROM Proveedores";
        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Proveedor proveedor = new Proveedor(
                    resultSet.getInt("idProveedor"),
                    resultSet.getString("nombreempresa"),
                        resultSet.getString("rfc"),
                    resultSet.getString("direccion"),
                    resultSet.getString("telefono"),
                    resultSet.getString("correoelectronico")                 
                );
                proveedores.add(proveedor);
            }
        }
        return proveedores;
    }

    public void actualizarProveedor(Proveedor proveedor) throws SQLException {
        String sql = "UPDATE proveedores SET nombreempresa=?, rfc=?, direccion=?, telefono=?, correoelectronico=? WHERE idproveedor=?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, proveedor.getNombreEmpresa());
            statement.setString(2, proveedor.getRfc());
            statement.setString(3, proveedor.getDireccion());
            statement.setString(4, proveedor.getTelefono());
            statement.setString(5, proveedor.getCorreoElectronico());
            statement.setInt(6, proveedor.getIdProveedor());
            statement.executeUpdate();
        }
    }

    public void eliminarProveedor(int idProveedor) throws SQLException {
        String sql = "DELETE FROM proveedores WHERE idproveedor=?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idProveedor);
            statement.executeUpdate();
        }
    }
}
