package model;

import java.time.LocalDateTime;

public class Transaccion {
    private Empleado empleado;
    private Cliente cliente;
    private Propiedad propiedad;
    private LocalDateTime fechaRegistro;

    public Transaccion(Empleado empleado, Cliente cliente, Propiedad propiedad) {

        this.empleado = empleado;
        this.cliente = cliente;
        this.propiedad = propiedad;
        this.fechaRegistro =LocalDateTime.now();
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Propiedad getPropiedad() {
        return propiedad;
    }

    public void setPropiedad(Propiedad propiedad) {
        this.propiedad = propiedad;
    }


    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
