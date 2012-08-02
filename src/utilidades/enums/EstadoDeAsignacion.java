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
 
    Pendiente(21), Descargando(22), Finalizado(23);
 
 private int code;
 
 private EstadoDeAsignacion(int c) {
   code = c;
 }
 
 public int getCode() {
   return code;
 }
     
     
}
