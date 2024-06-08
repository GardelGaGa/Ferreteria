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
import java.util.List;
import modelo.Categoria;

public class CategoriaDAO {
    private Connection conexion;

    public CategoriaDAO(Connection conexion) {
        this.conexion = conexion;
    }

    public void agregarCategoria(Categoria categoria) throws SQLException {
        String sql = "INSERT INTO Categorias (nombre, descripcion) VALUES (?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, categoria.getNombre());
            statement.setString(2, categoria.getDescripcion());
            statement.executeUpdate();
        }
    }

    public List<Categoria> obtenerTodasCategorias() throws SQLException {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM Categoria";
        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Categoria categoria = new Categoria(
                    resultSet.getInt("idCategoria"),
                    resultSet.getString("nombre"),
                    resultSet.getString("descripcion")
                );
                categorias.add(categoria);
            }
        }
        return categorias;
    }

    public void actualizarCategoria(Categoria categoria) throws SQLException {
        String sql = "UPDATE Categorias SET nombre=?, descripcion=? WHERE idCategoria=?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, categoria.getNombre());
            statement.setString(2, categoria.getDescripcion());
            statement.setInt(3, categoria.getIdCategoria());
            statement.executeUpdate();
        }
    }

    public void eliminarCategoria(int idCategoria) throws SQLException {
        String sql = "DELETE FROM Categorias WHERE idCategoria=?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idCategoria);
            statement.executeUpdate();
        }
    }
}
