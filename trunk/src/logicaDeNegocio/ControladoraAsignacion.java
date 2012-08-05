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

    /***
     * Crea una nueva asignación
     * @param m muelle
     * @param u usuario creador
     * @param v vehiculo a asignar
     * @param pesoMercancia peso de la mercancia cotenida en el vehículo
     * @param prioridad prioridad asignada por el usuario
     * @throws Exception 
     */
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
            if (m.getCerrado() == true) {
                throw new ExcepcionControlada("No se le puede asignar nuevas descargas a este muelle porque está cerrado");
            }

            //valido que el vehiculo no se encuentre descargando o en cola de otro muelle
            ArrayList todosLosMuelles = ControladoraMuelle.getInstance().listarMuelles();
            for (int i = 0; i < todosLosMuelles.size(); i++) {
                Muelle mLoop = (Muelle) todosLosMuelles.get(i);
                for (int j = 0; j < mLoop.getAsignaciones().size(); j++) {
                    Asignacion aLoop = (Asignacion) mLoop.getAsignaciones().get(j);
                    if (aLoop.getVehiculo().getid() == v.getid()) {
                        throw new ExcepcionControlada("El vehículo seleccionado se encuentra " + EstadoDeAsignacion.values()[aLoop.getEstado() - 1] + " En otro muelle ");
                    }
                }
            }

            Asignacion a = new Asignacion();
            if (m.getAsignaciones().size() == 0) {
                //El muelle no tiene vehiculos en cola por lo que directamente se inicia la descarga
                a.setEstado(EstadoDeAsignacion.Descargando.getCode());
                a.setFechaInicioDescarga(new Date());

            } else {
                a.setEstado(EstadoDeAsignacion.Pendiente.getCode());
            }
            
            a.setPesoMercancia(pesoMercanciaReal);
            a.setPrioridad(prioridad);
            a.setUsuario(u);
            a.setVehiculo(v);

            a.guardar(m.getid());
            m.agregarNuevaAsignacion(a);
            OrdenPorCriterio orden = OrdenPorCriterio.getStrategy(m.getCriterio());
            
            Asignacion temp = (Asignacion) m.getAsignaciones().get(0);
            m.getAsignaciones().remove(0);
            
            Collections.sort(m.getAsignaciones(), orden);
            
            m.getAsignaciones().add(0, temp);
            m.notificarObservadores();

        } catch (ExcepcionControlada ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExcepcionControlada(ex);
        }
    }

    /****
     * Metodo encargado de cambiar el estado de la asignacion actual y tomar la asignación del próximo vehiculo en cola para actualizar su estado.
     * Se pordría decir que este metodo es quien hace la descarga
     * @param m muelle
     * @param asignacion
     * @throws Exception 
     */
    public void modificarAsignacion(Muelle m, Asignacion asignacion) throws Exception {
        try {
            if (m == null || m.getid() < 1) {
                throw new ExcepcionControlada("El muelle seleccionado no es correcto");
            }

            m.eliminarAsignacion(asignacion);
            asignacion.setEstado(EstadoDeAsignacion.Finalizado.getCode());
            asignacion.guardar(-1);
            asignacion = null;

            //sigue con el siguiente en la cola del muelle
            if (m.getAsignaciones().size() > 0) {
                OrdenPorCriterio orden = OrdenPorCriterio.getStrategy(m.getCriterio());
                Collections.sort(m.getAsignaciones(), orden);
                for (int i = 0; i < m.getAsignaciones().size(); i++) {
                    Asignacion nuevaAsignacion = ((Asignacion) m.getAsignaciones().get(i));
                    if (nuevaAsignacion.getEstado() == EstadoDeAsignacion.Pendiente.getCode()) {
                        nuevaAsignacion.setEstado(EstadoDeAsignacion.Descargando.getCode());
                        nuevaAsignacion.guardar(-1);
                        i = m.getAsignaciones().size() + 1;
                    }
                }
            }
            m.notificarObservadores();

        } catch (ExcepcionControlada ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExcepcionControlada(ex);
        }
    }
}
