/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Maquilas;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author David
 */
public class Dialogo_Buscar_Maquilas extends javax.swing.JDialog {

    /**
     * Creates new form Dialogo_Buscar_Cliente
     */
    public Dialogo_Buscar_Maquilas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);
        this.tabla_Maquilas.getTableHeader().setFont( new Font( "Arial", Font.BOLD, 13 ) );
        this.tabla_Maquilas.getTableHeader().setBackground( new Color( 32, 136, 203 ));
        this.tabla_Maquilas.getTableHeader().setForeground( new Color( 255, 255, 255)); 
        this.campo_Buscar.setEditable(false);
        this.tabla_Maquilas.getTableHeader().setReorderingAllowed(false) ;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupo_Botones = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_Maquilas = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        campo_Buscar = new javax.swing.JTextField();
        combo_Opciones = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Maquilas");
        setMaximumSize(new java.awt.Dimension(1144, 405));
        setMinimumSize(new java.awt.Dimension(1144, 405));
        setPreferredSize(new java.awt.Dimension(1144, 405));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabla_Maquilas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Maquila", "Nombre", "RUC/ CI", "Servicios", "Direccion", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla_Maquilas);
        if (tabla_Maquilas.getColumnModel().getColumnCount() > 0) {
            tabla_Maquilas.getColumnModel().getColumn(1).setPreferredWidth(200);
            tabla_Maquilas.getColumnModel().getColumn(2).setPreferredWidth(20);
            tabla_Maquilas.getColumnModel().getColumn(3).setPreferredWidth(200);
            tabla_Maquilas.getColumnModel().getColumn(4).setPreferredWidth(200);
            tabla_Maquilas.getColumnModel().getColumn(5).setPreferredWidth(20);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 118, 1064, 230));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Buscar:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 34, -1, -1));

        campo_Buscar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campo_Buscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        campo_Buscar.setMaximumSize(new java.awt.Dimension(2, 20));
        campo_Buscar.setMinimumSize(new java.awt.Dimension(2, 20));
        campo_Buscar.setPreferredSize(new java.awt.Dimension(2, 20));
        campo_Buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campo_BuscarKeyTyped(evt);
            }
        });
        getContentPane().add(campo_Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(91, 32, 328, 23));

        combo_Opciones.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        combo_Opciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione.....", "Por nombre", "Por RUC / CI", "Por servicio" }));
        combo_Opciones.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(combo_Opciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 32, 190, 23));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Imagen-fondo-aurora-celeste_1.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1140, 400));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campo_BuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_BuscarKeyTyped
        
    }//GEN-LAST:event_campo_BuscarKeyTyped

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
            java.util.logging.Logger.getLogger(Dialogo_Buscar_Maquilas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Buscar_Maquilas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Buscar_Maquilas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Buscar_Maquilas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dialogo_Buscar_Maquilas dialog = new Dialogo_Buscar_Maquilas(new javax.swing.JFrame(), true);
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
    public javax.swing.JTextField campo_Buscar;
    public javax.swing.JComboBox<String> combo_Opciones;
    public javax.swing.ButtonGroup grupo_Botones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tabla_Maquilas;
    // End of variables declaration//GEN-END:variables
}
