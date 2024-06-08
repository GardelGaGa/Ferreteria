/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductoDAO {
    private Connection conexion;
     

    public ProductoDAO(Connection conexion) {
        this.conexion = conexion;
        if (this.conexion == null) {
            System.err.println("Error: La conexión es null en ProductoDAO");
        }
    }

    public void agregarProducto(Producto producto) throws SQLException {
    String sql = "INSERT INTO producto (codigo, nombre, descripcion, fk_categoria, proveedor, precioCompra, precioVenta, stock) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement statement = conexion.prepareStatement(sql)) {
        statement.setString(1, producto.getCodigo());
        statement.setString(2, producto.getNombre());
        statement.setString(3, producto.getDescripcion());
        statement.setInt(4, producto.getFkCategoria());
        statement.setString(5, producto.getProveedor());
        statement.setDouble(6, producto.getPrecioCompra());
        statement.setDouble(7, producto.getPrecioVenta());
        statement.setInt(8, producto.getStock());
        statement.executeUpdate();
    }
}


    public void actualizarProducto(Producto producto) throws SQLException {
    String sql = "UPDATE producto SET codigo=?, nombre=?, descripcion=?, fk_categoria=?, proveedor=?, precioCompra=?, precioVenta=?, stock=? WHERE idProducto=?";
    try (PreparedStatement statement = conexion.prepareStatement(sql)) {
        statement.setString(1, producto.getCodigo());
        statement.setString(2, producto.getNombre());
        statement.setString(3, producto.getDescripcion());
        statement.setInt(4, producto.getFkCategoria());
        statement.setString(5, producto.getProveedor()); 
        statement.setDouble(6, producto.getPrecioCompra());
        statement.setDouble(7, producto.getPrecioVenta());
        statement.setInt(8, producto.getStock());
        statement.setInt(9, producto.getIdProducto());
        statement.executeUpdate();
    }
}


    public void eliminarProducto(int idProducto) throws SQLException {
        String sql = "DELETE FROM producto WHERE idProducto=?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setInt(1, idProducto);
            statement.executeUpdate();
        }
    }

    public List<Producto> obtenerTodosProductos() throws SQLException {
        List<Producto> productos = new ArrayList<>(); // Cambio de nombre a 'productos'
        String sql = "SELECT * FROM producto;";
        try (PreparedStatement statement = conexion.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Producto producto = new Producto( // Cambio de nombre a 'producto'
                        resultSet.getInt("idProducto"),
                        resultSet.getString("codigo"),
                        resultSet.getString("nombre"),
                        resultSet.getString("descripcion"),
                        resultSet.getInt("fk_categoria"),
                        resultSet.getString("proveedor"),
                        resultSet.getDouble("preciocompra"),
                        resultSet.getDouble("precioventa"),
                        resultSet.getInt("stock")
                );
                productos.add(producto); // Se agrega el 'producto' creado a la lista 'productos'
            }
        }
        return productos; // Se devuelve la lista de productos
    }

    
    public List<Producto> buscarProductos(String termino) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String sql = "SELECT * FROM producto WHERE codigo LIKE ? OR nombre LIKE ? OR fk_categoria LIKE ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, "%" + termino + "%");
            statement.setString(2, "%" + termino + "%");
            statement.setString(3, "%" + termino + "%");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto(
                    rs.getInt("idProducto"),
                    rs.getString("codigo"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getInt("fk_categoria"),
                    rs.getString("proveedor"),
                    rs.getDouble("precioCompra"),
                    rs.getDouble("precioVenta"),
                    rs.getInt("stock")
                );
                productos.add(producto);
            }
        }
        return productos;
    }

   public Producto obtenerProductoPorCodigo(String codigo) {
    if (conexion == null) {
        System.out.println("La conexión es nula. No se puede ejecutar la consulta.");
        return null;
    }

    String sql = "SELECT * FROM producto WHERE codigo = ?";
    try (PreparedStatement statement = conexion.prepareStatement(sql)) {
        statement.setString(1, codigo); // Aquí se utiliza setString en lugar de setInt
        try (ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return new Producto( 
                    resultSet.getInt("idProducto"),
                    resultSet.getString("codigo"),
                    resultSet.getString("nombre"),
                    resultSet.getString("descripcion"),
                    resultSet.getInt("fk_categoria"),
                    resultSet.getString("proveedor"),
                    resultSet.getDouble("precioCompra"),
                    resultSet.getDouble("precioVenta"),
                    resultSet.getInt("stock")
                );
            }
        }
    } catch (SQLException ex) {
        // Manejar la excepción aquí
        ex.printStackTrace();
    }
    return null;
}


     public Producto obtenerProductoPorNombre(String nombre) {
        Producto producto = null;
        String sql = "SELECT * FROM productos WHERE nombre = ?";

        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, nombre);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int idProducto = resultSet.getInt("idProducto");
                    String codigo = resultSet.getString("codigo");
                    String descripcion = resultSet.getString("descripcion");
                    int fkCategoria = resultSet.getInt("fkCategoria");
                    String proveedor = resultSet.getString("proveedor");
                    double precioCompra = resultSet.getDouble("precioCompra");
                    double precioVenta = resultSet.getDouble("precioVenta");
                    int stock = resultSet.getInt("stock");

                    producto = new Producto(idProducto, codigo, nombre, descripcion, fkCategoria, proveedor, precioCompra, precioVenta, stock);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return producto;
    }
      public Map<String, Integer> cargarCategorias() {
        Map<String, Integer> categoriasMap = new HashMap<>();
        try {
            String sql = "SELECT idCategoria, nombre FROM categoria";
            try (PreparedStatement statement = conexion.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int idCategoria = resultSet.getInt("idCategoria");
                    String nombreCategoria = resultSet.getString("nombre");
                    categoriasMap.put(nombreCategoria, idCategoria);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Manejar la excepción según sea necesario
        }
        return categoriasMap;
    }

   public void actualizarStock(String codigoProducto, int cantidadVendida) throws SQLException {
    String sql = "UPDATE producto SET stock = stock - ? WHERE codigo = ?";
    try (PreparedStatement statement = conexion.prepareStatement(sql)) {
        System.out.println("Actualizando stock para el producto: " + codigoProducto);
        System.out.println("Cantidad vendida: " + cantidadVendida);
        statement.setInt(1, cantidadVendida);
        statement.setString(2, codigoProducto);
        int filasAfectadas = statement.executeUpdate();
        System.out.println("Filas afectadas: " + filasAfectadas);
    }
}




    public int obtenerStock(String codigoProducto) throws SQLException {
        String sql = "SELECT stock FROM producto WHERE codigo = ?";
        try (PreparedStatement statement = conexion.prepareStatement(sql)) {
            statement.setString(1, codigoProducto);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("stock");
            } else {
                throw new SQLException("No se encontró el producto con código: " + codigoProducto);
            }
        }
    }
    public void actualizarStockProducto(String codigoProducto, int nuevoStock) throws SQLException {
    String sql = "UPDATE producto SET stock = ? WHERE codigo = ?";
    try (PreparedStatement statement = conexion.prepareStatement(sql)) {
        statement.setInt(1, nuevoStock);
        statement.setString(2, codigoProducto);
        int filasAfectadas = statement.executeUpdate();
        System.out.println("Filas afectadas: " + filasAfectadas);
    }
}


}


