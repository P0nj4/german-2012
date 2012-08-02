/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utilidades.enums;

/**
 *
 * @author German
 */
public enum Criterio {
    FIFO(1), Peso(2), Prioridad(3);
    
    private int code;
 
 private Criterio(int c) {
   code = c;
 }
 
 public int getCode() {
   return code;
 }
}
