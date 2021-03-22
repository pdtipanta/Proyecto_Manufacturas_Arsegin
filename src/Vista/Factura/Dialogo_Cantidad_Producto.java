/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Factura;

import Modelo.Inventario;

/**
 *
 * @author David
 */
public class Dialogo_Cantidad_Producto extends javax.swing.JDialog {

    /**
     * Creates new form Dialogo_Buscar_Producto
     */
    public Dialogo_Cantidad_Producto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);
        this.setResizable(false);
        this.campo_Cantidad.setText("0");
        this.etiqueta_Advertencia.setVisible(false);
        this.etiqueta_Total.setText("0.0");
        this.boton_Agregar_Producto.setEnabled(false);
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
        etiqueta_Producto = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        campo_Cantidad = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        etiqueta_Precio = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        etiqueta_Total = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        etiqueta_Stock = new javax.swing.JLabel();
        boton_Agregar_Producto = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        etiqueta_Codigo = new javax.swing.JLabel();
        etiqueta_Advertencia = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Producto");
        setMaximumSize(new java.awt.Dimension(545, 360));
        setMinimumSize(new java.awt.Dimension(545, 360));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Producto:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        etiqueta_Producto.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        getContentPane().add(etiqueta_Producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 310, 20));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Cantidad:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        campo_Cantidad.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_Cantidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        campo_Cantidad.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                campo_CantidadPropertyChange(evt);
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
        getContentPane().add(campo_Cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 110, 20));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Precio unidad:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        etiqueta_Precio.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        etiqueta_Precio.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        etiqueta_Precio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                etiqueta_PrecioPropertyChange(evt);
            }
        });
        getContentPane().add(etiqueta_Precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 70, 20));

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Precio total:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, -1, 20));

        etiqueta_Total.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        getContentPane().add(etiqueta_Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 130, 20));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Stock:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        etiqueta_Stock.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        etiqueta_Stock.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        etiqueta_Stock.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        etiqueta_Stock.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        getContentPane().add(etiqueta_Stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 110, 20));

        boton_Agregar_Producto.setBackground(new java.awt.Color(255, 255, 255));
        boton_Agregar_Producto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        boton_Agregar_Producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/anadir.png"))); // NOI18N
        boton_Agregar_Producto.setText("Agregar");
        boton_Agregar_Producto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(boton_Agregar_Producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 325, 110, 40));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Codigo:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        etiqueta_Codigo.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        getContentPane().add(etiqueta_Codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 320, 20));

        etiqueta_Advertencia.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        etiqueta_Advertencia.setForeground(java.awt.Color.red);
        etiqueta_Advertencia.setText("La cantidad no debe ser mayor al stock");
        getContentPane().add(etiqueta_Advertencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, 220, -1));

        jLabel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Informacion producto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 520, 290));

        jLabel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 520, 70));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void campo_CantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_CantidadKeyTyped
        char c = evt.getKeyChar();
        if (c < '0' || c > '9' || this.campo_Cantidad.getText().length() >= 13) {
            evt.consume();
        }
    }//GEN-LAST:event_campo_CantidadKeyTyped

    private void etiqueta_PrecioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_etiqueta_PrecioPropertyChange

    }//GEN-LAST:event_etiqueta_PrecioPropertyChange

    private void campo_CantidadPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_campo_CantidadPropertyChange
       
    }//GEN-LAST:event_campo_CantidadPropertyChange

    private void campo_CantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_CantidadKeyReleased
        if (this.campo_Cantidad.getText().matches("^[1-9][0-9]*$")) {
            if (Integer.valueOf(this.campo_Cantidad.getText()) <= Integer.valueOf(this.etiqueta_Stock.getText())) {
                this.etiqueta_Advertencia.setVisible(false);
                this.calcular_Valores();
                this.boton_Agregar_Producto.setEnabled(true);
            } else {
                this.campo_Cantidad.setText("0");
                this.etiqueta_Total.setText("0.0");
                this.etiqueta_Advertencia.setVisible(true);
                this.boton_Agregar_Producto.setEnabled(false);
            }
        } else {
            this.etiqueta_Total.setText("0.0");
            this.boton_Agregar_Producto.setEnabled(false);
        }
    }//GEN-LAST:event_campo_CantidadKeyReleased

    public void calcular_Valores() {
        double total = Integer.valueOf(this.campo_Cantidad.getText()) * Double.valueOf(this.etiqueta_Precio.getText());
        this.etiqueta_Total.setText(String.format("%.2f", total));
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
            java.util.logging.Logger.getLogger(Dialogo_Cantidad_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Cantidad_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Cantidad_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Cantidad_Producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dialogo_Cantidad_Producto dialog = new Dialogo_Cantidad_Producto(new javax.swing.JFrame(), true);
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
    
    public void setValores(Inventario inventario){
        this.etiqueta_Producto.setText(inventario.getDescripcion());
        this.etiqueta_Codigo.setText(inventario.getCodigo());
        this.etiqueta_Stock.setText(String.valueOf(inventario.getCantidad_Disponible()));
        this.etiqueta_Precio.setText(String.valueOf(inventario.getPrecio_Venta()));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton boton_Agregar_Producto;
    public javax.swing.JTextField campo_Cantidad;
    public javax.swing.JLabel etiqueta_Advertencia;
    private javax.swing.JLabel etiqueta_Codigo;
    public javax.swing.JLabel etiqueta_Precio;
    public javax.swing.JLabel etiqueta_Producto;
    public javax.swing.JLabel etiqueta_Stock;
    public javax.swing.JLabel etiqueta_Total;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
