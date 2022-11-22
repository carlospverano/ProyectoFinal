package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FincaRaiz {

    private List<Propiedad> propiedades;
    private List <Cliente> clientes;
    private List <Empleado> empleados;
    private List <Administrador> administradores;

    public List<Propietario> getPropietarios() {
        return propietarios;
    }

    public void setPropietarios(List<Propietario> propietarios) {
        this.propietarios = propietarios;
    }

    public List<Transaccion> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<Transaccion> transacciones) {
        this.transacciones = transacciones;
    }

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
        Empleado empleadox= new Empleado("Alejandra","aleja@mail.com","1245","8etbs",Genero.FEMENINO);
        empleados.add(empleadox);
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
    public void registrarPropiedad(Propiedad propiedad) throws Exception {

       if (propiedad !=null){
           propiedades.add(propiedad);
       }

    }
    public void registrarPropietario(Propietario propietario, Empleado empleado) throws Exception{


        if (empleado.getEstado() == Estado.ACTIVO) {
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

        if (empleado.getEstado() == Estado.ACTIVO) {
            String dirrecion1 = empleado.getId();
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

            if (propiedad3.getDireccion().equals(propiedad.getDireccion())) {

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

            if (propiedad3.getDireccion().equals(propiedad.getDireccion())) {

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

    public Empleado registrarEmpleado(Empleado empleadoNuevo) throws Exception {

        if (empleadoNuevo != null){

            Empleado empleadoEncontrado = empleados.stream().filter((emp) -> emp.getId().equals(empleadoNuevo.getId())).findFirst().orElse(null);

            if(empleadoEncontrado instanceof Empleado){
                return null;
            }else{
                empleados.add(empleadoNuevo);
                return empleadoNuevo;
            }


        }

        return null;
    }
    public ArrayList<ReporteVentasPorEmpleado> visualizarReporte(LocalDateTime fechaInicio, LocalDateTime fechaFinal, String idEmpleado){
        ArrayList<ReporteVentasPorEmpleado> listaReporte = new ArrayList<ReporteVentasPorEmpleado>();

        ArrayList<Transaccion> listaTransaccionRango =  (ArrayList<Transaccion>) transacciones.stream().filter(transaccion -> (

                transaccion.getEmpleado().getId().equals(idEmpleado) &&
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
    public void bloquearCuenta(Empleado empleado){
        if (empleado != null){
            empleado.setEstado(Estado.BLOQUEADO);
        }
    }


    public void actualizarDatosEmpleado(Usuario usuario, Empleado empleado, String nombre, String userId, String password, Estado estado) throws Exception {

        if (usuario instanceof Administrador) {
            Empleado empleadoAux = empleado;
            empleado = empleados.stream().filter(empleados -> empleados.getId() == empleadoAux.getId()).findFirst().orElse(null);
            if (empleado != null) {
                empleado.setNombre(nombre);
                empleado.setId(userId);
                empleado.setPassword(password);
                empleado.setEstado(estado);
            }
        } else {
            throw new Exception("Solo los administradores pueden actualizar empleados");
        }
    }

    public Usuario autenticar (String email, String password){
       Administrador administrador= administradores.stream()
                .filter( (user)-> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        Empleado empleado= empleados.stream()
                .filter( (user)-> user.getEmail().equals(email) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        if (administrador!=null){
            return administrador;

        } else if (empleado!=null) {

            return empleado;

        } else {
            return null;
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