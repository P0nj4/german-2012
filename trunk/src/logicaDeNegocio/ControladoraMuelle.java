/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaDeNegocio;

import dominio.Asignacion;
import dominio.Muelle;
import dominio.Vehiculo;
import excepciones.ExcepcionControlada;
import java.util.ArrayList;
import utilidades.IObservador;

/**
 *
 * @author German
 */
public class ControladoraMuelle {

    private ArrayList todosLosMuelles;
    private static ControladoraMuelle instancia;
    private IObservador obs = null;

    private ControladoraMuelle() {
        todosLosMuelles = new ArrayList();
    }

    public static ControladoraMuelle getInstance() {
        if (instancia == null) {
            instancia = new ControladoraMuelle();
        }
        return instancia;
    }

    public void nuevoMuelle(int c, String descripcion, String nombre) throws Exception {
        try {
            if (descripcion == null || descripcion.trim().length() == 0) {
                throw new ExcepcionControlada("La descripción no puede ser vacía");
            }
            if (nombre == null || nombre.trim().length() == 0) {
                throw new ExcepcionControlada("El nombre no puede ser vacío");
            }
            if (c < 1) {
                throw new ExcepcionControlada("Seleccione un criterio correcto");
            }
            Muelle m = new Muelle();
            m.setCriterio(c);
            m.setDescripcion(descripcion);
            m.setNombre(nombre);
            m.guardar();
            todosLosMuelles.add(m);
        } catch (ExcepcionControlada ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExcepcionControlada(ex);
        }
    }

    public void modificarMuelle(int id, int c, String descripcion, String nombre) throws Exception {
        try {
            if (descripcion == null || descripcion.trim().length() == 0) {
                throw new ExcepcionControlada("La descripción no puede ser vacía");
            }
            if (nombre == null || nombre.trim().length() == 0) {
                throw new ExcepcionControlada("El nombre no puede ser vacío");
            }
            if (c < 1) {
                throw new ExcepcionControlada("Seleccione un criterio correcto");
            }
            if (id == 0) {
                throw new ExcepcionControlada("El muelle seleccionado no es correcto");
            }
            Muelle m = new Muelle();
            m.setCriterio(c);
            m.setDescripcion(descripcion);
            m.setNombre(nombre);
            m.setid(id);
            m.guardar();
        } catch (ExcepcionControlada ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExcepcionControlada(ex);
        }
    }

    public void eliminarMuelle(Muelle m) throws Exception {
        try {
            if (m.getid() < 1) {
                throw new ExcepcionControlada("El muelle seleccionado no es correcto");
            }


            if (m.getAsignaciones().size() == 0) {
                m.eliminar();
                //elimino el muelle de la lista de muelles disponibles               
                todosLosMuelles.remove(m);
            } else {
                throw new ExcepcionControlada("El muelle tiene descargas pendientes");
            }

        } catch (ExcepcionControlada ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExcepcionControlada(ex);
        }
    }

    public ArrayList listarMuelles() throws Exception {
        try {
            if (todosLosMuelles == null || todosLosMuelles.isEmpty()) {
                Muelle m = new Muelle();
                todosLosMuelles = m.obtenerTodos();
            }
            if (todosLosMuelles.isEmpty()) {
                throw new ExcepcionControlada("Aún no existen muelles");
            }
            return todosLosMuelles;
        } catch (ExcepcionControlada ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ExcepcionControlada(ex);
        }
    }

    public void agregarObservadorAMuelles(utilidades.IObservador o) throws Exception {
        try {
            if (o == null) {
                o = obs;
            } else {
                obs = o;
            }
            if (o != null) {
                listarMuelles();
                for (int i = 0; i < todosLosMuelles.size(); i++) {
                    ((Muelle) todosLosMuelles.get(i)).agregarObservador(o);
                    ArrayList asignaciones = ((Muelle) todosLosMuelles.get(i)).getAsignaciones();
                    for (int j = 0; j < asignaciones.size(); j++) {
                        ((Asignacion) asignaciones.get(j)).getVehiculo().agregarObservador(o);
                    }
                }
            }
        } catch (Exception ex) {
            throw new ExcepcionControlada(ex);
        }

    }

    public void eliminarObservadorAMuelles(utilidades.IObservador o) throws Exception {
        try {
            for (int i = 0; i < todosLosMuelles.size(); i++) {
                ((Muelle) todosLosMuelles.get(i)).eliminarObservador(o);
            }
        } catch (Exception ex) {
            throw new ExcepcionControlada(ex);
        }
    }

    public boolean MuelleTieneVehiculo(Vehiculo v) throws Exception {
        try {
            for (int i = 0; i < todosLosMuelles.size(); i++) {
                ArrayList asignaciones = (((Muelle) todosLosMuelles.get(i)).getAsignaciones());
                for (int j = 0; j < asignaciones.size(); j++) {
                    if (((Vehiculo) asignaciones.get(j)).getid() == v.getid()) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception ex) {
            throw new ExcepcionControlada(ex);
        }
    }

    public void actualizarVehiculoAsignado(Vehiculo v) throws Exception {
        listarMuelles();
        agregarObservadorAMuelles(null);
        for (int i = 0; i < todosLosMuelles.size(); i++) {
            ArrayList asginaciones = ((Muelle) todosLosMuelles.get(i)).getAsignaciones();
            for (int j = 0; j < asginaciones.size(); j++) {
                Asignacion vloop = (Asignacion) asginaciones.get(i);
                if (v.getid() == vloop.getVehiculo().getid()) {
                    vloop.getVehiculo().setMarca(v.getMarca());
                    vloop.getVehiculo().setModelo(v.getModelo());
                    vloop.getVehiculo().setMatricula(v.getMatricula());
                }
            }
        }
    }
}
