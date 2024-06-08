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
import java.util.Date;
import java.util.List;
import modelo.Compra;

public class CompraDAO {
    private Connection conexion;

    public CompraDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void agregarCompra(Compra compra) throws SQLException {
        String sql = "INSERT INTO Compras (fecha_compra, fk_idproveedor, totalcompra, metodo_pago) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(compra.getFechaCompra().getTime()));
            statement.setInt(2, compra.getFkIdProveedor());
            statement.setDouble(3, compra.getTotalCompra());
            statement.setString(4, compra.getMetodoPago());
            statement.executeUpdate();
        }
    }

    public List<Compra> obtenerTodasCompras() throws SQLException {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT * FROM Compras";
        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int idCompra = resultSet.getInt("idcompra");
                Date fechaCompra = resultSet.getDate("fecha_compra");
                int fkIdProveedor = resultSet.getInt("fk_idproveedor");
                double totalCompra = resultSet.getDouble("totalcompra");
                String metodoPago = resultSet.getString("metodo_pago");
                Compra compra = new Compra(idCompra, fechaCompra, fkIdProveedor, totalCompra, metodoPago);
                compras.add(compra);
            }
        }
        return compras;
    }

    public void actualizarCompra(Compra compra) throws SQLException {
        String sql = "UPDATE Compras SET fecha_compra=?, fk_idproveedor=?, totalcompra=?, metodo_pago=? WHERE idcompra=?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(compra.getFechaCompra().getTime()));
            statement.setInt(2, compra.getFkIdProveedor());
            statement.setDouble(3, compra.getTotalCompra());
            statement.setString(4, compra.getMetodoPago());
            statement.setInt(5, compra.getIdCompra());
            statement.executeUpdate();
        }
    }

    public void eliminarCompra(int idCompra) throws SQLException {
        String sql = "DELETE FROM Compras WHERE idcompra=?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idCompra);
            statement.executeUpdate();
        }
    }
}
