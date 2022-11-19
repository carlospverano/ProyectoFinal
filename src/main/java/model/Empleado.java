package model;

public class Empleado extends Usuario {
    private  boolean estado = true;

    public Empleado(String nombre, String email, String id, String password, boolean estado) {
        super(nombre,id,email,password);
        this.estado = estado;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
