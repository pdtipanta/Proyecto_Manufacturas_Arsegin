package Datos;

import Modelo.Rol;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author LENOVO
 */
public class DAO_Roles{
    private Rol funcion_Rol;
    private Connection conexion;
    
    public DAO_Roles( Connection conexion ){
        this.conexion = conexion;
    }
    
    public DefaultComboBoxModel consultar_Roles(){
        DefaultComboBoxModel modelo_Autocompletar = new DefaultComboBoxModel();
        
        try{
            CallableStatement procedimiento_consultar_Roles = this.conexion.prepareCall( "{CALL consulta_Roles()}" );
            ResultSet consulta = procedimiento_consultar_Roles.executeQuery();
            modelo_Autocompletar.addElement( "Seleccionar....." );
            while(consulta.next() ){
                 modelo_Autocompletar.addElement( consulta.getString( 1 ) ); 
           }
        }catch( SQLException ex ){
        }
        return modelo_Autocompletar;
    }
    
    public Rol consultar_Funcion_Rol(int rol){
        try {
            CallableStatement procedimiento_Iniciar_Sesion = this.conexion.prepareCall("{CALL consulta_Funcion_Rol(?)}");
            procedimiento_Iniciar_Sesion.setInt(1, rol);
            ResultSet consulta = procedimiento_Iniciar_Sesion.executeQuery();

            while (consulta.next()) {
                this.funcion_Rol = new Rol(consulta.getInt(1), consulta.getString(2));
            }
            consulta.close();
        } catch (SQLException e1) {
        }
        return this.funcion_Rol;
    }
}
