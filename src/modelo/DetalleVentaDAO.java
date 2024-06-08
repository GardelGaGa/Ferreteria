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

public class DetalleVentaDAO {

    private Connection conexion;

    public DetalleVentaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public boolean agregarVenta(Venta venta, List<DetalleVenta> detallesVenta) throws SQLException {
        String sqlVenta = "INSERT INTO Ventas (fechaventa, fk_idusuario, totalventa, metodopago) VALUES (?, ?, ?, ?)";
        String sqlDetalle = "INSERT INTO DetalleVenta (fk_idventa, fk_idproducto_, cantidad, preciounitario, subtotal) VALUES (?, ?, ?, ?, ?)";

        try {
            conexion.setAutoCommit(false); // Iniciar transacción

            // Insertar venta
            try (PreparedStatement statementVenta = conexion.prepareStatement(sqlVenta, PreparedStatement.RETURN_GENERATED_KEYS)) {
                statementVenta.setTimestamp(1, new java.sql.Timestamp(venta.getFechaVenta().getTime()));
                statementVenta.setInt(2, venta.getFkIdUsuario());
                statementVenta.setDouble(3, venta.getTotalVenta());
                statementVenta.setString(4, venta.getMetodoPago());
                int affectedRows = statementVenta.executeUpdate();

                if (affectedRows == 0) {
                    conexion.rollback();
                    return false;
                }

                // Obtener el ID de la venta generada
                int idVenta;
                try (ResultSet generatedKeys = statementVenta.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        idVenta = generatedKeys.getInt(1);
                    } else {
                        conexion.rollback();
                        return false;
                    }
                }

                // Insertar detalles de venta
                try (PreparedStatement statementDetalle = conexion.prepareStatement(sqlDetalle)) {
                    for (DetalleVenta detalle : detallesVenta) {
                        // Insertar detalle de venta
                        statementDetalle.setInt(1, idVenta);
                        statementDetalle.setInt(2, detalle.getFkIdProducto());
                        statementDetalle.setInt(3, detalle.getCantidad());
                        statementDetalle.setDouble(4, detalle.getPrecioUnitario());
                        statementDetalle.setDouble(5, detalle.getSubtotal());
                        statementDetalle.executeUpdate();
                    }
                }

                conexion.commit(); // Confirmar transacción
                return true;
            }
        } catch (SQLException e) {
            conexion.rollback(); // Revertir transacción en caso de error
            e.printStackTrace();
            return false;
        } finally {
            conexion.setAutoCommit(true); // Restaurar modo de confirmación automática
        }
    }

    public void eliminarDetalleVenta(int codigo) throws SQLException {
        String sql = "DELETE FROM DetalleVenta WHERE fk_idproducto = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, codigo);
            statement.executeUpdate();
        }
    }

    public void cancelarVenta() throws SQLException {
        String sql = "DELETE FROM DetalleVenta";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.executeUpdate();
        }
    }
}
