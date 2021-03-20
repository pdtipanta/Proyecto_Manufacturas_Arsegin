/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.Inventario;

import Controlador.PlaceHolder_Textos;
import Modelo.Rol;
import Modelo.Usuario;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;

/**
 *
 * @author David
 */
public class Panel_Inventarios extends javax.swing.JPanel {
    PlaceHolder_Textos textos_Place;
    
    /**
     * Creates new form Panel_Inventario
     */
    public Panel_Inventarios() {
        initComponents(); /*
        this.textos_Place = new PlaceHolder_Textos("Codigo del producto", this.campo_Codigo);
        this.textos_Place = new PlaceHolder_Textos("Descripcion del producto", this.campo_Descripcion);
        this.textos_Place = new PlaceHolder_Textos("Unidades producto", this.campo_Cantidad);
        this.textos_Place = new PlaceHolder_Textos("Precio de compra", this.campo_Precio_Compra);
        this.textos_Place = new PlaceHolder_Textos("Precio de venta al publico", this.campo_Precio_Venta);;
        this.textos_Place = new PlaceHolder_Textos("Nombre proveedor", this.combo_Proveedor);*/
  /*      this.etiqueta_Indicador_Codigo.setVisible( false );
        this.etiqueta_Indicador_Descripcion.setVisible( false ); 
        this.etiqueta_Indicador_Cantidad.setVisible( false );
        this.etiqueta_Indicador_Precio_Compra.setVisible( false );
        this.etiqueta_Indicador_Precio_Venta.setVisible( false );
        this.etiqueta_Indicador_Proveedor.setVisible( false );*/
  this.tabla_Inventario.getTableHeader().setReorderingAllowed(false) ;
        this.boton_Modificar.setEnabled( false );
        this.boton_Eliminar.setEnabled( false );
        //this.etiqueta_Correccion_Producto.setVisible(false);
    }
    
