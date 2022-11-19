package model;

public class Propiedad {

    private String direccion;
    private double valor;
    private double area;
    private Propietario propietario;
    private Disponibilidad disponibilidad;

    public Propiedad(String direccion, double valor, double area, Propietario propietario, Disponibilidad disponibilidad) {
        this.direccion = direccion;
        this.valor = valor;
        this.area = area;
        this.propietario = propietario;
        this.disponibilidad = disponibilidad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public void setArea(double area) {
        this.area = area;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public Disponibilidad getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Disponibilidad disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    @Override
    public String toString() {
        return "Propiedad{" +
                "direccion='" + direccion + '\'' +
                ", valor=" + valor +
                ", area=" + area +
                ", propietario=" + propietario +
                ", disponibilidad=" + disponibilidad +
                '}';
    }
}
