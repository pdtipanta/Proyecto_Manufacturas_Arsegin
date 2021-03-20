/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Maquilas;

import Controlador.PlaceHolder_Textos;
import Modelo.Usuario;

/**
 *
 * @author David
 */
public class Panel_Maquilas extends javax.swing.JPanel {
    PlaceHolder_Textos textos_Place;

    /**
     * Creates new form Panel_Maquila
     */
    public Panel_Maquilas() {
        initComponents();
        this.tabla_Maquilas.getTableHeader().setReorderingAllowed(false) ;
        this.campo_Buscar.setEditable(false);
        //this.grupo_Botones.add(this.radio_Cedula);
        //this.grupo_Botones.add(this.radio_RUC);
        //this.campo_RUC.setEditable(false);
/*        this.grupo_Botones_Telefono.add(this.radio_Convencional);
        this.grupo_Botones_Telefono.add(this.radio_Celular);
        this.campo_Telefono.setEditable(false);
        this.textos_Place = new PlaceHolder_Textos("Nombre maquila", this.combo_Maquila);
        this.textos_Place = new PlaceHolder_Textos("172004xxxxxxx", this.campo_RUC);
        this.textos_Place = new PlaceHolder_Textos("Av principal OE7-489", this.campo_Direccion);
        this.textos_Place = new PlaceHolder_Textos("023005698", this.campo_Telefono);
        this.textos_Place = new PlaceHolder_Textos("Servicios de maquila", this.caja_Servicios);
        this.etiqueta_Indicador_RUC.setVisible( false );
        this.etiqueta_Indicador_Direccion.setVisible( false );
        this.etiqueta_Indicador_Telefono.setVisible( false );  
        this.etiqueta_Indicador_Servicio.setVisible( false );
        this.etiqueta_Indicador_Maquila.setVisible( false );*/
        this.boton_Modificar.setEnabled( false );
        this.boton_Eliminar.setEnabled( false );
       // this.etiqueta_Correccion_RUC.setVisible(false);
    }

