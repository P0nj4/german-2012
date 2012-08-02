/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Asignacion_nueva.java
 *
 * Created on 29-jul-2012, 14:53:17
 */
package iu;

import dominio.Muelle;
import dominio.Usuario;
import dominio.Vehiculo;
import java.util.ArrayList;
import logicaDeNegocio.Fachada;

/**
 *
 * @author German
 */
public class Asignacion_nueva extends JInternalBaseClass {
    
    Usuario u = null;
    ArrayList todosLosMuelles = null;
    ArrayList todosLosVehiculos = null;

    /** Creates new form Asignacion_nueva */
    public Asignacion_nueva(Usuario usuLogueado) {
        initComponents();
        u = usuLogueado;
        try {
            ddlMuelle.removeAllItems();
            ddlVehiculos.removeAllItems();
            ddlPrioridad.removeAllItems();
            ddlVehiculos.addItem("Seleccionar...");
            ddlMuelle.addItem("Seleccionar...");
            todosLosMuelles = Fachada.getInstance().listarMuelles();
            for (int i = 0; i < todosLosMuelles.size(); i++) {
                ddlMuelle.addItem(todosLosMuelles.get(i));
            }
            
            todosLosVehiculos = Fachada.getInstance().listarVehiculos(u);
            for (int i = 0; i < todosLosVehiculos.size(); i++) {
                ddlVehiculos.addItem(todosLosVehiculos.get(i));
            }
            for (int i = 0; i < 5; i++) {
                ddlPrioridad.addItem(i + 1);
            }
            
        } catch (Exception ex) {
            msgBoxError(ex.getMessage());
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ddlVehiculos = new javax.swing.JComboBox();
        ddlMuelle = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtPeso = new javax.swing.JTextField();
        ddlPrioridad = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        ddlVehiculos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        ddlMuelle.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Vehículo");

        jLabel2.setText("Muelle");

        jLabel3.setText("Peso");

        ddlPrioridad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Prioridad");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addGap(5, 5, 5)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ddlPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ddlVehiculos, 0, 193, Short.MAX_VALUE)
                            .addComponent(ddlMuelle, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPeso))))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ddlVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ddlMuelle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ddlPrioridad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
    this.closeWindow();
}//GEN-LAST:event_btnCancelarActionPerformed
    
private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
    try {
        if (ddlMuelle.getSelectedIndex() > 0 && ddlVehiculos.getSelectedIndex() > 0) {
            Muelle mSeleccionado;
            mSeleccionado = (Muelle) todosLosMuelles.get(ddlMuelle.getSelectedIndex() - 1);
            Vehiculo vSeleccionado;
            vSeleccionado = (Vehiculo) todosLosVehiculos.get(ddlVehiculos.getSelectedIndex() - 1);
            Fachada.getInstance().nuevaAsignacion(mSeleccionado, u, vSeleccionado, txtPeso.getText(), ddlPrioridad.getSelectedIndex() + 1);
            closeWindow();
        } else {
            msgBoxError("Los datos ingresados no son correctos, por favor vuelva a intentar");
        }
    } catch (Exception x) {        
        msgBoxError(x.getMessage());
        
    }
}//GEN-LAST:event_btnAceptarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox ddlMuelle;
    private javax.swing.JComboBox ddlPrioridad;
    private javax.swing.JComboBox ddlVehiculos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField txtPeso;
    // End of variables declaration//GEN-END:variables
}