package model;

public class Propiedad {

    private String direcion;
    private double valor;
    private double area;
    private Propietario propietario;
    protected Disponibilidad disponibilidad;

    public Propiedad(String direcion, double valor, double area, Propietario propietario, Disponibilidad disponibilidad) {
        this.direcion = direcion;
        this.valor = valor;
        this.area = area;
        this.propietario = propietario;
        this.disponibilidad = disponibilidad;
    }

    public String getDirecion() {
        return direcion;
    }

    public void setDirrecion(String direcion) {
        this.direcion = direcion;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getArea() {
        return area;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public Disponibilidad getDisponibilidad() {
        return this.disponibilidad;
    }

    public void setDisponibilidad(Disponibilidad disponibilidad) {
        this.disponibilidad = disponibilidad;

    }

}
