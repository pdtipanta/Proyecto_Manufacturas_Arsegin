/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Maquilas.Orden_De_Produccion;

import Controlador.Render_Tabla_Orden_Produccion;
import Modelo.Usuario;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;
/**
 *
 * @author David
 */
public class Panel_Orden_De_Produccion extends javax.swing.JPanel {

    private TableRowSorter     TRSFiltro;
    /**
     * Creates new form Panel_Cotizacion
     */
    public Panel_Orden_De_Produccion() {
        initComponents();
        this.campo_Busqueda.setEditable(false);
        this.fecha_1.setVisible(false);
        this.fecha_2.setVisible(false);
        this.boton_Fecha.setVisible(false);
        this.fecha_1.setDate(new Date());
        this.fecha_2.setDate(new Date());
        this.etiqueta_Error_Fecha.setVisible(false);
        this.boton_Modificar_Orden.setEnabled(false);
        this.boton_Generar_Orden.setEnabled(false);
        this.tabla_Consulta_Orden_Produccion.getTableHeader().setReorderingAllowed(false);
        this.render_Columna();
    }

    public void Roles(String rol) {
        if (rol.equals("Vendedor")) {
            boolean[] bandera = {false, false, false, false, false, false, false};
            this.habilitar_Rol(bandera);
        }

        if (rol.equals("Bodeguero")) {
            boolean[] bandera = {false, false, false, false, false, false, false};
            this.habilitar_Rol(bandera);
        }

        if (rol.equals("Contador")) {
            boolean[] bandera = {true, false, false, true, false, false, false};
            this.habilitar_Rol(bandera);
        }

        if (rol.equals("Administrador")) {
            boolean[] bandera = {false, false, false, true, false, false, false};
            this.habilitar_Rol(bandera);
        }

        if (rol.equals("Maquila")) {
            boolean[] bandera = {true, true, true, true, true, true, true};
            this.habilitar_Rol(bandera);
        }
    }
    
