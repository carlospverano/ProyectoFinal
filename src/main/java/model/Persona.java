package model;

public class Persona {

    private String nombre;
    private String id;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Persona(String nombre, String id){
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public String getId(){
        return this.id;
    }
}
