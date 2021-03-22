/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Inventario;

import Modelo.Inventario;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author David
 */
public class Dialogo_Inventario extends javax.swing.JDialog {

    /**
     * Creates new form Dialogo_Inventario
     */
    public Dialogo_Inventario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);
        this.etiqueta_Indicador_Codigo.setVisible( false );
        this.etiqueta_Indicador_Descripcion.setVisible( false ); 
        this.etiqueta_Indicador_Cantidad.setVisible( false );
        this.etiqueta_Indicador_Precio_Compra.setVisible( false );
        this.etiqueta_Indicador_Precio_Venta.setVisible( false );
        this.etiqueta_Indicador_Proveedor.setVisible( false );
        this.etiqueta_Correccion_Producto.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        campo_Descripcion = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        campo_Cantidad = new javax.swing.JFormattedTextField();
        campo_Precio_Compra = new javax.swing.JTextField();
        campo_Precio_Venta = new javax.swing.JTextField();
        campo_Codigo = new javax.swing.JTextField();
        etiqueta_Indicador_Proveedor = new javax.swing.JLabel();
        etiqueta_Indicador_Codigo = new javax.swing.JLabel();
        etiqueta_Indicador_Cantidad = new javax.swing.JLabel();
        etiqueta_Indicador_Precio_Compra = new javax.swing.JLabel();
        etiqueta_Indicador_Precio_Venta = new javax.swing.JLabel();
        etiqueta_Indicador_Descripcion = new javax.swing.JLabel();
        combo_Proveedor = new javax.swing.JTextField();
        boton_Proveedor = new javax.swing.JButton();
        etiqueta_Correccion_Producto = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        boton_Guardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(752, 465));
        setMinimumSize(new java.awt.Dimension(752, 465));
        setPreferredSize(new java.awt.Dimension(752, 465));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Codigo:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, -1));

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Descripcion:");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        jLabel9.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel9.setText("Precio compra:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, -1, -1));

        jLabel10.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel10.setText("Precio venta:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        campo_Descripcion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_Descripcion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campo_Descripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campo_DescripcionKeyTyped(evt);
            }
        });
        getContentPane().add(campo_Descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 346, 24));

        jLabel11.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel11.setText("Proveedor:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, -1, -1));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel12.setText("Cantidad:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        campo_Cantidad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campo_Cantidad.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        campo_Cantidad.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_Cantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campo_CantidadKeyTyped(evt);
            }
        });
        getContentPane().add(campo_Cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 160, 24));

        campo_Precio_Compra.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_Precio_Compra.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campo_Precio_Compra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campo_Precio_CompraKeyTyped(evt);
            }
        });
        getContentPane().add(campo_Precio_Compra, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 140, 24));

        campo_Precio_Venta.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_Precio_Venta.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campo_Precio_Venta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campo_Precio_VentaActionPerformed(evt);
            }
        });
        campo_Precio_Venta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campo_Precio_VentaKeyTyped(evt);
            }
        });
        getContentPane().add(campo_Precio_Venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 200, 24));

        campo_Codigo.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_Codigo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campo_Codigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campo_CodigoKeyTyped(evt);
            }
        });
        getContentPane().add(campo_Codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 270, 24));

        etiqueta_Indicador_Proveedor.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        etiqueta_Indicador_Proveedor.setForeground(new java.awt.Color(198, 0, 0));
        etiqueta_Indicador_Proveedor.setText("*");
        getContentPane().add(etiqueta_Indicador_Proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 340, -1, -1));

        etiqueta_Indicador_Codigo.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        etiqueta_Indicador_Codigo.setForeground(new java.awt.Color(198, 0, 0));
        etiqueta_Indicador_Codigo.setText("*");
        getContentPane().add(etiqueta_Indicador_Codigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, -1, -1));

        etiqueta_Indicador_Cantidad.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        etiqueta_Indicador_Cantidad.setForeground(new java.awt.Color(198, 0, 0));
        etiqueta_Indicador_Cantidad.setText("*");
        getContentPane().add(etiqueta_Indicador_Cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 190, -1, -1));

        etiqueta_Indicador_Precio_Compra.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        etiqueta_Indicador_Precio_Compra.setForeground(new java.awt.Color(198, 0, 0));
        etiqueta_Indicador_Precio_Compra.setText("*");
        getContentPane().add(etiqueta_Indicador_Precio_Compra, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 240, -1, -1));

        etiqueta_Indicador_Precio_Venta.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        etiqueta_Indicador_Precio_Venta.setForeground(new java.awt.Color(198, 0, 0));
        etiqueta_Indicador_Precio_Venta.setText("*");
        getContentPane().add(etiqueta_Indicador_Precio_Venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, -1, -1));

        etiqueta_Indicador_Descripcion.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        etiqueta_Indicador_Descripcion.setForeground(new java.awt.Color(198, 0, 0));
        etiqueta_Indicador_Descripcion.setText("*");
        getContentPane().add(etiqueta_Indicador_Descripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, -1, -1));

        combo_Proveedor.setEditable(false);
        combo_Proveedor.setBackground(new java.awt.Color(255, 255, 255));
        combo_Proveedor.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        combo_Proveedor.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        combo_Proveedor.setMaximumSize(new java.awt.Dimension(2, 20));
        combo_Proveedor.setMinimumSize(new java.awt.Dimension(2, 20));
        combo_Proveedor.setPreferredSize(new java.awt.Dimension(2, 20));
        getContentPane().add(combo_Proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 320, 24));

        boton_Proveedor.setBackground(new java.awt.Color(255, 255, 255));
        boton_Proveedor.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        boton_Proveedor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/boton-anadir-usuario (1).png"))); // NOI18N
        boton_Proveedor.setText("Proveedor");
        boton_Proveedor.setToolTipText("Agregar proveedor");
        boton_Proveedor.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_Proveedor.setMaximumSize(new java.awt.Dimension(110, 37));
        boton_Proveedor.setMinimumSize(new java.awt.Dimension(110, 37));
        boton_Proveedor.setPreferredSize(new java.awt.Dimension(110, 37));
        getContentPane().add(boton_Proveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, 110, -1));

        etiqueta_Correccion_Producto.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        etiqueta_Correccion_Producto.setForeground(new java.awt.Color(220, 0, 0));
        etiqueta_Correccion_Producto.setText("El codigo de producto ya se encuentra registrado para otro producto");
        getContentPane().add(etiqueta_Correccion_Producto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 390, -1));

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        boton_Guardar.setBackground(java.awt.Color.white);
        boton_Guardar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        boton_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/disquete.png"))); // NOI18N
        boton_Guardar.setText("Guardar");
        boton_Guardar.setToolTipText(" Agregar");
        boton_Guardar.setMaximumSize(new java.awt.Dimension(103, 37));
        boton_Guardar.setMinimumSize(new java.awt.Dimension(60, 37));
        boton_Guardar.setPreferredSize(new java.awt.Dimension(60, 37));
        boton_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_GuardarActionPerformed(evt);
            }
        });
        jToolBar1.add(boton_Guardar);

        getContentPane().add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campo_DescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_DescripcionKeyTyped
        char c = evt.getKeyChar();
        if( c == evt.VK_SPACE && this.campo_Descripcion.getText().length() == 0  ) evt.consume();
    }//GEN-LAST:event_campo_DescripcionKeyTyped

    private void campo_CantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_CantidadKeyTyped
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != evt.VK_BACK_SPACE )) evt.consume();
    }//GEN-LAST:event_campo_CantidadKeyTyped

    private void campo_Precio_CompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_Precio_CompraKeyTyped
       validar_Campos( evt, this.campo_Precio_Compra );
    }//GEN-LAST:event_campo_Precio_CompraKeyTyped

    private void campo_Precio_VentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campo_Precio_VentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campo_Precio_VentaActionPerformed

    private void campo_Precio_VentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_Precio_VentaKeyTyped
        validar_Campos( evt, this.campo_Precio_Venta );
    }//GEN-LAST:event_campo_Precio_VentaKeyTyped

    private void campo_CodigoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_CodigoKeyTyped
        char c = evt.getKeyChar();
        if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9') && (c < '#' || c > '&') && (c < '-' || c > '/') && (c == evt.VK_SPACE)) {
            evt.consume();
        }
    }//GEN-LAST:event_campo_CodigoKeyTyped

    private void boton_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_GuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton_GuardarActionPerformed

    public void validar_Campos(KeyEvent evt, JTextField campo) {
        char c = evt.getKeyChar();
        if (((c < '0') || (c > '9')) && (c != evt.VK_BACK_SPACE) && (c != '.' || campo.getText().contains("."))) {
            evt.consume();
        }
    }
     
    public boolean etiquetas() {
        boolean bandera = true;

        if (this.campo_Codigo.getText().isEmpty()) {
            this.etiqueta_Indicador_Codigo.setVisible(true);
            bandera = false;
        } else {
            this.etiqueta_Indicador_Codigo.setVisible(false);
        }

        if (this.campo_Descripcion.getText().isEmpty()) {
            this.etiqueta_Indicador_Descripcion.setVisible(true);
            bandera = false;
        } else {
            this.etiqueta_Indicador_Descripcion.setVisible(false);
        }

        if (this.campo_Cantidad.getText().isEmpty()) {
            this.etiqueta_Indicador_Cantidad.setVisible(true);
            bandera = false;
        } else {
            this.etiqueta_Indicador_Cantidad.setVisible(false);
        }

        if (this.campo_Precio_Compra.getText().isEmpty()) {
            this.etiqueta_Indicador_Precio_Compra.setVisible(true);
            bandera = false;
        } else {
            this.etiqueta_Indicador_Precio_Compra.setVisible(false);
        }

        if (this.campo_Precio_Venta.getText().isEmpty()) {
            this.etiqueta_Indicador_Precio_Venta.setVisible(true);
            bandera = false;
        } else {
            this.etiqueta_Indicador_Precio_Venta.setVisible(false);
        }

        if (this.combo_Proveedor.getText().isEmpty()) {
            this.etiqueta_Indicador_Proveedor.setVisible(true);
            bandera = false;
        } else {
            this.etiqueta_Indicador_Proveedor.setVisible(false);
        }
        return bandera;
    }
  
    public void correccion_Campos(String valor) {
        if (valor.equals(this.campo_Codigo.getText())) {
            this.etiqueta_Correccion_Producto.setVisible(true);
        } else {
            this.etiqueta_Correccion_Producto.setVisible(false);
        }
    }

    public void setCampos(Inventario inventario) {
        this.campo_Codigo.setText(inventario.getCodigo());
        this.campo_Descripcion.setText(inventario.getDescripcion());
        this.campo_Cantidad.setText(String.valueOf(inventario.getCantidad_Disponible()));
        this.campo_Precio_Compra.setText(String.valueOf(inventario.getPrecio_Compra()));
        this.campo_Precio_Venta.setText(String.valueOf(inventario.getPrecio_Venta()));
        this.combo_Proveedor.setText(inventario.getProveedor());
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
            java.util.logging.Logger.getLogger(Dialogo_Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Inventario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dialogo_Inventario dialog = new Dialogo_Inventario(new javax.swing.JFrame(), true);
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
    public javax.swing.JButton boton_Guardar;
    public javax.swing.JButton boton_Proveedor;
    public javax.swing.JFormattedTextField campo_Cantidad;
    public javax.swing.JTextField campo_Codigo;
    public javax.swing.JTextField campo_Descripcion;
    public javax.swing.JTextField campo_Precio_Compra;
    public javax.swing.JTextField campo_Precio_Venta;
    public javax.swing.JTextField combo_Proveedor;
    private javax.swing.JLabel etiqueta_Correccion_Producto;
    private javax.swing.JLabel etiqueta_Indicador_Cantidad;
    private javax.swing.JLabel etiqueta_Indicador_Codigo;
    private javax.swing.JLabel etiqueta_Indicador_Descripcion;
    private javax.swing.JLabel etiqueta_Indicador_Precio_Compra;
    private javax.swing.JLabel etiqueta_Indicador_Precio_Venta;
    private javax.swing.JLabel etiqueta_Indicador_Proveedor;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
