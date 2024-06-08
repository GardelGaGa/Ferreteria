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
public class Usuario {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String cargo;
    private String nombreUsuario;
    private String contraseña;
    private String correoElectronico;
    private String telefono;
    private Date fechaContratacion;
    private double salario;

    public Usuario(int idUsuario, String nombre, String apellido, String cargo, String nombreUsuario, String contraseña, String correoElectronico, String telefono, Date fechaContratacion, double salario) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.nombreUsuario = nombreUsuario;
        this.contraseña = contraseña;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.fechaContratacion = fechaContratacion;
        this.salario = salario;
    }
  

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCargo() {
        return cargo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getTelefono() {
        return telefono;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public double getSalario() {
        return salario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Usuario{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellido=" + apellido + ", cargo=" + cargo + ", nombreUsuario=" + nombreUsuario + ", contrase\u00f1a=" + contraseña + ", correoElectronico=" + correoElectronico + ", telefono=" + telefono + ", fechaContratacion=" + fechaContratacion + ", salario=" + salario + '}';
    }

}
