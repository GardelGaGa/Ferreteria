/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

/**
 *
 * @author alary
 */

import modelo.Venta;
import modelo.VentaDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modelo.DetalleVenta;
import modelo.Producto;
import modelo.ProductoDAO;

public class VentaController {

    private VentaDAO ventaDAO;
    private DetalleVenta detalleVenta;
    private ProductoDAO productoDAO;
    private Connection conexion;
    private Producto producto;

    public VentaController(VentaDAO ventaDAO, Connection conexion) {
        this.conexion = conexion;
        this.ventaDAO = ventaDAO;
        this.producto = producto;
        this.productoDAO = new ProductoDAO(conexion);
    }

    public boolean agregarVenta(Venta venta, List<DetalleVenta> detallesVenta) {
    try {
        boolean ventaGuardada = ventaDAO.agregarVenta(venta, detallesVenta);

        if (ventaGuardada) {
            for (DetalleVenta detalle : detallesVenta) {
                String codigoProducto = detalle.getCodigoProducto();
                int cantidadVendida = detalle.getCantidad();

                // Actualizar el stock después de agregar la venta
                productoDAO.actualizarStock(codigoProducto, cantidadVendida);
            }
        }

        return ventaGuardada;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}





    public List<Venta> obtenerTodasVentas() {
        try {
            return ventaDAO.obtenerTodasVentas();
        } catch (SQLException ex) {
            System.out.println("Error al obtener ventas: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public void actualizarVenta(Venta venta) {
        try {
            ventaDAO.actualizarVenta(venta);
            System.out.println("Venta actualizada correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al actualizar venta: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void eliminarVenta(int idVenta) {
        try {
            ventaDAO.eliminarVenta(idVenta);
            System.out.println("Venta eliminada correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al eliminar venta: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    
}
