/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;


public class DetalleVenta {
    private int fkIdVenta;
    private int fkIdProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    private String codigoProducto;

    public DetalleVenta(int fkIdVenta, int fkIdProducto, int cantidad, double precioUnitario, double subtotal) {
        this.fkIdVenta = fkIdVenta;
        this.fkIdProducto = fkIdProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }
    public DetalleVenta(String codigoProducto, int cantidad, double precioUnitario) {
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }
    
      public DetalleVenta(String codigoProducto, int cantidad, double precioUnitario, double subtotal) {
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }
      

    public int getFkIdVenta() {
        return fkIdVenta;
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

    public void setFkIdVenta(int fkIdVenta) {
        this.fkIdVenta = fkIdVenta;
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
        return "DetalleVenta{" + "fkIdVenta=" + fkIdVenta + ", fkIdProducto=" + fkIdProducto + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", subtotal=" + subtotal + '}';
    }
    
}