    public void Roles(String rol) {
        if (rol.equals("Vendedor")) {
            boolean[] bandera = {false, false, false, false, true, false, true, false};
            this.habilitar_Rol(bandera);
        }
        
        if (rol.equals("Bodeguero")) {
            boolean[] bandera = {true, true, true, true, true, true, true, true};
            this.habilitar_Rol(bandera);
        }
        
        if (rol.equals("Contador")) {
            boolean[] bandera = {true, true, true, true, true, true, true, true};
            this.habilitar_Rol(bandera);
        }
        
        if (rol.equals("Administrador")) {
            boolean[] bandera = {true, true, true, true, true, true, true, true};
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

        jToolBar1 = new javax.swing.JToolBar();
        boton_Nuevo_Producto = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        boton_Modificar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        boton_Eliminar = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        boton_Informe = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        boton_Devoluciones = new javax.swing.JButton();
        boton_Cerrar_Sesion = new javax.swing.JButton();
        etiqueta_Rol = new javax.swing.JLabel();
        etiqueta_Nombre_Usuario = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jToolBar2 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        campo_Buscar = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        combo_Opciones = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_Inventario = new javax.swing.JTable();

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

        boton_Nuevo_Producto.setBackground(new java.awt.Color(255, 255, 255));
        boton_Nuevo_Producto.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        boton_Nuevo_Producto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/agregar-archivo.png"))); // NOI18N
        boton_Nuevo_Producto.setText("Nuevo");
        boton_Nuevo_Producto.setFocusable(false);
        boton_Nuevo_Producto.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        boton_Nuevo_Producto.setMaximumSize(new java.awt.Dimension(100, 37));
        boton_Nuevo_Producto.setMinimumSize(new java.awt.Dimension(100, 37));
        boton_Nuevo_Producto.setPreferredSize(new java.awt.Dimension(100, 37));
        boton_Nuevo_Producto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton_Nuevo_ProductoActionPerformed(evt);
            }
        });
        jToolBar1.add(boton_Nuevo_Producto);
        jToolBar1.add(jSeparator4);
        jToolBar1.add(jSeparator1);

        boton_Modificar.setBackground(java.awt.Color.white);
        boton_Modificar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        boton_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/editar-documento.png"))); // NOI18N
        boton_Modificar.setText("Modificar");
        boton_Modificar.setToolTipText(" Editar");
        boton_Modificar.setMaximumSize(new java.awt.Dimension(113, 37));
        boton_Modificar.setMinimumSize(new java.awt.Dimension(60, 37));
        jToolBar1.add(boton_Modificar);
        jToolBar1.add(jSeparator2);

        boton_Eliminar.setBackground(java.awt.Color.white);
        boton_Eliminar.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        boton_Eliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/boton-eliminar.png"))); // NOI18N
        boton_Eliminar.setText("Eliminar");
        boton_Eliminar.setToolTipText("Eliminar");
        boton_Eliminar.setMaximumSize(new java.awt.Dimension(100, 37));
        boton_Eliminar.setMinimumSize(new java.awt.Dimension(60, 37));
        jToolBar1.add(boton_Eliminar);
        jToolBar1.add(jSeparator3);

        boton_Informe.setBackground(new java.awt.Color(255, 255, 255));
        boton_Informe.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        boton_Informe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reanudar.png"))); // NOI18N
        boton_Informe.setText("Reporte");
        boton_Informe.setToolTipText("Reporte inventario");
        boton_Informe.setMaximumSize(new java.awt.Dimension(100, 37));
        boton_Informe.setMinimumSize(new java.awt.Dimension(100, 37));
        boton_Informe.setPreferredSize(new java.awt.Dimension(100, 37));
        jToolBar1.add(boton_Informe);
        jToolBar1.add(jSeparator5);

        boton_Devoluciones.setBackground(new java.awt.Color(255, 255, 255));
        boton_Devoluciones.setText("Devoluciones");
        boton_Devoluciones.setFocusable(false);
        boton_Devoluciones.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton_Devoluciones.setMaximumSize(new java.awt.Dimension(100, 37));
        boton_Devoluciones.setMinimumSize(new java.awt.Dimension(100, 37));
        boton_Devoluciones.setPreferredSize(new java.awt.Dimension(100, 37));
        boton_Devoluciones.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(boton_Devoluciones);

        add(jToolBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 120, 800));

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

        jToolBar2.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N
        jLabel1.setText("   ");
        jToolBar2.add(jLabel1);

        campo_Buscar.setEditable(false);
        campo_Buscar.setBackground(new java.awt.Color(255, 255, 255));
        campo_Buscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        campo_Buscar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        campo_Buscar.setMaximumSize(new java.awt.Dimension(730, 30));
        campo_Buscar.setMinimumSize(new java.awt.Dimension(730, 30));
        campo_Buscar.setPreferredSize(new java.awt.Dimension(730, 30));
        campo_Buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                campo_BuscarKeyTyped(evt);
            }
        });
        jToolBar2.add(campo_Buscar);
        jToolBar2.add(jSeparator6);

        combo_Opciones.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        combo_Opciones.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar.....", "Por Codigo", "Por Descripcion", "Por Proveedor" }));
        combo_Opciones.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar2.add(combo_Opciones);

        add(jToolBar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 980, 40));

        tabla_Inventario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tabla_Inventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Proveedor", "Disponible", "P. Compra", "P. Venta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
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
        tabla_Inventario.setMaximumSize(new java.awt.Dimension(1396, 600));
        tabla_Inventario.setMinimumSize(new java.awt.Dimension(1396, 600));
        jScrollPane1.setViewportView(tabla_Inventario);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 40, 1280, 760));
    }// </editor-fold>//GEN-END:initComponents

    private void boton_Nuevo_ProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton_Nuevo_ProductoActionPerformed
       // limpiar_Campos();
       // this.botones(true, true, false, false, true, true);
    }//GEN-LAST:event_boton_Nuevo_ProductoActionPerformed

    private void campo_BuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_campo_BuscarKeyTyped

    }//GEN-LAST:event_campo_BuscarKeyTyped

    public void validar_Campos( KeyEvent evt, JTextField campo ){
        char c = evt.getKeyChar();
        if ( ( ( c < '0' ) || ( c > '9' ) ) && ( c != evt.VK_BACK_SPACE) && ( c != '.' || campo.getText().contains( "." ) ) ) evt.consume();
    }
    /*
    public boolean etiquetas( boolean bandera ){
        
        if( this.campo_Codigo.getText().isEmpty() ){ 
            this.etiqueta_Indicador_Codigo.setVisible( true );
            bandera = false;
        }else{ 
            this.etiqueta_Indicador_Codigo.setVisible( false );
        }
        
        if( this.campo_Descripcion.getText().isEmpty() ){ 
            this.etiqueta_Indicador_Descripcion.setVisible( true );
            bandera = false;
        }else{ 
            this.etiqueta_Indicador_Descripcion.setVisible( false );
        }
            
        if( this.campo_Cantidad.getText().isEmpty() ){
            this.etiqueta_Indicador_Cantidad.setVisible( true );
            bandera = false;
        }else{ 
            this.etiqueta_Indicador_Cantidad.setVisible( false );
        }
        
        if( this.campo_Precio_Compra.getText().isEmpty() ){
            this.etiqueta_Indicador_Precio_Compra.setVisible( true );
            bandera = false;
        }else{ 
            this.etiqueta_Indicador_Precio_Compra.setVisible( false );
        }
        
        if( this.campo_Precio_Venta.getText().isEmpty() ){
            this.etiqueta_Indicador_Precio_Venta.setVisible( true );
            bandera = false;
        }else{ 
            this.etiqueta_Indicador_Precio_Venta.setVisible( false );
        }
        
        if( this.combo_Proveedor.getText().isEmpty()){
            this.etiqueta_Indicador_Proveedor.setVisible( true );
            bandera = false;
        }else{ 
            this.etiqueta_Indicador_Proveedor.setVisible( false );
        }
        return bandera;
    }*/
    /*
    public void limpiar_Campos(){
        this.campo_Codigo.setText( "" );
        this.campo_Descripcion.setText( "" );
        this.campo_Cantidad.setText( "" );
        this.campo_Precio_Compra.setText( "" );
        this.campo_Precio_Venta.setText( "" );
        this.combo_Proveedor.setText("");
        this.etiqueta_Correccion_Producto.setVisible(false);
    }*/
    /*
    public void botones( boolean bandera1, boolean bandera2, boolean bandera3, boolean bandera4, boolean bandera5, boolean bandera6  ){
        this.boton_Guardar.setEnabled(bandera1);
        this.boton_Nuevo_Producto.setEnabled(bandera2);
        this.boton_Modificar.setEnabled(bandera3);
        this.boton_Eliminar.setEnabled(bandera4);
        this.campo_Codigo.setEditable(bandera5);
        this.boton_Informe.setEnabled(bandera6);
    }*/
  /*  
    public void setCampos( String codigo, String descripcion, String cantidad_Disponible, String precio_Compra, String precio_Venta, String proveedor){
        this.botones(false, false, true, true, false, true);
        this.campo_Codigo.setText(codigo);
        this.campo_Descripcion.setText(descripcion);
        this.campo_Cantidad.setText(cantidad_Disponible);
        this.campo_Precio_Compra.setText(precio_Compra);
        this.campo_Precio_Venta.setText(precio_Venta);
        this.combo_Proveedor.setText(proveedor);
    }*/
    
    public void habilitar_Rol(boolean[] bandera) {
        this.boton_Nuevo_Producto.setVisible(bandera[0]);
       // this.boton_Guardar.setVisible(bandera[1]);
        this.boton_Modificar.setVisible(bandera[2]);
        this.boton_Eliminar.setVisible(bandera[3]);
        this.boton_Informe.setVisible(bandera[4]);
        this.boton_Devoluciones.setVisible(bandera[5]);
        //this.boton_Buscar.setVisible(bandera[6]);
        //this.boton_Proveedor.setVisible(bandera[7]);
    }
    
    public void set_Usuario(Usuario usuario, String rol){
        this.etiqueta_Nombre_Usuario.setText(usuario.getNombre() + " " + usuario.getApellido());
        this.etiqueta_Rol.setText(rol);
    }
    /*
    public void correccion_Campos(String valor){
        if(valor.equals(this.campo_Codigo.getText())){
            this.etiqueta_Correccion_Producto.setVisible(true);
        }else{
            this.etiqueta_Correccion_Producto.setVisible(false);
        }
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton boton_Cerrar_Sesion;
    public javax.swing.JButton boton_Devoluciones;
    public javax.swing.JButton boton_Eliminar;
    public javax.swing.JButton boton_Informe;
    public javax.swing.JButton boton_Modificar;
    public javax.swing.JButton boton_Nuevo_Producto;
    public javax.swing.JTextField campo_Buscar;
    public javax.swing.JComboBox<String> combo_Opciones;
    public javax.swing.JLabel etiqueta_Nombre_Usuario;
    public javax.swing.JLabel etiqueta_Rol;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    public javax.swing.JTable tabla_Inventario;
    // End of variables declaration//GEN-END:variables
}
