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
import modelo.Compra;
import modelo.CompraDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CompraController {
    private CompraDAO compraDAO;

    public CompraController(Connection conexion) {
        this.compraDAO = new CompraDAO(conexion);
    }

    public void agregarCompra(Compra compra) {
        try {
            compraDAO.agregarCompra(compra);
            System.out.println("Compra agregada correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al agregar compra: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public List<Compra> obtenerTodasCompras() {
        try {
            return compraDAO.obtenerTodasCompras();
        } catch (SQLException ex) {
            System.out.println("Error al obtener compras: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public void actualizarCompra(Compra compra) {
        try {
            compraDAO.actualizarCompra(compra);
            System.out.println("Compra actualizada correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al actualizar compra: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void eliminarCompra(int idCompra) {
        try {
            compraDAO.eliminarCompra(idCompra);
            System.out.println("Compra eliminada correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al eliminar compra: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}