package model;

import java.time.LocalDateTime;

public class ReporteVentasPorEmpleado {
    private String nombreEmpleado;
    private LocalDateTime fecha;
    private int cantidadPropiedades;
    private double total;

    public ReporteVentasPorEmpleado() {

    }



    public ReporteVentasPorEmpleado(String nombreEmpleado, LocalDateTime fecha, int cantidadPropiedades, double total) {
        super();
        this.nombreEmpleado = nombreEmpleado;
        this.fecha = fecha;
        this.cantidadPropiedades = cantidadPropiedades;
        this.total = total;
    }

    public String getNombreEmpleado() {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado) {
        this.nombreEmpleado = nombreEmpleado;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public int getCantidadPropiedades() {
        return cantidadPropiedades;
    }

    public void setCantidadPropiedades(int cantidadPropiedades) {
        this.cantidadPropiedades = cantidadPropiedades;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
