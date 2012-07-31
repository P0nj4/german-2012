/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Principal.java
 *
 * Created on 28-jul-2012, 18:27:09
 */
package iu;

import dominio.Usuario;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author German
 */
public class Principal extends javax.swing.JFrame {
    
    Usuario loggedUser = null;
    ArrayList ventanas = null;
    
    private void agregarVentanaHija(JInternalBaseClass o) {
        boolean estaEnLaLista = false;
        for (int i = 0; i < ventanas.size(); i++) {
            if (o.getClass() == ventanas.get(i).getClass()) {
                ((JInternalBaseClass) o).setVisible(true);                
                estaEnLaLista = true;
            }
        }
        if (!estaEnLaLista) {
            ventanas.add(o);
            this.desktop.add(o);
            o.setVisible(true);
        }
    }

    /** Creates new form Principal */
    public Principal(Usuario u) {
        initComponents();
        ventanas = new ArrayList();
        this.setExtendedState(this.MAXIMIZED_BOTH);
        //this.setUndecorated(true);
        
        loggedUser = u;
        
        ListaSiempreVisible frm = new ListaSiempreVisible();
        agregarVentanaHija(frm);
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {

            public void windowClosing(WindowEvent winEvt) {
                //chequear los muelles
                System.exit(0);
            }
        });
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktop = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        btnVehiculos = new javax.swing.JMenu();
        btnVehiculoNuevo = new javax.swing.JMenuItem();
        btnVehiculoModificar = new javax.swing.JMenuItem();
        btnVehiculoEliminar = new javax.swing.JMenuItem();
        menuMuelles = new javax.swing.JMenu();
        btnMuelleNuevo = new javax.swing.JMenuItem();
        btnMuelleModificar = new javax.swing.JMenuItem();
        btnMuelleEliminar = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnVehiculos.setText("Vehículos");
        btnVehiculos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVehiculosActionPerformed(evt);
            }
        });

        btnVehiculoNuevo.setText("Nuevo");
        btnVehiculoNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVehiculoNuevoActionPerformed(evt);
            }
        });
        btnVehiculos.add(btnVehiculoNuevo);

        btnVehiculoModificar.setText("Modificar");
        btnVehiculoModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVehiculoModificarActionPerformed(evt);
            }
        });
        btnVehiculos.add(btnVehiculoModificar);

        btnVehiculoEliminar.setText("Eliminar");
        btnVehiculoEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVehiculoEliminarActionPerformed(evt);
            }
        });
        btnVehiculos.add(btnVehiculoEliminar);

        jMenuBar1.add(btnVehiculos);

        menuMuelles.setText("Muelles");

        btnMuelleNuevo.setText("Nuevo");
        btnMuelleNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuelleNuevoActionPerformed(evt);
            }
        });
        menuMuelles.add(btnMuelleNuevo);

        btnMuelleModificar.setText("Modificar");
        btnMuelleModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuelleModificarActionPerformed(evt);
            }
        });
        menuMuelles.add(btnMuelleModificar);

        btnMuelleEliminar.setText("Eliminar");
        btnMuelleEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuelleEliminarActionPerformed(evt);
            }
        });
        menuMuelles.add(btnMuelleEliminar);

        jMenuBar1.add(menuMuelles);

        jMenu1.setText("Registro");

        jMenuItem1.setText("Llegada");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Fin de descarga");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktop, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnVehiculoNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVehiculoNuevoActionPerformed
    try {
        Vehiculo_nuevo frm = new Vehiculo_nuevo(loggedUser);
        this.agregarVentanaHija(frm);
        
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, ex.getMessage(), "Advertencia", JOptionPane.ERROR_MESSAGE);
    }
}//GEN-LAST:event_btnVehiculoNuevoActionPerformed
    
private void btnVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVehiculosActionPerformed
}//GEN-LAST:event_btnVehiculosActionPerformed
    
private void btnVehiculoModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVehiculoModificarActionPerformed
    Vehiculo_modificar frm = new Vehiculo_modificar(loggedUser);
    this.agregarVentanaHija(frm);
}//GEN-LAST:event_btnVehiculoModificarActionPerformed
    
private void btnVehiculoEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVehiculoEliminarActionPerformed
    Vehiculo_eliminar frm = new Vehiculo_eliminar(loggedUser);
    this.agregarVentanaHija(frm);
}//GEN-LAST:event_btnVehiculoEliminarActionPerformed
    
private void btnMuelleNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuelleNuevoActionPerformed
    Muelle_nuevo frm = new Muelle_nuevo();
    this.agregarVentanaHija(frm);
}//GEN-LAST:event_btnMuelleNuevoActionPerformed
    
private void btnMuelleEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuelleEliminarActionPerformed
    Muelle_eliminar frm = new Muelle_eliminar();
    this.agregarVentanaHija(frm);
}//GEN-LAST:event_btnMuelleEliminarActionPerformed
    
private void btnMuelleModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuelleModificarActionPerformed
    Muelle_modificar frm = new Muelle_modificar();
    this.agregarVentanaHija(frm);
}//GEN-LAST:event_btnMuelleModificarActionPerformed
    
private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
    Asignacion_nueva frm = new Asignacion_nueva(loggedUser);
    this.agregarVentanaHija(frm);
}//GEN-LAST:event_jMenuItem1ActionPerformed
    
private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
    Asignacion_modificar frm = new Asignacion_modificar();
    this.agregarVentanaHija(frm);
}//GEN-LAST:event_jMenuItem2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                new Principal(null).setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btnMuelleEliminar;
    private javax.swing.JMenuItem btnMuelleModificar;
    private javax.swing.JMenuItem btnMuelleNuevo;
    private javax.swing.JMenuItem btnVehiculoEliminar;
    private javax.swing.JMenuItem btnVehiculoModificar;
    private javax.swing.JMenuItem btnVehiculoNuevo;
    private javax.swing.JMenu btnVehiculos;
    private javax.swing.JDesktopPane desktop;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenu menuMuelles;
    // End of variables declaration//GEN-END:variables
}
