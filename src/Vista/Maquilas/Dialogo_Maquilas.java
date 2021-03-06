/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Maquilas;

import Modelo.Maquila;

/**
 *
 * @author David
 */
public class Dialogo_Maquilas extends javax.swing.JDialog {

    /**
     * Creates new form Dialogo_Maquilas
     */
    public Dialogo_Maquilas(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);
        this.grupo_Botones.add(this.radio_Cedula);
        this.grupo_Botones.add(this.radio_RUC);
        this.campo_RUC.setEditable(false);
        this.grupo_Botones_Telefono.add(this.radio_Convencional);
        this.grupo_Botones_Telefono.add(this.radio_Celular);
        this.campo_Telefono.setEditable(false);
        this.etiqueta_Indicador_RUC.setVisible( false );
        this.etiqueta_Indicador_Direccion.setVisible( false );
        this.etiqueta_Indicador_Telefono.setVisible( false );  
        this.etiqueta_Indicador_Servicio.setVisible( false );
        this.etiqueta_Indicador_Maquila.setVisible( false );
        this.etiqueta_Correccion_RUC.setVisible(false);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        campo_RUC = new javax.swing.JTextField();
        campo_Direccion = new javax.swing.JTextField();
        campo_Telefono = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        caja_Servicios = new javax.swing.JTextArea();
        etiqueta_Indicador_RUC = new javax.swing.JLabel();
        etiqueta_Indicador_Direccion = new javax.swing.JLabel();
        etiqueta_Indicador_Telefono = new javax.swing.JLabel();
        etiqueta_Indicador_Servicio = new javax.swing.JLabel();
        combo_Maquila = new javax.swing.JTextField();
        etiqueta_Indicador_Maquila = new javax.swing.JLabel();
        radio_RUC = new javax.swing.JRadioButton();
        radio_Cedula = new javax.swing.JRadioButton();
        radio_Convencional = new javax.swing.JRadioButton();
        radio_Celular = new javax.swing.JRadioButton();
        etiqueta_Correccion_RUC = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        boton_Guardar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(820, 500));
        setMinimumSize(new java.awt.Dimension(820, 500));
        setPreferredSize(new java.awt.Dimension(820, 500));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Maquila:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("R.U.C/ CI:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel3.setText("Direccion:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, -1, -1));

        jLabel4.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel4.setText("Telefono:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, -1, -1));

        jLabel5.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel5.setText("Servicio:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        campo_RUC.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_RUC.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campo_RUC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campo_RUCFocusGained(evt);
            }
        });
        campo_RUC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campo_RUCKeyTyped(evt);
            }
        });
        getContentPane().add(campo_RUC, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 249, 24));

        campo_Direccion.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_Direccion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campo_Direccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campo_DireccionFocusGained(evt);
            }
        });
        campo_Direccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campo_DireccionKeyTyped(evt);
            }
        });
        getContentPane().add(campo_Direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 249, 24));

        campo_Telefono.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_Telefono.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campo_Telefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campo_TelefonoFocusGained(evt);
            }
        });
        campo_Telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campo_TelefonoKeyTyped(evt);
            }
        });
        getContentPane().add(campo_Telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 150, 24));

        caja_Servicios.setColumns(20);
        caja_Servicios.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        caja_Servicios.setRows(5);
        caja_Servicios.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        caja_Servicios.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                caja_ServiciosFocusGained(evt);
            }
        });
        caja_Servicios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                caja_ServiciosKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(caja_Servicios);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, 287, 100));

        etiqueta_Indicador_RUC.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        etiqueta_Indicador_RUC.setForeground(new java.awt.Color(198, 0, 0));
        etiqueta_Indicador_RUC.setText("*");
        getContentPane().add(etiqueta_Indicador_RUC, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 130, -1, -1));

        etiqueta_Indicador_Direccion.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        etiqueta_Indicador_Direccion.setForeground(new java.awt.Color(198, 0, 0));
        etiqueta_Indicador_Direccion.setText("*");
        getContentPane().add(etiqueta_Indicador_Direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, -1, -1));

        etiqueta_Indicador_Telefono.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        etiqueta_Indicador_Telefono.setForeground(new java.awt.Color(198, 0, 0));
        etiqueta_Indicador_Telefono.setText("*");
        getContentPane().add(etiqueta_Indicador_Telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, -1, -1));

        etiqueta_Indicador_Servicio.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        etiqueta_Indicador_Servicio.setForeground(new java.awt.Color(195, 0, 0));
        etiqueta_Indicador_Servicio.setText("*");
        getContentPane().add(etiqueta_Indicador_Servicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, -1, -1));

        combo_Maquila.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        combo_Maquila.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        combo_Maquila.setMaximumSize(new java.awt.Dimension(2, 20));
        combo_Maquila.setMinimumSize(new java.awt.Dimension(2, 20));
        combo_Maquila.setPreferredSize(new java.awt.Dimension(2, 20));
        combo_Maquila.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                combo_MaquilaFocusGained(evt);
            }
        });
        getContentPane().add(combo_Maquila, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 310, 24));

        etiqueta_Indicador_Maquila.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        etiqueta_Indicador_Maquila.setForeground(new java.awt.Color(198, 0, 0));
        etiqueta_Indicador_Maquila.setText("*");
        getContentPane().add(etiqueta_Indicador_Maquila, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, -1));

        radio_RUC.setBackground(new java.awt.Color(255, 255, 255));
        radio_RUC.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        radio_RUC.setText("RUC");
        radio_RUC.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                radio_RUCFocusGained(evt);
            }
        });
        radio_RUC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_RUCActionPerformed(evt);
            }
        });
        getContentPane().add(radio_RUC, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, -1, -1));

        radio_Cedula.setBackground(new java.awt.Color(255, 255, 255));
        radio_Cedula.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        radio_Cedula.setText("Cedula");
        radio_Cedula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                radio_CedulaFocusGained(evt);
            }
        });
        radio_Cedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_CedulaActionPerformed(evt);
            }
        });
        getContentPane().add(radio_Cedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 140, -1, -1));

        radio_Convencional.setBackground(new java.awt.Color(255, 255, 255));
        radio_Convencional.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        radio_Convencional.setText("Convencional");
        radio_Convencional.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                radio_ConvencionalFocusGained(evt);
            }
        });
        radio_Convencional.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_ConvencionalActionPerformed(evt);
            }
        });
        getContentPane().add(radio_Convencional, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 200, -1, -1));

        radio_Celular.setBackground(new java.awt.Color(255, 255, 255));
        radio_Celular.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        radio_Celular.setText("Celular");
        radio_Celular.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                radio_CelularFocusGained(evt);
            }
        });
        radio_Celular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_CelularActionPerformed(evt);
            }
        });
        getContentPane().add(radio_Celular, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, -1, -1));

        etiqueta_Correccion_RUC.setFont(new java.awt.Font("Arial", 3, 11)); // NOI18N
        etiqueta_Correccion_RUC.setForeground(new java.awt.Color(220, 0, 0));
        etiqueta_Correccion_RUC.setText("El RUC/CI ya se encuentra registrado para otra maquila");
        getContentPane().add(etiqueta_Correccion_RUC, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 152, 310, -1));

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        boton_Guardar.setBackground(java.awt.Color.white);
        boton_Guardar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        boton_Guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/maquila guar.png"))); // NOI18N
        boton_Guardar.setText("Guardar");
        boton_Guardar.setToolTipText(" Agregar");
        boton_Guardar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_Guardar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_Guardar.setMaximumSize(new java.awt.Dimension(80, 52));
        boton_Guardar.setMinimumSize(new java.awt.Dimension(120, 40));
        boton_Guardar.setPreferredSize(new java.awt.Dimension(120, 40));
        boton_Guardar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        boton_Guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_GuardarActionPerformed(evt);
            }
        });
        jToolBar1.add(boton_Guardar);

        getContentPane().add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 55));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void campo_RUCKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_RUCKeyTyped
        char c = evt.getKeyChar();
        if (this.radio_RUC.isSelected()) {
            if (c < '0' || c > '9' || this.campo_RUC.getText().length() >= 13) {
                evt.consume();
            }
        }

        if (this.radio_Cedula.isSelected()) {
            if (c < '0' || c > '9' || this.campo_RUC.getText().length() >= 10) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_campo_RUCKeyTyped

    private void campo_DireccionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_DireccionKeyTyped
        char c = evt.getKeyChar();
        if( c == evt.VK_SPACE && this.campo_Direccion.getText().length() == 0 ) evt.consume();
        else if(  ( c < 'a' || c > 'z' ) && ( c < 'A' || c>'Z' ) && ( c < '0' || c > '9' ) && ( c < '-' || c > '.' ) && c != evt.VK_SPACE ){
            evt.consume();
        }
    }//GEN-LAST:event_campo_DireccionKeyTyped

    private void campo_TelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_TelefonoKeyTyped
        char c = evt.getKeyChar();

        if (this.radio_Convencional.isSelected()) {
            if (c < '0' || c > '9' || this.campo_Telefono.getText().length() >= 9) {
                evt.consume();
            }
        }

        if (this.radio_Celular.isSelected()) {
            if (c < '0' || c > '9' || this.campo_Telefono.getText().length() >= 10) {
                evt.consume();
            }
        }
    }//GEN-LAST:event_campo_TelefonoKeyTyped

    private void caja_ServiciosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_caja_ServiciosKeyTyped
        char c = evt.getKeyChar();
        if( c == evt.VK_SPACE && this.caja_Servicios.getText().length() == 0 ) evt.consume();
        else if( ( c < 'a' || c > 'z' ) && ( c < 'A' || c>'Z' ) && ( c < '0' || c > '9' ) && c != evt.VK_SPACE ){
            evt.consume();
        }
    }//GEN-LAST:event_caja_ServiciosKeyTyped

    private void radio_RUCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_RUCActionPerformed
        this.campo_RUC.setEditable(true);
        this.campo_RUC.setText("");
    }//GEN-LAST:event_radio_RUCActionPerformed

    private void radio_CedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_CedulaActionPerformed
        this.campo_RUC.setEditable(true);
        this.campo_RUC.setText("");
    }//GEN-LAST:event_radio_CedulaActionPerformed

    private void radio_ConvencionalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_ConvencionalActionPerformed
        this.campo_Telefono.setEditable(true);
        this.campo_Telefono.setText("");
    }//GEN-LAST:event_radio_ConvencionalActionPerformed

    private void radio_CelularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_CelularActionPerformed
        this.campo_Telefono.setEditable(true);
        this.campo_Telefono.setText("");
    }//GEN-LAST:event_radio_CelularActionPerformed

    private void boton_GuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_GuardarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boton_GuardarActionPerformed

    private void combo_MaquilaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_combo_MaquilaFocusGained
        this.colores_Bordes();
        this.combo_Maquila.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 180, 20)));
    }//GEN-LAST:event_combo_MaquilaFocusGained

    private void campo_RUCFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campo_RUCFocusGained
        this.colores_Bordes();
        this.campo_RUC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 180, 20)));
    }//GEN-LAST:event_campo_RUCFocusGained

    private void radio_RUCFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_radio_RUCFocusGained
        this.colores_Bordes();
        this.radio_RUC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 180, 20)));
    }//GEN-LAST:event_radio_RUCFocusGained

    private void radio_CedulaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_radio_CedulaFocusGained
        this.colores_Bordes();
        this.radio_Cedula.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 180, 20)));
    }//GEN-LAST:event_radio_CedulaFocusGained

    private void campo_DireccionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campo_DireccionFocusGained
        this.colores_Bordes();
        this.campo_Direccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 180, 20)));
    }//GEN-LAST:event_campo_DireccionFocusGained

    private void campo_TelefonoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_campo_TelefonoFocusGained
        this.colores_Bordes();
        this.campo_Telefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 180, 20)));
    }//GEN-LAST:event_campo_TelefonoFocusGained

    private void radio_ConvencionalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_radio_ConvencionalFocusGained
        this.colores_Bordes();
        this.radio_Convencional.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 180, 20)));
    }//GEN-LAST:event_radio_ConvencionalFocusGained

    private void radio_CelularFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_radio_CelularFocusGained
        this.colores_Bordes();
        this.radio_Celular.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 180, 20)));
    }//GEN-LAST:event_radio_CelularFocusGained

    private void caja_ServiciosFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_caja_ServiciosFocusGained
        this.colores_Bordes();
        this.caja_Servicios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 180, 20)));
    }//GEN-LAST:event_caja_ServiciosFocusGained

    public boolean etiquetas(){
       boolean bandera = true;
       
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
    }
    
    public void correccion_Campos(String valor) {
        if (valor.equals(this.campo_RUC.getText())) {
            this.etiqueta_Correccion_RUC.setVisible(true);
        } else {
            this.etiqueta_Correccion_RUC.setVisible(false);
        }
    }

    public void setCampos(Maquila maquila) {
        this.combo_Maquila.setText(maquila.getMaquila());
        this.campo_Direccion.setText(maquila.getDireccion());
        this.campo_RUC.setText(maquila.getRUC());
        this.campo_Telefono.setText(maquila.getTelefono());
        this.caja_Servicios.setText(maquila.getServicio());
    }
    
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
    }
    
    public void colores_Bordes() {
        this.caja_Servicios.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        this.campo_Direccion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        this.campo_RUC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        this.campo_Telefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
        this.combo_Maquila.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));
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
            java.util.logging.Logger.getLogger(Dialogo_Maquilas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Maquilas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Maquilas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dialogo_Maquilas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Dialogo_Maquilas dialog = new Dialogo_Maquilas(new javax.swing.JFrame(), true);
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
    public javax.swing.JTextArea caja_Servicios;
    public javax.swing.JTextField campo_Direccion;
    public javax.swing.JTextField campo_RUC;
    public javax.swing.JTextField campo_Telefono;
    public javax.swing.JTextField combo_Maquila;
    private javax.swing.JLabel etiqueta_Correccion_RUC;
    private javax.swing.JLabel etiqueta_Indicador_Direccion;
    private javax.swing.JLabel etiqueta_Indicador_Maquila;
    private javax.swing.JLabel etiqueta_Indicador_RUC;
    private javax.swing.JLabel etiqueta_Indicador_Servicio;
    private javax.swing.JLabel etiqueta_Indicador_Telefono;
    private javax.swing.ButtonGroup grupo_Botones;
    private javax.swing.ButtonGroup grupo_Botones_Telefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JRadioButton radio_Cedula;
    private javax.swing.JRadioButton radio_Celular;
    private javax.swing.JRadioButton radio_Convencional;
    private javax.swing.JRadioButton radio_RUC;
    // End of variables declaration//GEN-END:variables
}
