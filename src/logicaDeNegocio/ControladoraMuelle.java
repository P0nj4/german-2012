/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaDeNegocio;

import dominio.Muelle;
import excepciones.ExcepcionControlada;
import java.util.ArrayList;

/**
 *
 * @author German
 */
public class ControladoraMuelle {

    private ArrayList todosLosMuelles;
    private static ControladoraMuelle instancia;

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

    public void eliminarMuelle(int id) throws Exception {
        try {
            if (id < 1) {
                throw new ExcepcionControlada("El muelle seleccionado no es correcto");
            }
            Muelle m = new Muelle();
            m.setid(id);
            m.eliminar();

            //elimino el muelle de la lista de muelles disponibles
            for (int i = 0; i < todosLosMuelles.size(); i++) {
                Muelle muelleLoop = (Muelle) todosLosMuelles.get(i);
                if (muelleLoop.getid() == id) {
                    todosLosMuelles.remove(i);
                    i = todosLosMuelles.size() + 1;
                }
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
            listarMuelles();
            for (int i = 0; i < todosLosMuelles.size(); i++) {
                ((Muelle) todosLosMuelles.get(i)).agregarObservador(o);
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
}
