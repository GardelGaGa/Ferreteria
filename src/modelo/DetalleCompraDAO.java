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
import java.sql.SQLException;

public class DetalleCompraDAO {
    private Connection conexion;

    public DetalleCompraDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void agregarDetalleCompra(DetalleCompra detalleCompra) throws SQLException {
        String sql = "INSERT INTO DetalleCompra (fk_idcompra, fk_idproducto, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, detalleCompra.getFkIdCompra());
            statement.setInt(2, detalleCompra.getFkIdProducto());
            statement.setInt(3, detalleCompra.getCantidad());
            statement.setDouble(4, detalleCompra.getPrecioUnitario());
            statement.setDouble(5, detalleCompra.getSubtotal());
            statement.executeUpdate();
        }
    }
    public void actualizarDetalleCompra(DetalleCompra detalleCompra) throws SQLException {
        String sql = "UPDATE DetalleCompra SET fk_idproducto = ?, cantidad = ?, precio_unitario = ?, subtotal = ? WHERE fk_idcompra = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, detalleCompra.getFkIdProducto());
            statement.setInt(2, detalleCompra.getCantidad());
            statement.setDouble(3, detalleCompra.getPrecioUnitario());
            statement.setDouble(4, detalleCompra.getSubtotal());
            statement.setInt(5, detalleCompra.getFkIdCompra());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al actualizar detalle de compra", e);
        }
    }

    // Método para eliminar un detalle de compra de la base de datos
    public void eliminarDetalleCompra(int fkIdCompra, int fkIdProducto) throws SQLException {
        String sql = "DELETE FROM DetalleCompra WHERE fk_idcompra = ? AND fk_idproducto = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, fkIdCompra);
            statement.setInt(2, fkIdProducto);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Error al eliminar detalle de compra", e);
        }
    }

    // Método para obtener todos los detalles de una compra específica (opcional)
    // Este método podría ser útil dependiendo de tus necesidades
    // public List<DetalleCompra> obtenerDetallesPorCompra(int fkIdCompra) throws SQLException {
    //     List<DetalleCompra> detalles = new ArrayList<>();
    //     String sql = "SELECT * FROM DetalleCompra WHERE fk_idcompra = ?";
    //     try (PreparedStatement statement = conexion.prepareStatement(sql)) {
    //         statement.setInt(1, fkIdCompra);
    //         ResultSet rs = statement.executeQuery();
    //         while (rs.next()) {
    //             DetalleCompra detalle = new DetalleCompra(
    //                 rs.getInt("fk_idcompra"),
    //                 rs.getInt("fk_idproducto"),
    //                 rs.getInt("cantidad"),
    //                 rs.getDouble("precio_unitario"),
    //                 rs.getDouble("subtotal")
    //             );
    //             detalles.add(detalle);
    //         }
    //     } catch (SQLException e) {
    //         throw new SQLException("Error al obtener detalles de la compra", e);
    //     }
    //     return detalles;
    // }
}