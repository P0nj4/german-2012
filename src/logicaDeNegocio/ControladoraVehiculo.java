/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logicaDeNegocio;

import dominio.Usuario;
import dominio.Vehiculo;
import utilidades.enums.TipoDeUsuario;
import excepciones.ExcepcionControlada;
import java.util.ArrayList;

/**
 *
 * @author German
 */
public class ControladoraVehiculo {

    private static ControladoraVehiculo instancia;

    private ControladoraVehiculo() {
    }

    public static ControladoraVehiculo getInstance() {
        if (instancia == null) {
            instancia = new ControladoraVehiculo();
        }
        return instancia;
    }

    public void eliminarVehiculo(int id) throws Exception {
        try {
            if (id == 0) {
                throw new ExcepcionControlada("El vehiculo seleccionado no es correcto");
            }
            
            Vehiculo v = new Vehiculo();
            v.setid(id);
            if(ControladoraMuelle.getInstance().MuelleTieneVehiculo(v)){
                throw new ExcepcionControlada("El vehículo no puede ser eliminado dado que esta en una cola de espera");
            }else{
                v.eliminar();
            }
        } catch (ExcepcionControlada e) {
            throw e;
        } catch (Exception ex) {
            throw new ExcepcionControlada(ex);
        }
    }

    public void NuevoVehiculo(String marca, String modelo, String matricula, Usuario u) throws Exception {
        try {
            if (u.getTipoDeUsuario() != TipoDeUsuario.Administrador.getCode()) {
                throw new ExcepcionControlada("Usted no tiene permisos para realizar esta acción");
            }
            if (marca == null || marca.trim().length() == 0) {
                throw new ExcepcionControlada("La marca no puede ser vacía");
            }
            if (modelo == null || modelo.trim().length() == 0) {
                throw new ExcepcionControlada("El modelo no puede ser vacío");
            }
            if (matricula == null || matricula.trim().length() == 0) {
                throw new ExcepcionControlada("La matrícula no puede ser vacía");
            }
            Vehiculo v = new Vehiculo();
            v.setMarca(marca);
            v.setMatricula(matricula);
            v.setModelo(modelo);
            v.guardar();
        } catch (ExcepcionControlada e) {
            throw e;
        } catch (Exception ex) {
            throw new ExcepcionControlada(ex);
        }
    }

    /***
     * Modifica los valores de un vehiculo dado su matrícula
     * @param id
     * @param marca
     * @param modelo
     * @param matricula
     * @throws ExcepcionControlada 
     */
    public void ModificarVehiculo(int id, String marca, String modelo, String matricula, Usuario u) throws Exception {
        try {
            if (u.getTipoDeUsuario() != TipoDeUsuario.Administrador.getCode()) {
                throw new ExcepcionControlada("Usted no tiene permisos para realizar esta acción");
            }
            if (marca == null || marca.trim().length() == 0) {
                throw new ExcepcionControlada("La marca no puede ser vacía");
            }
            if (modelo == null || modelo.trim().length() == 0) {
                throw new ExcepcionControlada("El modelo no puede ser vacío");
            }
            if (matricula == null || matricula.trim().length() == 0) {
                throw new ExcepcionControlada("La matrícula no puede ser vacía");
            }
            Vehiculo v = new Vehiculo();
            v.setid(id);
            v.setMarca(marca);
            v.setMatricula(matricula);
            v.setModelo(modelo);
            v.guardar();

        } catch (ExcepcionControlada e) {
            throw e;
        } catch (Exception ex) {
            throw new ExcepcionControlada(ex);
        }
    }

    /***
     * Retorna una lista con todos los vehiculos existentes
     * @return ArrayList
     * @throws ExcepcionControlada 
     */
    public ArrayList listarVehiculos(Usuario u) throws Exception {
        try {
            if (u.getTipoDeUsuario() != TipoDeUsuario.Administrador.getCode()) {
                throw new ExcepcionControlada("Usted no tiene permisos para realizar esta acción");
            }
            Vehiculo v = new Vehiculo();
            return v.obtenerTodos();
        } catch (ExcepcionControlada e) {
            throw e;
        } catch (Exception ex) {
            throw new ExcepcionControlada(ex);
        }

    }
    
    
    public Vehiculo buscarVehiculo(String matricula) throws Exception{
        try{
            Vehiculo v = new Vehiculo();
            v.setMatricula(matricula);
            v.leer();
            if(v.getid() == 0){
                throw new ExcepcionControlada("No se ha encontrado ese vehículo");
            }
            return v;
        }catch(Exception ex){
        throw new ExcepcionControlada(ex);
        }
    }
}
