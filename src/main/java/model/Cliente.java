package model;

public class Cliente extends Persona{

    public Cliente(String nombre, String id) {
        super(nombre, id);
    }

    @Override
    public String toString() {
        return this.getNombre();
    }

}
