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
import modelo.Proveedor;
import modelo.ProveedorDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProveedorController {
    private ProveedorDAO proveedorDAO;

    public ProveedorController(Connection conexion) {
        this.proveedorDAO = new ProveedorDAO(conexion);
    }

    public void agregarProveedor(Proveedor proveedor) {
        try {
            proveedorDAO.agregarProveedor(proveedor);
            System.out.println("Proveedor agregado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al agregar proveedor: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public List<Proveedor> obtenerTodosProveedores() {
        try {
            return proveedorDAO.obtenerTodosProveedores();
        } catch (SQLException ex) {
            System.out.println("Error al obtener proveedores: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public void actualizarProveedor(Proveedor proveedor) {
        try {
            proveedorDAO.actualizarProveedor(proveedor);
            System.out.println("Proveedor actualizado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al actualizar proveedor: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void eliminarProveedor(int idProveedor) {
        try {
            proveedorDAO.eliminarProveedor(idProveedor);
            System.out.println("Proveedor eliminado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al eliminar proveedor: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}