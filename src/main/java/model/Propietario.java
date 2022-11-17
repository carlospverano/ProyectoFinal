package model;

public class Propietario extends Persona {
    public Propietario(String nombre, String id) {
        super(nombre, id);
    }

    @Override
    public String toString() {
        return this.getNombre();
    }
}

