/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


public class Producto {
    private int idProducto;
    private String codigo;
    private String nombre;
    private String descripcion;
    private int fkCategoria;
    private String proveedor;
    private double precioCompra;
    private double precioVenta;
    private int stock;
     
    //private int cantidad;

    public Producto(int idProducto,String codigo, String nombre, String descripcion, int fkCategoria, String proveedor, double precioCompra, double precioVenta, int stock) {
        this.idProducto = idProducto;
        this.codigo= codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fkCategoria = fkCategoria;
        this.proveedor = proveedor;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.stock = stock;
    }

    //public Producto(int cantidad) {
       // this.cantidad = cantidad;
   // }
     public Producto(String codigo, String nombre, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioCompra = precio;
        // La cantidad se inicializa con 1 por defecto
        this.stock = stock;
    }
     public Producto(String codigo, String nombre, double precio, int cantidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precioCompra = precio;
        //this.cantidad = cantidad;
    }



    public int getIdProducto() {
        return idProducto;
    }
    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getFkCategoria() {
        return fkCategoria;
    }

    public String getProveedor() {
        return proveedor;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public int getStock() {
        return stock;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    public void setCodigo(String codigo) {
        this.codigo= codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFkCategoria(int fkCategoria) {
        this.fkCategoria = fkCategoria;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto +", codigo=" + codigo +", nombre=" + nombre + ", descripcion=" + descripcion + ", fkCategoria=" + fkCategoria + ", proveedor=" + proveedor + ", precioCompra=" + precioCompra + ", precioVenta=" + precioVenta + ", stock=" + stock + '}';
    }

   // public int getCantidad() {
      //  return cantidad;
    //}

    //public void setCantidad(int cantidad) {
      //  this.cantidad = cantidad;
    //}
    
}
