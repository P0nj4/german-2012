/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades.enums;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author German
 */
public enum EstadoDeAsignacion {
 
    Pendiente(1), Descargando(2), Finalizado(3);
 
 private int code;
 
 private EstadoDeAsignacion(int c) {
   code = c;
 }
 
 public int getCode() {
   return code;
 }
     
     
}
