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
public class Proveedor {
    private int idProveedor;
    private String nombreEmpresa;
    private String rfc;
    private String direccion;
    private String telefono;
    private String correoElectronico;

    public Proveedor(int idProveedor, String nombreEmpresa, String rfc, String direccion, String telefono, String correoElectronico) {
        this.idProveedor = idProveedor;
        this.nombreEmpresa = nombreEmpresa;
        this.rfc = rfc;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public String getRfc() {
        return rfc;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "idProveedor=" + idProveedor + ", nombreEmpresa=" + nombreEmpresa + ", rfc=" + rfc + ", direccion=" + direccion + ", telefono=" + telefono + ", correoElectronico=" + correoElectronico + '}';
    }
    
}