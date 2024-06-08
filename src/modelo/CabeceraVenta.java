package modelo;

public class CabeceraVenta {
    
    // Atributos
    private int idCabeceraventa;
    private double valorPagar;
    private String fechaVenta;
    private int estado;
    private String formaPago;
    private String observaciones;
    
    // Constructor
    public CabeceraVenta() {
        this.idCabeceraventa = 0;
        this.valorPagar = 0.0;
        this.fechaVenta = "";
        this.estado = 0;
        this.formaPago = "";
        this.observaciones = "";
    }
    
    // Constructor sobrecargado
    public CabeceraVenta(int idCabeceraventa, double valorPagar, String fechaVenta, int estado, String formaPago, String observaciones) {
        this.idCabeceraventa = idCabeceraventa;
        this.valorPagar = valorPagar;
        this.fechaVenta = fechaVenta;
        this.estado = estado;
        this.formaPago = formaPago;
        this.observaciones = observaciones;
    }
    
    // Getters y Setters
    public int getIdCabeceraventa() {
        return idCabeceraventa;
    }

    public void setIdCabeceraventa(int idCabeceraventa) {
        this.idCabeceraventa = idCabeceraventa;
    }

    public double getValorPagar() {
        return valorPagar;
    }

    public void setValorPagar(double valorPagar) {
        this.valorPagar = valorPagar;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    // toString
    @Override
    public String toString() {
        return "CabeceraVenta{" + 
               "idCabeceraventa=" + idCabeceraventa + 
               ", valorPagar=" + valorPagar + 
               ", fechaVenta=" + fechaVenta + 
               ", estado=" + estado + 
               ", formaPago='" + formaPago + '\'' + 
               ", observaciones='" + observaciones + '\'' + 
               '}';
    }
}
