/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Maquilas.Orden_De_Produccion;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 *
 * @author David
 */
public class Dialogo_Agregar_Trabajo extends javax.swing.JDialog {
    private DecimalFormat           formato_Numero = new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.US));

    /**
     * Creates new form Dialogo_Agregar_Trabajo
     */
    public Dialogo_Agregar_Trabajo(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.campo_Total.setText("0.0");
        this.campo_Cantidad.setText("0");
        this.campo_Unitario.setText("0");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        campo_Cantidad = new javax.swing.JTextField();
        campo_Descripcion = new javax.swing.JTextField();
        campo_Unitario = new javax.swing.JTextField();
        campo_Total = new javax.swing.JTextField();
        jToolBar1 = new javax.swing.JToolBar();
        boton_Agregar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nuevo trabajo");
        setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        setMaximumSize(new java.awt.Dimension(557, 300));
        setMinimumSize(new java.awt.Dimension(557, 300));
        setPreferredSize(new java.awt.Dimension(557, 300));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Cantidad:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Descripcion:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 125, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Valor unitario:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Valor total:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 195, -1, -1));

        campo_Cantidad.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_Cantidad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campo_Cantidad.setMaximumSize(new java.awt.Dimension(2, 20));
        campo_Cantidad.setMinimumSize(new java.awt.Dimension(2, 20));
        campo_Cantidad.setPreferredSize(new java.awt.Dimension(2, 20));
        campo_Cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_CantidadActionPerformed(evt);
            }
        });
        campo_Cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campo_CantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campo_CantidadKeyTyped(evt);
            }
        });
        getContentPane().add(campo_Cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 108, 25));

        campo_Descripcion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_Descripcion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campo_Descripcion.setMaximumSize(new java.awt.Dimension(2, 20));
        campo_Descripcion.setMinimumSize(new java.awt.Dimension(2, 20));
        campo_Descripcion.setPreferredSize(new java.awt.Dimension(2, 20));
        campo_Descripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campo_DescripcionKeyTyped(evt);
            }
        });
        getContentPane().add(campo_Descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 125, 330, 25));

        campo_Unitario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_Unitario.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campo_Unitario.setMaximumSize(new java.awt.Dimension(2, 20));
        campo_Unitario.setMinimumSize(new java.awt.Dimension(2, 20));
        campo_Unitario.setPreferredSize(new java.awt.Dimension(2, 20));
        campo_Unitario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                campo_UnitarioKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campo_UnitarioKeyTyped(evt);
            }
        });
        getContentPane().add(campo_Unitario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 120, 25));

        campo_Total.setEditable(false);
        campo_Total.setBackground(new java.awt.Color(255, 255, 255));
        campo_Total.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_Total.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campo_Total.setMaximumSize(new java.awt.Dimension(2, 20));
        campo_Total.setMinimumSize(new java.awt.Dimension(2, 20));
        campo_Total.setPreferredSize(new java.awt.Dimension(2, 20));
        getContentPane().add(campo_Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 195, 113, 25));

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        boton_Agregar.setBackground(new java.awt.Color(255, 255, 255));
        boton_Agregar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        boton_Agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/trabajo add.png"))); // NOI18N
        boton_Agregar.setText("Agregar");
        boton_Agregar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_Agregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_Agregar.setMaximumSize(new java.awt.Dimension(120, 54));
        boton_Agregar.setMinimumSize(new java.awt.Dimension(120, 37));
        boton_Agregar.setPreferredSize(new java.awt.Dimension(120, 37));
        boton_Agregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(boton_Agregar);

        getContentPane().add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 2, 665, 56));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campo_CantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_CantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_CantidadActionPerformed

    private void campo_CantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_CantidadKeyTyped
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != evt.VK_BACK_SPACE )) evt.consume();
    }//GEN-LAST:event_campo_CantidadKeyTyped

    private void campo_DescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_DescripcionKeyTyped
        char c = evt.getKeyChar();
        if( c == evt.VK_SPACE && this.campo_Descripcion.getText().length() == 0  ) evt.consume();
    }//GEN-LAST:event_campo_DescripcionKeyTyped

    private void campo_UnitarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_UnitarioKeyTyped
        char c = evt.getKeyChar();
        if ( ( ( c < '0' ) || ( c > '9' ) ) && ( c != evt.VK_BACK_SPACE) && ( c != '.' || this.campo_Unitario.getText().contains( "." ) ) ) evt.consume();
    }//GEN-LAST:event_campo_UnitarioKeyTyped

    private void campo_CantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_CantidadKeyReleased
        calcular_Valor();
    }//GEN-LAST:event_campo_CantidadKeyReleased

    private void campo_UnitarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_UnitarioKeyReleased
        calcular_Valor();
    }//GEN-LAST:event_campo_UnitarioKeyReleased

    public void calcular_Valor() {
        try {
            double total = Integer.valueOf(this.campo_Cantidad.getText()) * Double.valueOf(this.campo_Unitario.getText());
            this.campo_Total.setText(this.formato_Numero.format(total));
        } catch (NumberFormatException e) {
        }
    }
    
    public boolean verificar_Campos() {
        boolean bandera = true;

        if (this.campo_Descripcion.getText().isEmpty()) {
            bandera = false;
        }

        if (this.campo_Cantidad.getText().isEmpty() || this.campo_Cantidad.getText().equals("0")) {
            bandera = false;
        }

        if (this.campo_Unitario.getText().isEmpty() || this.campo_Unitario.getText().equals("0")) {
            bandera = false;
        }

        if (this.campo_Total.getText().isEmpty() || this.campo_Total.getText().equals("0")) {
            bandera = false;
        }
        return bandera;
    }
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
            java.util.logging.Logger.getLogger(Dialogo_Agregar_Trabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Agregar_Trabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Agregar_Trabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Agregar_Trabajo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dialogo_Agregar_Trabajo dialog = new Dialogo_Agregar_Trabajo(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton boton_Agregar;
    public javax.swing.JTextField campo_Cantidad;
    public javax.swing.JTextField campo_Descripcion;
    public javax.swing.JTextField campo_Total;
    public javax.swing.JTextField campo_Unitario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
