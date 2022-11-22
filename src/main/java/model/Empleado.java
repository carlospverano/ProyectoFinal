package model;

public class Empleado extends Usuario {

    private String nombre;
    private Genero genero;
    private String id;
    private String email;
    private Estado estado;


    public Empleado(String nombre, String email, String id, String password, Genero genero) {
        super(nombre,id,email,password);
        this.genero = genero;
        estado = Estado.ACTIVO;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }
}
