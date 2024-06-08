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
import modelo.Categoria;
import modelo.CategoriaDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CategoriaController {
    private CategoriaDAO categoriaDAO;
      private Map<Integer, Categoria> categoriasMap = new HashMap<>();

    public CategoriaController(Connection conexion) {
        this.categoriaDAO = new CategoriaDAO(conexion);
        cargarCategorias();
    }

    public void agregarCategoria(Categoria categoria) {
        try {
            categoriaDAO.agregarCategoria(categoria);
            System.out.println("Categoría agregada correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al agregar categoría: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public List<Categoria> obtenerTodasCategorias() {
        try {
            return categoriaDAO.obtenerTodasCategorias();
        } catch (SQLException ex) {
            System.out.println("Error al obtener categorías: " + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public void actualizarCategoria(Categoria categoria) {
        try {
            categoriaDAO.actualizarCategoria(categoria);
            System.out.println("Categoría actualizada correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al actualizar categoría: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void eliminarCategoria(int idCategoria) {
        try {
            categoriaDAO.eliminarCategoria(idCategoria);
            System.out.println("Categoría eliminada correctamente.");
        } catch (SQLException ex) {
            System.out.println("Error al eliminar categoría: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void cargarCategorias() {
        categoriasMap = new HashMap<>();
        List<Categoria> categorias = obtenerTodasCategorias();
        for (Categoria categoria : categorias) {
            categoriasMap.put(categoria.getIdCategoria(), categoria);
        }
    }

   public Map<Integer, Categoria> getCategoriasMap() {
    return categoriasMap;
}

}
