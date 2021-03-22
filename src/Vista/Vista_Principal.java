/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.Rol;
import Modelo.Usuario;
import javax.swing.JMenuBar;

/**
 *
 * @author David
 */
public class Vista_Principal extends javax.swing.JFrame {
    private JMenuBar menu;
    private Usuario usuario;
    private Rol rol;
    /**
     * Creates new form Vista_Princi
     */
    public Vista_Principal() {
        initComponents();
        menu_Principal.setVisible( false );
        setLocationRelativeTo( null );
        setTitle( "MANUFACTURAS ARSEGIN" );
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_Contenedor = new javax.swing.JPanel();
        menu_Principal = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1400, 830));
        setMinimumSize(new java.awt.Dimension(1400, 830));
        setResizable(false);

        Panel_Contenedor.setBackground(new java.awt.Color(255, 255, 255));
        Panel_Contenedor.setMaximumSize(new java.awt.Dimension(1400, 800));
        Panel_Contenedor.setMinimumSize(new java.awt.Dimension(1400, 800));
        Panel_Contenedor.setPreferredSize(new java.awt.Dimension(1400, 800));

        menu_Principal.setBackground(new java.awt.Color(255, 255, 255));
        menu_Principal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        menu_Principal.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        menu_Principal.setMaximumSize(new java.awt.Dimension(338, 30));
        menu_Principal.setMinimumSize(new java.awt.Dimension(338, 30));
        menu_Principal.setPreferredSize(new java.awt.Dimension(338, 30));
        setJMenuBar(menu_Principal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Contenedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_Contenedor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Vista_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vista_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vista_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vista_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vista_Principal().setVisible(true);
            }
        });
    }
    
    public void set_Menu(JMenuBar menu){
        this.menu = menu;
    }
    
    public void borrar_Menu(){
        menu.removeAll();
        menu.revalidate();
        menu.repaint();
    }
    
    public void set_Usuario( Usuario usuario){
        this.usuario = usuario;
    }
    
    public void set_Rol(Rol rol){
        this.rol = rol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Rol getRol() {
        return rol;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel Panel_Contenedor;
    public javax.swing.JMenuBar menu_Principal;
    // End of variables declaration//GEN-END:variables
}
