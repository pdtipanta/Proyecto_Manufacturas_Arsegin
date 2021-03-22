/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Registro_Usuarios;

import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author David
 */
public class Dialogo_Buscar_Usuarios extends javax.swing.JDialog {

    /**
     * Creates new form Dialogo_Buscar_Cliente
     */
    public Dialogo_Buscar_Usuarios(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);
        this.tabla_Usuarios.getTableHeader().setFont( new Font( "Arial", Font.BOLD, 13 ) );
        this.tabla_Usuarios.getTableHeader().setBackground( new Color( 32, 136, 203 ));
        this.tabla_Usuarios.getTableHeader().setForeground( new Color( 255, 255, 255)); 
        this.campo_Buscar.setEditable(false);
        this.tabla_Usuarios.getTableHeader().setReorderingAllowed(false) ;
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
        tabla_Usuarios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        campo_Buscar = new javax.swing.JTextField();
        combo_Opciones = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Buscar usuarios");
        setBackground(new java.awt.Color(0, 153, 153));
        setMinimumSize(new java.awt.Dimension(1150, 410));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(452, 390));

        tabla_Usuarios.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabla_Usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cedula", "Nombre", "Apellido", "Correo", "Rol"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla_Usuarios);
        if (tabla_Usuarios.getColumnModel().getColumnCount() > 0) {
            tabla_Usuarios.getColumnModel().getColumn(0).setPreferredWidth(60);
            tabla_Usuarios.getColumnModel().getColumn(1).setPreferredWidth(150);
            tabla_Usuarios.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 1070, 200));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setText("Buscar:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(37, 34, -1, -1));

        campo_Buscar.setEditable(false);
        campo_Buscar.setBackground(new java.awt.Color(255, 255, 255));
        campo_Buscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_Buscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        campo_Buscar.setMaximumSize(new java.awt.Dimension(2, 20));
        campo_Buscar.setMinimumSize(new java.awt.Dimension(2, 20));
        campo_Buscar.setPreferredSize(new java.awt.Dimension(2, 20));
        campo_Buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campo_BuscarKeyTyped(evt);
            }
        });
        getContentPane().add(campo_Buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(94, 32, 304, 23));

        combo_Opciones.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        combo_Opciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar.....", "Por CI", "Por nombre", "Por rol" }));
        combo_Opciones.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(combo_Opciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 32, 230, 23));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 102, 1150, 10));

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
            java.util.logging.Logger.getLogger(Dialogo_Buscar_Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Buscar_Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Buscar_Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Buscar_Usuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dialogo_Buscar_Usuarios dialog = new Dialogo_Buscar_Usuarios(new javax.swing.JFrame(), true);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    public javax.swing.JTable tabla_Usuarios;
    // End of variables declaration//GEN-END:variables
}
