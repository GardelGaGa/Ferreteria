package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.CabeceraVenta;
import modelo.DetalleVentaPdf;

public class Ctrl_RegistrarVenta {

    // Último id de la cabecera registrada
    public static int idCabeceraRegistrada;
    java.math.BigDecimal iDColVar;

    /**
     * Método para guardar la cabecera de venta
     */
    public boolean guardar(CabeceraVenta objeto) {
        boolean respuesta = false;
        Connection conexion = null;

        try {
            // Obtener la conexión
            conexion = new Conexion().conectar();
            if (conexion != null) {
                // Ajusta la consulta SQL según la estructura de tu tabla
                PreparedStatement consulta = conexion.prepareStatement("INSERT INTO tb_cabecera_venta (id, valor_pagar, fecha_venta, estado, forma_pago, observaciones) VALUES (?, ?, ?, ?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
                consulta.setInt(1, 0); // id (auto-incremental en la base de datos)
                consulta.setDouble(2, objeto.getValorPagar());
                consulta.setString(3, objeto.getFechaVenta());
                consulta.setInt(4, objeto.getEstado());
                consulta.setString(5, objeto.getFormaPago());
                consulta.setString(6, objeto.getObservaciones());

                if (consulta.executeUpdate() > 0) {
                    respuesta = true;
                }

                ResultSet rs = consulta.getGeneratedKeys();
                if (rs.next()) {
                    iDColVar = rs.getBigDecimal(1);
                    idCabeceraRegistrada = iDColVar.intValue();
                }

                consulta.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar cabecera de venta: " + e);
        } finally {
            // Cerrar recursos
            try {
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return respuesta;
    }

    /**
     * Método para guardar el detalle de venta
     */
    public boolean guardarDetalle(DetalleVentaPdf objeto) {
        boolean respuesta = false;
        Connection conexion = null;
        PreparedStatement consulta = null;

        try {
            // Obtener la conexión
            conexion = new Conexion().conectar();
            if (conexion != null) {
                // Ajusta la consulta SQL según la estructura de tu tabla
                consulta = conexion.prepareStatement("INSERT INTO tb_detalle_venta (id, id_cabecera_venta, id_producto, cantidad, precio_unitario, subtotal, descuento, iva, total_pagar, estado, codigo_promocional, fecha_entrega) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                consulta.setInt(1, 0); // id (auto-incremental en la base de datos)
                consulta.setInt(2, idCabeceraRegistrada);
                consulta.setInt(3, objeto.getIdProducto());
                consulta.setInt(4, objeto.getCantidad());
                consulta.setDouble(5, objeto.getPrecioUnitario());
                consulta.setDouble(6, objeto.getSubTotal());
                consulta.setDouble(7, objeto.getDescuento());
                consulta.setDouble(8, objeto.getIva());
                consulta.setDouble(9, objeto.getTotalPagar());
                consulta.setInt(10, objeto.getEstado());
                consulta.setString(11, objeto.getCodigoPromocional());
                consulta.setString(12, objeto.getFechaEntrega());

                if (consulta.executeUpdate() > 0) {
                    respuesta = true;
                }

                consulta.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al guardar detalle de venta: " + e);
        } finally {
            // Cerrar recursos
            try {
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return respuesta;
    }

    /**
     * Método para actualizar la cabecera de una venta
     */
    public boolean actualizar(CabeceraVenta objeto, int idCabeceraVenta) {
        boolean respuesta = false;
        Connection conexion = null;
        PreparedStatement consulta = null;

        try {
            // Obtener la conexión
            conexion = new Conexion().conectar();
            if (conexion != null) {
                // Ajusta la consulta SQL según la estructura de tu tabla
                consulta = conexion.prepareStatement("UPDATE tb_cabecera_venta SET estado = ?, forma_pago = ?, observaciones = ? WHERE idCabeceraVenta = ?");
                consulta.setInt(1, objeto.getEstado());
                consulta.setString(2, objeto.getFormaPago());
                consulta.setString(3, objeto.getObservaciones());
                consulta.setInt(4, idCabeceraVenta);

                if (consulta.executeUpdate() > 0) {
                    respuesta = true;
                }

                consulta.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar cabecera de venta: " + e);
        } finally {
            // Cerrar recursos
            try {
                if (conexion != null) conexion.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return respuesta;
    }
}
