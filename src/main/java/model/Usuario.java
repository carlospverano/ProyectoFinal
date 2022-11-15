package model;

public class Usuario extends Persona{
    private String email;
    private String password;

    public Usuario(String nombre, String id, String email, String password) {
        super(nombre, id);
        this.email = email;
        this.password = password;
    }

    public String getEmail(){return this.email;}

    public String getPassword(){return this.password;}


}
