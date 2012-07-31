/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package excepciones;

/**
 *
 * @author Germán Pereyra
 */
public class ExcepcionControlada extends Exception{
    private String message;
    
    
    public ExcepcionControlada(Exception ex){        
        super("");
        this.message = "Lamentamos informarle que ha ocurrido un error en el sistema, \n por favor pongase en contacto con el administrador. \n Error: " + ex.getMessage() + " " + ex.getStackTrace().toString();        
        
    }
    public ExcepcionControlada(String message){        
        super("");
        this.message =message;        
        
    }

    /**
     * @return el mensaje de excepción
     */
    public String getMessage() {
        return message;
    }
    
}
