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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.UsuariosDAO;

public class VentaDAO {
    private Connection conexion;
    private UsuariosDAO usuariosDAO;
    private ProductoDAO productoDAO;

    public VentaDAO(Connection conexion) {
        this.conexion = conexion;
        this.productoDAO = new ProductoDAO(conexion);
        this.usuariosDAO = new UsuariosDAO(conexion);
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



    public List<Venta> obtenerTodasVentas() throws SQLException {
        List<Venta> ventas = new ArrayList<>();
        String sql = "SELECT * FROM Ventas";
        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int idVenta = resultSet.getInt("idventa");
                Date fechaVenta = resultSet.getDate("fecha_venta");
                int fkIdUsuario = resultSet.getInt("fk_idusuario");
                double totalVenta = resultSet.getDouble("totalventa");
                String metodoPago = resultSet.getString("metodo_pago");
                Venta venta = new Venta(idVenta, fechaVenta, fkIdUsuario, totalVenta, metodoPago);
                ventas.add(venta);
            }
        }
        return ventas;
    }

    public void actualizarVenta(Venta venta) throws SQLException {
        String sql = "UPDATE Ventas SET fecha_venta=?, fk_idusuario=?, totalventa=?, metodo_pago=? WHERE idventa=?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setDate(1, new java.sql.Date(venta.getFechaVenta().getTime()));
            statement.setInt(2, venta.getFkIdUsuario());
            statement.setDouble(3, venta.getTotalVenta());
            statement.setString(4, venta.getMetodoPago());
            statement.setInt(5, venta.getIdVenta());
            statement.executeUpdate();
        }
    }

    public void eliminarVenta(int idVenta) throws SQLException {
        String sql = "DELETE FROM Ventas WHERE idventa=?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idVenta);
            statement.executeUpdate();
        }
    }

    public Map<String, Integer> obtenerProductosMasVendidos() throws SQLException {
        String query = "SELECT p.idproducto, SUM(dv.cantidad) AS totalVendidos\n"
                + "FROM detalleventa dv\n"
                + "JOIN producto p ON dv.fk_idproducto_ = p.idproducto\n"
                + "GROUP BY p.idproducto\n"
                + "ORDER BY totalVendidos DESC";

        Map<String, Integer> productosMasVendidos = new HashMap<>();

        try (PreparedStatement stmt = conexion.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                productosMasVendidos.put(rs.getString("nombreProducto"), rs.getInt("totalVendidos"));
            }
        }

        return productosMasVendidos;
    }

}
