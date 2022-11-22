package model;

public class Empleado extends Usuario {
    private  boolean estado = true;

    private String nombre;
    private Genero genero;
    private String id;
    private String email;


    public Empleado(String nombre, String email, String id, String password, Genero genero) {
        super(nombre,id,email,password);
        this.genero = genero;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Genero getGenero() {
        return genero;
    }

}
