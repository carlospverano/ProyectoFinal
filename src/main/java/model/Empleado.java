package model;

public class Empleado extends Usuario {
    private  boolean estado = true;
    private String nombre;
    private String email;
    private String userId;
    private String password;

    public Empleado(String nombre, String email, String id, String password, boolean estado) {
        super(nombre, email, password, id);
        this.estado = estado;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getNombre(){
        return this.nombre;
    }
    public String getUserId() {
        return this.userId;
    }

    public String getEmail(){ return this.email;}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