    public void Roles(String rol) {
        if (rol.equals("Vendedor")) {
            boolean[] bandera = {false, false, false, false, false, false};
            this.habilitar_Rol(bandera);
        }

        if (rol.equals("Bodeguero")) {
            boolean[] bandera = {false, false, false, false, false, false};
            this.habilitar_Rol(bandera);
        }

        if (rol.equals("Contador")) {
            boolean[] bandera = {false, false, false, true, false, false};
            this.habilitar_Rol(bandera);
        }

        if (rol.equals("Administrador")) {
            boolean[] bandera = {false, false, false, true, false, false};
            this.habilitar_Rol(bandera);
        }
        
        if (rol.equals("Maquila")) {
            boolean[] bandera = {true, true, true, true, true, true};
            this.habilitar_Rol(bandera);
        }
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
        grupo_Botones_Telefono = new javax.swing.ButtonGroup();
        jToolBar1 = new javax.swing.JToolBar();
        boton_Nuevo_Maquila = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        boton_Modificar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        boton_Eliminar = new javax.swing.JButton();
        boton_Cerrar_Sesion = new javax.swing.JButton();
        etiqueta_Rol = new javax.swing.JLabel();
        etiqueta_Nombre_Usuario = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        campo_Buscar = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        combo_Opciones = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_Maquilas = new javax.swing.JTable();

        setBackground(new java.awt.Color(239, 239, 239));
        setMaximumSize(new java.awt.Dimension(1400, 800));
        setMinimumSize(new java.awt.Dimension(1400, 800));
        setPreferredSize(new java.awt.Dimension(1400, 800));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar1.setFloatable(false);
        jToolBar1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jToolBar1.setRollover(true);

        boton_Nuevo_Maquila.setBackground(new java.awt.Color(255, 255, 255));
        boton_Nuevo_Maquila.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        boton_Nuevo_Maquila.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/agregar-archivo.png"))); // NOI18N
        boton_Nuevo_Maquila.setText("Nuevo");
        boton_Nuevo_Maquila.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_Nuevo_Maquila.setFocusable(false);
        boton_Nuevo_Maquila.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        boton_Nuevo_Maquila.setMaximumSize(new java.awt.Dimension(100, 37));
        boton_Nuevo_Maquila.setMinimumSize(new java.awt.Dimension(100, 37));
        boton_Nuevo_Maquila.setPreferredSize(new java.awt.Dimension(100, 37));
        boton_Nuevo_Maquila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_Nuevo_MaquilaActionPerformed(evt);
            }
        });
        jToolBar1.add(boton_Nuevo_Maquila);
        jToolBar1.add(jSeparator1);
        jToolBar1.add(jSeparator2);

        boton_Modificar.setBackground(java.awt.Color.white);
        boton_Modificar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        boton_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar-documento.png"))); // NOI18N
        boton_Modificar.setText("Modificar");
        boton_Modificar.setToolTipText(" Editar");
        boton_Modificar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_Modificar.setMaximumSize(new java.awt.Dimension(100, 37));
        boton_Modificar.setMinimumSize(new java.awt.Dimension(100, 37));
        boton_Modificar.setPreferredSize(new java.awt.Dimension(100, 37));
        jToolBar1.add(boton_Modificar);
        jToolBar1.add(jSeparator3);

        boton_Eliminar.setBackground(java.awt.Color.white);
        boton_Eliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        boton_Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/boton-eliminar.png"))); // NOI18N
        boton_Eliminar.setText("Eliminar");
        boton_Eliminar.setToolTipText("Eliminar");
        boton_Eliminar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_Eliminar.setMaximumSize(new java.awt.Dimension(100, 37));
        boton_Eliminar.setMinimumSize(new java.awt.Dimension(100, 37));
        boton_Eliminar.setPreferredSize(new java.awt.Dimension(100, 37));
        jToolBar1.add(boton_Eliminar);

        add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 110, 800));

        boton_Cerrar_Sesion.setBackground(new java.awt.Color(255, 255, 255));
        boton_Cerrar_Sesion.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        boton_Cerrar_Sesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Exit-32.png"))); // NOI18N
        boton_Cerrar_Sesion.setText("Cerrar");
        boton_Cerrar_Sesion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(boton_Cerrar_Sesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(1280, 0, 120, 40));

        etiqueta_Rol.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        etiqueta_Rol.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        add(etiqueta_Rol, new org.netbeans.lib.awtextra.AbsoluteConstraints(1134, 20, 147, 20));

        etiqueta_Nombre_Usuario.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        etiqueta_Nombre_Usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/usuario.png"))); // NOI18N
        etiqueta_Nombre_Usuario.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        etiqueta_Nombre_Usuario.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        add(etiqueta_Nombre_Usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 0, 180, 40));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo blanco.png"))); // NOI18N
        add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 0, 180, 40));

        jToolBar2.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N
        jLabel1.setText("    ");
        jToolBar2.add(jLabel1);

        campo_Buscar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        campo_Buscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campo_Buscar.setMaximumSize(new java.awt.Dimension(750, 30));
        campo_Buscar.setMinimumSize(new java.awt.Dimension(750, 30));
        campo_Buscar.setPreferredSize(new java.awt.Dimension(730, 30));
        campo_Buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campo_BuscarKeyTyped(evt);
            }
        });
        jToolBar2.add(campo_Buscar);
        jToolBar2.add(jSeparator4);

        combo_Opciones.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        combo_Opciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione.....", "Por nombre", "Por RUC / CI", "Por servicio" }));
        combo_Opciones.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar2.add(combo_Opciones);

        add(jToolBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 990, 40));

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

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 1290, 760));
    }// </editor-fold>//GEN-END:initComponents

    private void boton_Nuevo_MaquilaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_Nuevo_MaquilaActionPerformed

    }//GEN-LAST:event_boton_Nuevo_MaquilaActionPerformed

    private void campo_BuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_BuscarKeyTyped

    }//GEN-LAST:event_campo_BuscarKeyTyped
