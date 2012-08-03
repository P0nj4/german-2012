/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaDeNegocio;

import dominio.Asignacion;
import dominio.Muelle;
import dominio.Usuario;
import dominio.Vehiculo;
import excepciones.ExcepcionControlada;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import utilidades.enums.Criterio;
import utilidades.enums.EstadoDeAsignacion;
import utilidades.strategy.OrdenPorCriterio;

/**
 *
 * @author German
 */
public class ControladoraAsignacion {

    private static ControladoraAsignacion instancia;

    private ControladoraAsignacion() {
    }

    public static ControladoraAsignacion getInstance() {
        if (instancia == null) {
            instancia = new ControladoraAsignacion();
        }
        return instancia;
    }

    public void nuevaAsignacion(Muelle m, Usuario u, Vehiculo v, String pesoMercancia, int prioridad) throws Exception {
        try {
            long pesoMercanciaReal = 0;
            try {
                pesoMercanciaReal = Long.parseLong(pesoMercancia);
            } catch (Exception ex) {
                throw new excepciones.ExcepcionControlada("El peso indicado no es correcto");
            }
            if (m == null || m.getid() < 1) {
                throw new ExcepcionControlada("El muelle seleccionado no es correcto");
            }
            if (u == null || u.getid() < 1) {
                throw new ExcepcionControlada("El usuario seleccionado no es correcto");
            }
            if (v == null || v.getid() < 1) {
                throw new ExcepcionControlada("El vehículo seleccionado no es correcto");
            }
            if (pesoMercanciaReal < 1) {
                throw new ExcepcionControlada("El peso ingresado de la mercancía no es correcto");
            }
            if (prioridad > 5 || prioridad < 1) {
                throw new ExcepcionControlada("La prioridad ingresada no es correcta");
            }
            if(m.getCerrado() == true){
                throw new ExcepcionControlada("No se le puede asignar nuevas descargas a este muelle porque está cerrado");
            }

            ArrayList todosLosMuelles = ControladoraMuelle.getInstance().listarMuelles();
            for (int i = 0; i < todosLosMuelles.size(); i++) {
                Muelle mLoop = (Muelle) todosLosMuelles.get(i);
                for (int j = 0; j < mLoop.getAsignaciones().size(); j++) {
                    Asignacion aLoop = (Asignacion) mLoop.getAsignaciones().get(j);
                    if (aLoop.getVehiculo().getid() == v.getid()) {
                        if (aLoop.getEstado()== EstadoDeAsignacion.Pendiente.getCode() || aLoop.getEstado() == EstadoDeAsignacion.Descargando.getCode()) {
                            throw new ExcepcionControlada("El vehículo seleccionado se encuentra " + EstadoDeAsignacion.values()[aLoop.getEstado() - 1]  + " En otro muelle ");
                        }
                    }
                }
            }

            Asignacion a = new Asignacion();
            if (m.getAsignaciones().size() == 0) {
                //El muelle no tiene vehiculos en cola por lo que directamente se inicia la descarga
                a.setEstado(EstadoDeAsignacion.Descargando.getCode());
                a.setFechaInicioDescarga(new Date());

            } else {                
                a.setEstado( EstadoDeAsignacion.Pendiente.getCode());
            }
            a.setParentId(m.getid());
            a.setPesoMercancia(pesoMercanciaReal);
            a.setPrioridad(prioridad);
            a.setUsuario(u);
            a.setVehiculo(v);

            a.guardar();
            m.agregarNuevaAsignacion(a);
            OrdenPorCriterio orden = OrdenPorCriterio.getStrategy(m.getCriterio());
	    Collections.sort(m.getAsignaciones(), orden);
            

        } catch (ExcepcionControlada ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExcepcionControlada(ex);
        }
    }

    public void modificarAsignacion(Muelle m, Asignacion asignacion) throws Exception {
        try {
            if (m == null || m.getid() < 1) {
                throw new ExcepcionControlada("El muelle seleccionado no es correcto");
            }
            
            m.eliminarAsignacion(asignacion);
            asignacion.setEstado(EstadoDeAsignacion.Finalizado.getCode());
            asignacion.guardar();
            //sigue con el siguiente en la cola del muelle
            if (m.getAsignaciones().size() > 0) {
                OrdenPorCriterio orden = OrdenPorCriterio.getStrategy(m.getCriterio());
		Collections.sort(m.getAsignaciones(), orden);
                for(int i =0; i < m.getAsignaciones().size(); i++){
                    Asignacion nuevaAsignacion = ((Asignacion) m.getAsignaciones().get(i));
                    if(nuevaAsignacion.getEstado() == EstadoDeAsignacion.Pendiente.getCode()){                        
                        nuevaAsignacion.setEstado(EstadoDeAsignacion.Descargando.getCode());
                        nuevaAsignacion.guardar();
                        i = m.getAsignaciones().size()+1;
                    }
                }
            }
            asignacion = null;
        } catch (ExcepcionControlada ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExcepcionControlada(ex);
        }
    }
    
    
}