     public void habilitar_Rol(boolean[] bandera) {
        this.boton_Nueva_Orden.setVisible(bandera[0]);
        this.boton_Modificar_Orden.setVisible(bandera[2]);
        this.boton_Generar_Orden.setVisible(bandera[3]);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        boton_Nueva_Orden = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        boton_Modificar_Orden = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        boton_Generar_Orden = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        boton_Cerrar_Sesion = new javax.swing.JButton();
        etiqueta_Rol = new javax.swing.JLabel();
        etiqueta_Nombre_Usuario = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_Consulta_Orden_Produccion = new javax.swing.JTable();
        jToolBar2 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        campo_Busqueda = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        fecha_1 = new com.toedter.calendar.JDateChooser();
        fecha_2 = new com.toedter.calendar.JDateChooser();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        etiqueta_Error_Fecha = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        boton_Fecha = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        combo_Opciones = new javax.swing.JComboBox<>();

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
        jToolBar1.add(jSeparator1);

        boton_Nueva_Orden.setBackground(new java.awt.Color(255, 255, 255));
        boton_Nueva_Orden.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        boton_Nueva_Orden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/produccion add.png"))); // NOI18N
        boton_Nueva_Orden.setText("Nuevo");
        boton_Nueva_Orden.setToolTipText("Nueva cotizacion");
        boton_Nueva_Orden.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_Nueva_Orden.setFocusable(false);
        boton_Nueva_Orden.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        boton_Nueva_Orden.setMaximumSize(new java.awt.Dimension(120, 42));
        boton_Nueva_Orden.setMinimumSize(new java.awt.Dimension(120, 42));
        boton_Nueva_Orden.setPreferredSize(new java.awt.Dimension(120, 42));
        boton_Nueva_Orden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_Nueva_OrdenActionPerformed(evt);
            }
        });
        jToolBar1.add(boton_Nueva_Orden);
        jToolBar1.add(jSeparator2);

        boton_Modificar_Orden.setBackground(new java.awt.Color(255, 255, 255));
        boton_Modificar_Orden.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        boton_Modificar_Orden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/produccion mod.png"))); // NOI18N
        boton_Modificar_Orden.setText("Modificar");
        boton_Modificar_Orden.setToolTipText("Modificar orden");
        boton_Modificar_Orden.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_Modificar_Orden.setFocusable(false);
        boton_Modificar_Orden.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        boton_Modificar_Orden.setMaximumSize(new java.awt.Dimension(120, 42));
        boton_Modificar_Orden.setMinimumSize(new java.awt.Dimension(120, 42));
        boton_Modificar_Orden.setPreferredSize(new java.awt.Dimension(120, 42));
        jToolBar1.add(boton_Modificar_Orden);
        jToolBar1.add(jSeparator3);

        boton_Generar_Orden.setBackground(new java.awt.Color(255, 255, 255));
        boton_Generar_Orden.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        boton_Generar_Orden.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/imprimir.png"))); // NOI18N
        boton_Generar_Orden.setText("Generar");
        boton_Generar_Orden.setToolTipText("Generar orden");
        boton_Generar_Orden.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_Generar_Orden.setFocusable(false);
        boton_Generar_Orden.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        boton_Generar_Orden.setMaximumSize(new java.awt.Dimension(120, 42));
        boton_Generar_Orden.setMinimumSize(new java.awt.Dimension(120, 42));
        boton_Generar_Orden.setPreferredSize(new java.awt.Dimension(120, 42));
        jToolBar1.add(boton_Generar_Orden);
        jToolBar1.add(jSeparator5);

        add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 800));

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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo blanco.png"))); // NOI18N
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 0, 180, 40));

        tabla_Consulta_Orden_Produccion.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabla_Consulta_Orden_Produccion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No_Orden", "Maquila", "RUC / CI", "Fecha", "Estado", "Valor", "Telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla_Consulta_Orden_Produccion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_Consulta_Orden_ProduccionMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_Consulta_Orden_Produccion);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 1270, 760));

        jToolBar2.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N
        jLabel1.setText("    ");
        jToolBar2.add(jLabel1);

        campo_Busqueda.setEditable(false);
        campo_Busqueda.setBackground(new java.awt.Color(255, 255, 255));
        campo_Busqueda.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_Busqueda.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campo_Busqueda.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        campo_Busqueda.setMaximumSize(new java.awt.Dimension(450, 30));
        campo_Busqueda.setMinimumSize(new java.awt.Dimension(450, 30));
        campo_Busqueda.setPreferredSize(new java.awt.Dimension(440, 30));
        campo_Busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campo_BusquedaKeyTyped(evt);
            }
        });
        jToolBar2.add(campo_Busqueda);
        jToolBar2.add(jSeparator4);

        fecha_1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Desde", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
        jToolBar2.add(fecha_1);

        fecha_2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hasta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
        jToolBar2.add(fecha_2);
        jToolBar2.add(jSeparator6);

        etiqueta_Error_Fecha.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        etiqueta_Error_Fecha.setForeground(new java.awt.Color(204, 0, 51));
        etiqueta_Error_Fecha.setText("*");
        jToolBar2.add(etiqueta_Error_Fecha);
        jToolBar2.add(jSeparator7);

        boton_Fecha.setBackground(new java.awt.Color(255, 255, 255));
        boton_Fecha.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        boton_Fecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/busqueda (1).png"))); // NOI18N
        boton_Fecha.setText("Buscar");
        boton_Fecha.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        boton_Fecha.setMaximumSize(new java.awt.Dimension(75, 40));
        boton_Fecha.setMinimumSize(new java.awt.Dimension(75, 40));
        boton_Fecha.setPreferredSize(new java.awt.Dimension(75, 40));
        jToolBar2.add(boton_Fecha);
        jToolBar2.add(jSeparator8);

        combo_Opciones.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        combo_Opciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione.....", "Por numero", "Por nombre", "Por RUC", "Por fecha" }));
        combo_Opciones.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        combo_Opciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_OpcionesActionPerformed(evt);
            }
        });
        jToolBar2.add(combo_Opciones);

        add(jToolBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 0, 970, 40));
    }// </editor-fold>//GEN-END:initComponents

    private void boton_Nueva_OrdenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_Nueva_OrdenActionPerformed

    }//GEN-LAST:event_boton_Nueva_OrdenActionPerformed

    private void campo_BusquedaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_BusquedaKeyTyped
        if (this.combo_Opciones.getSelectedItem().equals("Seleccionar.....")) {
            this.campo_Busqueda.setEditable(false);
        } else {
            this.campo_Busqueda.setEditable(true);
            if (evt.getSource() == this.campo_Busqueda) {
                this.campo_Busqueda.addKeyListener(new KeyAdapter() {

                    public void keyReleased(final KeyEvent e) {
                        filtro();
                    }
                });

                TRSFiltro = new TableRowSorter(this.tabla_Consulta_Orden_Produccion.getModel());
                this.tabla_Consulta_Orden_Produccion.setRowSorter(TRSFiltro);
            }
        }
    }//GEN-LAST:event_campo_BusquedaKeyTyped

    private void tabla_Consulta_Orden_ProduccionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_Consulta_Orden_ProduccionMouseClicked
        seleccion_Tabla(this.tabla_Consulta_Orden_Produccion.getSelectedRow());
    }//GEN-LAST:event_tabla_Consulta_Orden_ProduccionMouseClicked

    private void combo_OpcionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_OpcionesActionPerformed

        if (evt.getSource() == this.combo_Opciones) {
            if (this.combo_Opciones.getSelectedItem().equals("Por fecha")) {
                this.desactivar_Calendarios(true);
            } else {
                this.desactivar_Calendarios(false);
            }
        }
    }//GEN-LAST:event_combo_OpcionesActionPerformed
    
    public void filtro() {
        if (this.combo_Opciones.getSelectedItem() == "Por numero") {
            filtrar_Tabla(0);
        } else if (this.combo_Opciones.getSelectedItem() == "Por nombre") {
            filtrar_Tabla(1);
        } else if (this.combo_Opciones.getSelectedItem() == "Por RUC") {
            filtrar_Tabla(2);
        }
    }

    public void filtrar_Tabla(int valor) {
        seleccion_Tabla(this.tabla_Consulta_Orden_Produccion.getSelectedRow());
        TRSFiltro.setRowFilter(RowFilter.regexFilter("(?i)" + this.campo_Busqueda.getText(), valor));
    }
    
    public void seleccion_Tabla(int bandera) {
        if (bandera != -1) {
            this.boton_Modificar_Orden.setEnabled(true);
            this.boton_Generar_Orden.setEnabled(true);
        } else {
            this.boton_Modificar_Orden.setEnabled(false);
            this.boton_Generar_Orden.setEnabled(false);
        }
    }
    
    public void set_Usuario(Usuario usuario, String rol) {
        this.etiqueta_Nombre_Usuario.setText(usuario.getNombre() + " " + usuario.getApellido());
        this.etiqueta_Rol.setText(rol);
    }

    public void desactivar_Calendarios(boolean bandera) {
        this.fecha_1.setVisible(bandera);
        this.fecha_2.setVisible(bandera);
        this.boton_Fecha.setVisible(bandera);
    }

    public boolean verificar_Campos() {
        boolean bandera = true;

        if (this.fecha_1.getDate() == null) {
            bandera = false;
        }

        if (this.fecha_2.getDate() == null) {
            bandera = false;
        }
        return bandera;
    }

    public String calendario_Inicio() {
        Date fecha = this.fecha_1.getDate();
        return new SimpleDateFormat("yyyy-MM-dd").format(fecha);
    }

    public String calendario_Final() {
        Date fecha = this.fecha_2.getDate();
        return new SimpleDateFormat("yyyy-MM-dd").format(fecha);
    }

    public void etiqueta_Error_Etiqueta(boolean bandera) {
        this.etiqueta_Error_Fecha.setVisible(bandera);
    }
    
    public void render_Columna(){
        Render_Tabla_Orden_Produccion render_Tabla_Orden0 = new Render_Tabla_Orden_Produccion(0);
        this.tabla_Consulta_Orden_Produccion.getColumnModel().getColumn(0).setCellRenderer(render_Tabla_Orden0);

        Render_Tabla_Orden_Produccion render_Tabla_Orden3 = new Render_Tabla_Orden_Produccion(3);
        this.tabla_Consulta_Orden_Produccion.getColumnModel().getColumn(3).setCellRenderer(render_Tabla_Orden3);

        Render_Tabla_Orden_Produccion render_Tabla_Orden4 = new Render_Tabla_Orden_Produccion(4);
        this.tabla_Consulta_Orden_Produccion.getColumnModel().getColumn(4).setCellRenderer(render_Tabla_Orden4);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton boton_Cerrar_Sesion;
    public javax.swing.JButton boton_Fecha;
    public javax.swing.JButton boton_Generar_Orden;
    public javax.swing.JButton boton_Modificar_Orden;
    public javax.swing.JButton boton_Nueva_Orden;
    public javax.swing.JTextField campo_Busqueda;
    public javax.swing.JComboBox<String> combo_Opciones;
    public javax.swing.JLabel etiqueta_Error_Fecha;
    public javax.swing.JLabel etiqueta_Nombre_Usuario;
    public javax.swing.JLabel etiqueta_Rol;
    public com.toedter.calendar.JDateChooser fecha_1;
    public com.toedter.calendar.JDateChooser fecha_2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    public javax.swing.JTable tabla_Consulta_Orden_Produccion;
    // End of variables declaration//GEN-END:variables
}
