/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;



public class Venta {
    private int idVenta;
    private Date fechaVenta;
    private int fkIdUsuario;
    private double totalVenta;
    private String metodoPago;
    private double pago;
    private double cambio;
    private double subtotal;
    private double iva;

    public Venta(int idVenta, Date fechaVenta, int fkIdUsuario, double totalVenta, String metodoPago) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.fkIdUsuario = fkIdUsuario;
        this.totalVenta = totalVenta;
        this.metodoPago = metodoPago;
    }
    public Venta(Date fechaVenta, int fkIdUsuario, double totalVenta, String metodoPago) {
        this.fechaVenta = fechaVenta;
         this.fkIdUsuario = fkIdUsuario;
        this.totalVenta = totalVenta;
        this.metodoPago = metodoPago;
        
    }

    public Venta(double subtotal, double iva, double totalVenta, String metodoPago, double pago, double cambio, Date fechaVenta, int fkIdUsuario) {
        this.subtotal = subtotal;
        this.iva = iva;
        this.totalVenta = totalVenta;
        this.metodoPago = metodoPago;
        this.pago = pago;
        this.cambio = cambio;
        this.fechaVenta = fechaVenta;
        this.fkIdUsuario = fkIdUsuario;
    }
    public double getPago() {
        return pago;
    }

    public double getCambio() {
        return cambio;
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

    public void setPago(double pago) {
        this.pago = pago;
    }

    public void setCambio(double cambio) {
        this.cambio = cambio;
    }

    @Override
    public String toString() {
        return "Venta{" + "idVenta=" + idVenta + ", fechaVenta=" + fechaVenta + ", fkIdUsuario=" + fkIdUsuario + ", totalVenta=" + totalVenta + ", metodoPago=" + metodoPago + ", pago=" + pago + ", cambio=" + cambio + ", subtotal=" + subtotal + ", iva=" + iva + '}';
    }

  

  
}
