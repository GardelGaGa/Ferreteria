/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import modelo.Producto;
import modelo.ProductoDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductoController {
    private ProductoDAO productoDAO;

    public ProductoController(Connection conexion) {
        this.productoDAO = new ProductoDAO(conexion);
    }


 
    public List<Producto> obtenerTodosProductos() {
        try {
            return productoDAO.obtenerTodosProductos();
        } catch (SQLException ex) {
            System.out.println("Error al obtener productos: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
    
        public void agregarProducto(Producto producto) {
        try {
            productoDAO.agregarProducto(producto);
            System.out.println("Producto agregado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al agregar producto: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    public void actualizarProducto(Producto producto) {
        try {
            productoDAO.actualizarProducto(producto);
            System.out.println("Producto actualizado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al actualizar producto: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void eliminarProducto(int idProducto) {
        try {
            productoDAO.eliminarProducto(idProducto);
            System.out.println("Producto eliminado correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al eliminar producto: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    public List<Producto> buscarProductos(String termino) {
        try {
            return productoDAO.buscarProductos(termino);
        } catch (SQLException ex) {
            System.out.println("Error al buscar productos: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
}