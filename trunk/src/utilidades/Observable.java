/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades;

/**
 *
 * @author German
 */
import java.util.ArrayList;

public abstract class Observable {

    //El constructor crea el vector con la asociacion Observable-Observador
    public Observable() {
        _observadores = new ArrayList<IObservador>();
    }

    //Agregar y eliminar sencillamente operan sobre vector _observadores...
    public void agregarObservador(IObservador o) {
        _observadores.add(o);
    }
    
    public void eliminarObservador(IObservador o) {
        _observadores.remove(o);
    }

    //Notificacion: Para cada observador se invoca el método actualizar().
    public void notificarObservadores() {
        for (int i = 0; i < _observadores.size(); i++) {
            ((IObservador) _observadores.get(i)).actualizar();
        }        
    }
    //Este atributo privado mantiene el vector con los observadores
    private ArrayList<IObservador> _observadores;
}
