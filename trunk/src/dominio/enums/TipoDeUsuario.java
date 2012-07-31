/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio.enums;

/**
 *
 * @author German
 */
public enum TipoDeUsuario {
    Administrador(1), Usuario(2);
    
    private int code;
 
 private TipoDeUsuario(int c) {
   code = c;
 }
 
 public int getCode() {
   return code;
 }
}
