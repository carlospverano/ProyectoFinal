package model;

public class Administrador extends Usuario{
    private String email;
    public Administrador(String nombre, String id, String email, String password) {
        super(nombre, id, email, password);
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }
}
