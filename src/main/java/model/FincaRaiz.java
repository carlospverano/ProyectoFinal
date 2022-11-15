package model;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FincaRaiz {

    private List<Propiedad> propiedades;
    private List <Cliente> clientes;
    private List <Empleado> empleados;
    private List <Administrador> administradores;
    private  List <Propietario> propietarios;
    private List<Transaccion> transacciones;

    Scanner teclado = new Scanner(System.in);


    public FincaRaiz(){
        propiedades = new ArrayList<>();
        empleados = new ArrayList<>();
        clientes = new ArrayList<>();
        administradores = new ArrayList<>();
        propietarios = new ArrayList<>();
        transacciones = new ArrayList<>();

        Administrador admin = new Administrador("admin", "001", "admin@fincaraiz.com", "admin");
        administradores.add(admin);
    }

    public List<Propiedad> getPropiedades() {
        return propiedades;
    }

    public void setPropiedades(List<Propiedad> propiedades) {
        this.propiedades = propiedades;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<Administrador> getAdministradores() {
        return administradores;
    }

    public void setAdministradores(List<Administrador> administradores) {
        this.administradores = administradores;
    }
    public void registrarPropiedad(Propiedad propiedad, Usuario usuario) throws Exception {

        if (usuario instanceof Empleado) {
            String dirrecion1 = propiedad.getDirecion();
            Propiedad propiedaAux = propiedades.stream().filter(propiedades -> propiedades.getDirecion() == dirrecion1).findFirst().orElse(null);
            if (propiedaAux != null) {
                throw new Exception("La propiedad ya existe");
            } else if (propiedad != null) {

                propiedades.add(propiedad);
            } else {
                throw new Exception("Datos invalidos");
            }
        }
        else {
            throw new Exception("Solo los empleados pueden registrar propiedades");
        }
    }
    public void registrarPropietario(Propietario propietario, Empleado empleado) throws Exception{


        if (empleado.isEstado() == true) {
            String dirrecion1 = propietario.getNombre();
            Propietario propietarioAux = propietarios.stream().filter(propietario1 -> propietario1.getNombre() == dirrecion1).findFirst().orElse(null);
            if (propietarioAux != null) {
                throw new Exception("La propiedad ya existe");
            } else if (propietario!= null) {

                propietarios.add(propietario);
            } else {
                throw new Exception("Datos invalidos");
            }
        } else {
            throw new Exception("Solo los empleados pueden registrar propiedades");

        }
    }

    public void registrarCliente (Cliente cliente,Empleado empleado)throws Exception{

        if (empleado.isEstado() == true) {
            String dirrecion1 = empleado.getUserId();
            Empleado propietarioAux = empleados.stream().filter(empleado1 -> empleado1.getNombre() == dirrecion1).findFirst().orElse(null);
            if (propietarioAux != null) {
                throw new Exception("La propiedad ya existe");
            } else if (cliente!= null) {

                clientes.add(cliente);
            } else {
                throw new Exception("Datos invalidos");
            }
        } else {
            throw new Exception("Solo los empleados pueden registrar propiedades");

        }
    }
    public void alquiler (Empleado empleado, Cliente cliente, Propiedad propiedad){
        String disponibilidad = propiedad.getDisponibilidad().toString();

        List<Propiedad> listaResultado = propiedades.stream().filter(propiedad1 -> propiedad.equals(propiedad1)).collect(Collectors.toList());
        for (Propiedad propiedad3 : listaResultado) {

            if (propiedad3.getDirecion().equals(propiedad.getDirecion())) {

                if(disponibilidad.equalsIgnoreCase("disponible") ) {
                    System.out.println("Alquilada");
                    propiedad.setDisponibilidad(Disponibilidad.NO_DISPONIBLE);

                    registrarTransaccion(empleado, cliente, propiedad);

                }else {
                    System.out.println("La propiedad ya esta alquilada.");
                }
            }else {
                System.out.println("La propiedad no esta registrada");
            }
        }

    }

    public void vender (Empleado empleado, Cliente cliente, Propiedad propiedad){
        String disponibilidad = propiedad.getDisponibilidad().toString();

        List<Propiedad> listaResultado = propiedades.stream().filter(propiedad1 -> propiedad.equals(propiedad1)).collect(Collectors.toList());


        for (Propiedad propiedad3 : listaResultado) {

            if (propiedad3.getDirecion().equals(propiedad.getDirecion())) {

                if(disponibilidad.equalsIgnoreCase("disponible") ) {
                    System.out.println("Vendida");
                    propiedad.setDisponibilidad(Disponibilidad.NO_DISPONIBLE);

                    registrarTransaccion(empleado, cliente, propiedad);


                }else {
                    System.out.println("La propiedad ya esta alquilada.");
                }
            }else {
                System.out.println("La propiedad no esta registrada");
            }
        }
    }

    public void registrarTransaccion(Empleado empleado, Cliente cliente, Propiedad propiedad){

        transacciones.add(new Transaccion(empleado, cliente, propiedad));

	/*
    	Propiedad propiedadDisponible = this.obtenerPropiedad(propiedad.getDirecion());

    	if(propiedadDisponible == null) {
    		System.out.println("esta propiedad no existe");
    	}
    	else {
    		if(propiedadDisponible.isDisponible()) {
        		System.out.println("la propiedad ya esta ocupada "+propiedad.getDirecion());
        	}
        	else {
        		Transaccion transaccion = new Transaccion(empleado, cliente, propiedad);
        		transacciones.add(transaccion);

        		propiedadDisponible.setDisponible(false);
        	}
    	}*/

    }
    public List<String> buscarPropiedad(String propiedad){

        return propiedades.stream().map( (propiedad1) -> {
            return propiedad1.getClass().getSimpleName();
        }).filter( (propiedad2)-> {
            return propiedad2.equalsIgnoreCase(propiedad);
        }).collect(Collectors.toList());
    }

    public void registrarEmpleado(Empleado empleado, Administrador administrador) throws Exception {


        if (administrador instanceof Administrador) {
            String userId1 = empleado.getUserId();
            Empleado empleadoAux = empleados.stream().filter(empleado1 -> empleado1.getUserId() == userId1).findFirst().orElse(null);
            if (empleadoAux != null) {
                throw new Exception("La propiedad ya existe");
            } else if (empleado != null) {

                empleados.add(empleado);
            } else {
                throw new Exception("Datos invalidos");
            }
        } else {
            throw new Exception("Solo los empleados pueden registrar propiedades");

        }
    }
    public ArrayList<ReporteVentasPorEmpleado> visualizarReporte(LocalDateTime fechaInicio, LocalDateTime fechaFinal, String idEmpleado){
        ArrayList<ReporteVentasPorEmpleado> listaReporte = new ArrayList<ReporteVentasPorEmpleado>();

        ArrayList<Transaccion> listaTransaccionRango =  (ArrayList<Transaccion>) transacciones.stream().filter(transaccion -> (

                transaccion.getEmpleado().getUserId().equals(idEmpleado) &&
                        (transaccion.getFechaRegistro().isAfter(fechaInicio) || transaccion.getFechaRegistro().equals(fechaInicio))
                        &&
                        (transaccion.getFechaRegistro().isBefore(fechaFinal) || transaccion.getFechaRegistro().equals(fechaFinal))
        )).collect(Collectors.toList());

        //Devuelve los datos de un empleado, Busca por id y por rango de fechas y devuelve una lista de transacciones por ejemplo ventas y alquileres de casas


        if(listaTransaccionRango.size() > 0) {
            ReporteVentasPorEmpleado ventas = new ReporteVentasPorEmpleado(); //Aqui instancio el empleado con
            ventas.setNombreEmpleado(listaTransaccionRango.get(0).getEmpleado().getNombre()); //Con esto accedo al primer registro que sería el nombre del empleado, siempre sera el mismo porque se filtro por un solo id
            int cantidadVentas = 0;
            double total = 0;
            for (Transaccion transac : listaTransaccionRango) {
                total += transac.getPropiedad().getValor();
                cantidadVentas++;
            }

            ventas.setCantidadPropiedades(cantidadVentas);
            ventas.setTotal(total);
            listaReporte.add(ventas);
        }


        return listaReporte;
    }
    public void bloquearCuenta(Usuario usuario,Empleado empleado){

        if (usuario instanceof Administrador) {
            Empleado empleadoAux = empleado;
            empleado = empleados.stream().filter(empleados -> empleados.getUserId() == empleadoAux.getUserId()).findFirst().orElse(null);
            if (empleado != null) {
                empleado.setEstado(false);
            }else {
                System.out.println("Datos invalidos.");
            }
        }else {
            System.out.println("Solo los administradores pueden bloquear cuentas");
        }
    }


    public void actualizarDatosEmpleado(Usuario usuario, Empleado empleado, String nombre, String userId, String password, boolean estado) throws Exception {

        if (usuario instanceof Administrador) {
            Empleado empleadoAux = empleado;
            empleado = empleados.stream().filter(empleados -> empleados.getUserId() == empleadoAux.getUserId()).findFirst().orElse(null);
            if (empleado != null) {
                empleado.setNombre(nombre);
                empleado.setUserId(userId);
                empleado.setPassword(password);
                empleado.setEstado(estado);
            }
        } else {
            throw new Exception("Solo los administradores pueden actualizar empleados");
        }
    }


    public void menuEmpleado() {
        System.out.println("1.Registrar propiedad.");
        System.out.println("2.Registrar propietario.");
        System.out.println("3.Registrar cliente.");
        //System.out.println("4.Alquiler.");
        //System.out.println("5.Venta.");
        System.out.println("6.Retirar propiedad");
    }
    public void menuAdministrador() {
        System.out.println("1.Visualizar reportes.");
        System.out.println("2.Registrar empleado.");
        System.out.println("3.Actulizar datos empleado.");
        System.out.println("4.Bloquear cuenta.");
    }
    public void menuCliente() {
        System.out.println("1.Comprar");
        System.out.println("2.Alquilar");
    }
}