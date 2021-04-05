package Datos;

import Modelo.Sesion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion_Database {

    private String usuario_Sesion;

    private Connection conexion;
    private Statement st;
    private final String driver = "com.mysql.cj.jdbc.Driver";

    private final String usuario;
    private final String contraseña;
    private final String direccion = "jdbc:mysql://localhost:3306/Proyecto_Manufacturas_Arsegin?useTimezone=true&serverTimezone=UTC";

    public Conexion_Database(Sesion sesion) {
        this.usuario = sesion.getUsuario();
        this.contraseña = sesion.getClave();
    }

    public Connection iniciar() {

        try {
            Class.forName(driver);
            this.conexion = (Connection) DriverManager.getConnection(direccion, this.usuario, this.contraseña);
            st = conexion.createStatement();
            st.close();
            conexion.setAutoCommit(false);
            this.usuario_Sesion = this.conexion.getMetaData().getUserName();
        } catch (ClassNotFoundException | SQLException e1) {;
        }
        return this.conexion;
    }

    public Connection obtenerConeccion() {
        return conexion;
    }

    public Connection cerrarConeccion() {
        return conexion = null;
    }

    public String usuario() {
        return this.usuario_Sesion;
    }
}

