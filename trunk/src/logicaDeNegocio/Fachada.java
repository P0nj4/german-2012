package logicaDeNegocio;

import java.util.*;

import dominio.*;


import excepciones.ExcepcionControlada;
import utilidades.IObservador;
import persistencia.*;

public class Fachada {

    private static Fachada instancia;

    private Fachada() {
        ManejadorBD.getInstancia().setUrl("jdbc:odbc:disenio2012");
    }

    public static Fachada getInstance() {
        if (instancia == null) {
            instancia = new Fachada();
        }
        return instancia;

    }

    public Usuario loguearUsuario(String usuario, String password) throws Exception {
        return ControladoraUsuario.getInstance().loguearUsuario(usuario, password);
    }

    /***************************/
    public void NuevoVehiculo(String marca, String modelo, String matricula, Usuario u) throws Exception {
        ControladoraVehiculo.getInstance().NuevoVehiculo(marca, modelo, matricula, u);
    }

    public void ModificarVehiculo(int id, String marca, String modelo, String matricula, Usuario u) throws Exception {
        ControladoraVehiculo.getInstance().ModificarVehiculo(id, marca, modelo, matricula, u);
    }

    public ArrayList listarVehiculos(Usuario u) throws Exception {
        return ControladoraVehiculo.getInstance().listarVehiculos(u);
    }

    public void eliminarVehiculo(int id) throws Exception {
        ControladoraVehiculo.getInstance().eliminarVehiculo(id);
    }

    
    /****************************/
    public void nuevoMuelle(int c, String descripcion, String nombre) throws Exception {
        ControladoraMuelle.getInstance().nuevoMuelle(c, descripcion, nombre);
    }

    public ArrayList listarMuelles() throws Exception {
        return ControladoraMuelle.getInstance().listarMuelles();
    }

    public void eliminarMuelle(Muelle m) throws Exception {
        ControladoraMuelle.getInstance().eliminarMuelle(m);
    }

    public void modificarMuelle(int id, int criterio, String descripcion, String nombre) throws Exception {
        ControladoraMuelle.getInstance().modificarMuelle(id, criterio, descripcion, nombre);
    }
    public void agregarObservadorDeMuelles(IObservador o) throws Exception{
        ControladoraMuelle.getInstance().agregarObservadorAMuelles(o);
    }
    
     public void eliminarObservadorDeMuelles(IObservador o) throws Exception{
        ControladoraMuelle.getInstance().eliminarObservadorAMuelles(o);
    }
    
    /*******************************/
    public void nuevaAsignacion(Muelle m, Usuario u, Vehiculo v, String pesoMercancia, int prioridad) throws Exception {
        ControladoraAsignacion.getInstance().nuevaAsignacion(m, u, v, pesoMercancia, prioridad);
    }
    
    public void modificarAsignacion(Muelle m, Asignacion asignacion) throws Exception {
        ControladoraAsignacion.getInstance().modificarAsignacion(m, asignacion);
    }
}
