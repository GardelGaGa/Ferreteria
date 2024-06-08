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
public class Compra {
    private int idCompra;
    private Date fechaCompra;
    private int fkIdProveedor;
    private double totalCompra;
    private String metodoPago;

    public Compra(int idCompra, Date fechaCompra, int fkIdProveedor, double totalCompra, String metodoPago) {
        this.idCompra = idCompra;
        this.fechaCompra = fechaCompra;
        this.fkIdProveedor = fkIdProveedor;
        this.totalCompra = totalCompra;
        this.metodoPago = metodoPago;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public int getFkIdProveedor() {
        return fkIdProveedor;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public void setFkIdProveedor(int fkIdProveedor) {
        this.fkIdProveedor = fkIdProveedor;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    @Override
    public String toString() {
        return "Compra{" + "idCompra=" + idCompra + ", fechaCompra=" + fechaCompra + ", fkIdProveedor=" + fkIdProveedor + ", totalCompra=" + totalCompra + ", metodoPago=" + metodoPago + '}';
    }
}
