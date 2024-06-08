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
public class DetalleCompra {
    private int fkIdCompra;
    private int fkIdProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public DetalleCompra(int fkIdCompra, int fkIdProducto, int cantidad, double precioUnitario, double subtotal) {
        this.fkIdCompra = fkIdCompra;
        this.fkIdProducto = fkIdProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public int getFkIdCompra() {
        return fkIdCompra;
    }

    public int getFkIdProducto() {
        return fkIdProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setFkIdCompra(int fkIdCompra) {
        this.fkIdCompra = fkIdCompra;
    }

    public void setFkIdProducto(int fkIdProducto) {
        this.fkIdProducto = fkIdProducto;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public String toString() {
        return "DetalleCompra{" + "fkIdCompra=" + fkIdCompra + ", fkIdProducto=" + fkIdProducto + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", subtotal=" + subtotal + '}';
    }
    
}
