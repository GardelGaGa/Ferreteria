/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author alary
 */
public class ProductoVendido {
     private int idVenta;
    private Date fechaVenta;
    private int fkIdUsuario;
    private double totalVenta;
    private String metodoPago;
    private int fkIdProducto;
    private String codigoProducto;
    private String nombreProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public ProductoVendido(int idVenta, Date fechaVenta, int fkIdUsuario, double totalVenta, String metodoPago, int fkIdProducto, String codigoProducto, String nombreProducto, int cantidad, double precioUnitario, double subtotal) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.fkIdUsuario = fkIdUsuario;
        this.totalVenta = totalVenta;
        this.metodoPago = metodoPago;
        this.fkIdProducto = fkIdProducto;
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public Date getFechaVenta() {
        return fechaVenta;
    }

    public int getFkIdUsuario() {
        return fkIdUsuario;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public int getFkIdProducto() {
        return fkIdProducto;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
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

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public void setFkIdUsuario(int fkIdUsuario) {
        this.fkIdUsuario = fkIdUsuario;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public void setFkIdProducto(int fkIdProducto) {
        this.fkIdProducto = fkIdProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
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
        return "ProductoVendido{" + "idVenta=" + idVenta + ", fechaVenta=" + fechaVenta + ", fkIdUsuario=" + fkIdUsuario + ", totalVenta=" + totalVenta + ", metodoPago=" + metodoPago + ", fkIdProducto=" + fkIdProducto + ", codigoProducto=" + codigoProducto + ", nombreProducto=" + nombreProducto + ", cantidad=" + cantidad + ", precioUnitario=" + precioUnitario + ", subtotal=" + subtotal + '}';
    }

    
}
