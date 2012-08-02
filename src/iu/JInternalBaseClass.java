/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package iu;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;


/**
 *
 * @author German
 */
public class JInternalBaseClass extends javax.swing.JInternalFrame {
    JDesktopPane parent;
    
    public void setParent(JDesktopPane mdi){
        this.parent = mdi; 
    }
    public void msgBoxError(String message){
        JOptionPane.showMessageDialog(null, message, "Advertencia", JOptionPane.ERROR_MESSAGE);
    }
    
    public void closeWindow(){
        this.setVisible(false);        
        this.parent.remove(this);
        this.dispose();
    }
}