/*
    public boolean etiquetas( boolean bandera ){
       
        if( this.combo_Maquila.getText().isEmpty() ){
            this.etiqueta_Indicador_Maquila.setVisible( true );
            bandera = false;
        }else{ 
            this.etiqueta_Indicador_Maquila.setVisible( false );
        }
            
        if( this.campo_RUC.getText().matches( "[0-9][0-9]{12}" ) && this.radio_RUC.isSelected() ){
            this.etiqueta_Indicador_RUC.setVisible( false );
        }else if(this.campo_RUC.getText().matches( "[0-9][0-9]{9}" ) && this.radio_Cedula.isSelected()){
            this.etiqueta_Indicador_RUC.setVisible( false );
        }
        else{
            this.etiqueta_Indicador_RUC.setVisible( true );
            bandera = false;
        }
            
        if( this.campo_Direccion.getText().isEmpty() ){ 
            this.etiqueta_Indicador_Direccion.setVisible( true );
            bandera = false;
        }else{ 
            this.etiqueta_Indicador_Direccion.setVisible( false );
        }
            
        if( this.campo_Telefono.getText().matches( "[0][1-9][0-9]{7}" ) && this.radio_Convencional.isSelected() ){
            this.etiqueta_Indicador_Telefono.setVisible( false );
        }else if(this.campo_Telefono.getText().matches( "[0-9][0-9]{9}" ) && this.radio_Celular.isSelected()){
            this.etiqueta_Indicador_Telefono.setVisible( false );
        }
        else{
            this.etiqueta_Indicador_Telefono.setVisible( true );
            bandera = false;
        }
            
        if( this.caja_Servicios.getText().isEmpty() ){
            this.etiqueta_Indicador_Servicio.setVisible( true );
            bandera = false;
        }else{ 
            this.etiqueta_Indicador_Servicio.setVisible( false );
        }
        return bandera;
    }*/
    /*
    public void limpiar_Campos(){
        this.combo_Maquila.setText("");
        this.campo_Direccion.setText( "" );
        this.campo_RUC.setText( "" );
        this.campo_Telefono.setText( "" );
        this.caja_Servicios.setText( "" );
        this.grupo_Botones.clearSelection();
        this.grupo_Botones_Telefono.clearSelection();
        this.campo_RUC.setEditable(false);
        this.campo_Telefono.setEditable(false);
    }*/
    /*
    public void botones( boolean bandera1, boolean bandera2, boolean bandera3, boolean bandera4, boolean bandera5  ){
        this.boton_Guardar.setEnabled(bandera1);
        this.boton_Nuevo_Maquila.setEnabled(bandera2);
        this.boton_Modificar.setEnabled(bandera3);
        this.boton_Eliminar.setEnabled(bandera4);
    }*/
    /*
    public void setCampos( String maquila, String RUC, String direccion, String telefono, String servicio ){
        this.botones(false, true, true, true, false);
        this.combo_Maquila.setText(maquila);
        this.campo_Direccion.setText(direccion);
        this.campo_RUC.setText(RUC);
        this.campo_Telefono.setText(telefono);
        this.caja_Servicios.setText(servicio);
    }*/
   /* 
    public void campos_Busqueda() {
        this.campo_RUC.setEditable(true);
        this.campo_Telefono.setEditable(true);

        if (this.campo_RUC.getText().length() == 10) {
            this.radio_Cedula.setSelected(true);
        } else if (this.campo_RUC.getText().length() == 13) {
            this.radio_RUC.setSelected(true);
        }

        if (this.campo_Telefono.getText().length() == 9) {
            this.radio_Convencional.setSelected(true);
        } else if (this.campo_Telefono.getText().length() == 10) {
            this.radio_Celular.setSelected(true);
        }
    }*/
    /*
    public void limpiar_Etiquetas_Campos() {
        this.etiqueta_Indicador_Maquila.setVisible(false);
        this.etiqueta_Indicador_RUC.setVisible(false);
        this.etiqueta_Indicador_Direccion.setVisible(false);
        this.etiqueta_Indicador_Telefono.setVisible(false);
        this.etiqueta_Indicador_Servicio.setVisible(false);
        this.etiqueta_Correccion_RUC.setVisible(false);
    }*/

    public void set_Usuario(Usuario usuario, String rol) {
        this.etiqueta_Nombre_Usuario.setText(usuario.getNombre() + " " + usuario.getApellido());
        this.etiqueta_Rol.setText(rol);
    }
    
    public void habilitar_Rol(boolean[] bandera) {
        //this.boton_Guardar.setVisible(bandera[0]);
        this.boton_Modificar.setVisible(bandera[1]);
        this.boton_Eliminar.setVisible(bandera[2]);
        //this.boton_Buscar.setVisible(bandera[3]);
       // this.radio_Cedula.setVisible(bandera[4]);
       // this.radio_RUC.setVisible(bandera[4]);
       // this.radio_Celular.setVisible(bandera[5]);
       // this.radio_Convencional.setVisible(bandera[5]);
    }
    /*
    public void correccion_Campos(String valor) {
        if (valor.equals(this.campo_RUC.getText())) {
            this.etiqueta_Correccion_RUC.setVisible(true);
        } else {
            this.etiqueta_Correccion_RUC.setVisible(false);
        }
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton boton_Cerrar_Sesion;
    public javax.swing.JButton boton_Eliminar;
    public javax.swing.JButton boton_Modificar;
    public javax.swing.JButton boton_Nuevo_Maquila;
    public javax.swing.JTextField campo_Buscar;
    public javax.swing.JComboBox<String> combo_Opciones;
    public javax.swing.JLabel etiqueta_Nombre_Usuario;
    public javax.swing.JLabel etiqueta_Rol;
    private javax.swing.ButtonGroup grupo_Botones;
    private javax.swing.ButtonGroup grupo_Botones_Telefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    public javax.swing.JTable tabla_Maquilas;
    // End of variables declaration//GEN-END:variables
}
