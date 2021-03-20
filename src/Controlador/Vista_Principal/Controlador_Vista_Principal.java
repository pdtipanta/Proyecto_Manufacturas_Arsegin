package Controlador.Vista_Principal;

import Controlador.Menu;
import Modelo.Usuario;
import Vista.Vista_Principal;
import java.sql.Connection;

public class Controlador_Vista_Principal{
    private final Vista_Principal         vista;
    private final Usuario                 usuario;
    private final String                  rol;
    private Menu                          menu;
    private final Connection              conexion_Database;
    
    public Controlador_Vista_Principal(Vista_Principal vista, Connection conexion_Database, Usuario usuario, String rol) {
        this.vista = vista;
        this.conexion_Database = conexion_Database;
        this.usuario = usuario;
        this.rol = rol;
    }

    public void iniciar() {
        this.funcionalidades_Usuario();
    }

    public void funcionalidades_Usuario() {
        this.menu = new Menu(this.vista, this.conexion_Database, this.usuario, this.rol);

        switch (this.rol) {
            
            case "Vendedor":
                limpiar_Panel_Contenedor();
                menu.construir_Menu_Cliente();
                menu.construir_Submenu_Cliente();
                menu.construir_Submenu_Estado_Cuenta();
                menu.construir_Submenu_Cotizacion();
                menu.construir_Submenu_Factura();
                menu.construir_Menu_Proveedor();
                menu.construir_Submenu_Proveedor();
                menu.construir_Submenu_Orden_Compra();
                menu.construir_Menu_Inventario();
                menu.construir_Submenu_Inventario();
                menu.construir();
                break;

            case "Bodeguero":
                limpiar_Panel_Contenedor();
                menu.construir_Menu_Inventario();
                menu.construir_Submenu_Inventario();
                menu.construir();
                break;

            case "Maquila":
                limpiar_Panel_Contenedor();
                menu.construir_Menu_Maquila();
                menu.construir_Submenu_Maquila();
                menu.construir_Submenu_Orden_Produccion();
                menu.construir();
                break;

            case "Contador":
                limpiar_Panel_Contenedor();
                menu.construir_Menu_Cliente();
                menu.construir_Submenu_Cliente();
                menu.construir_Submenu_Estado_Cuenta();
                menu.construir_Submenu_Factura();
                menu.construir_Menu_Proveedor();
                menu.construir_Submenu_Proveedor();
                menu.construir_Submenu_Compras();
                menu.construir_Submenu_Pagos();
                menu.construir();
                break;
                
            case "Administrador":
                limpiar_Panel_Contenedor();
                menu.construir_Menu_Cliente();
                menu.construir_Submenu_Cliente();
                menu.construir_Submenu_Estado_Cuenta();
                menu.construir_Submenu_Cotizacion();
                menu.construir_Submenu_Factura();
                
                menu.construir_Menu_Proveedor();
                menu.construir_Submenu_Proveedor();
                menu.construir_Submenu_Compras();
                menu.construir_Submenu_Orden_Compra();
                menu.construir_Submenu_Pagos();
                
                menu.construir_Menu_Inventario();
                menu.construir_Submenu_Inventario();

                menu.construir_Menu_Maquila();
                menu.construir_Submenu_Maquila();
                menu.construir_Submenu_Orden_Produccion();
                menu.construir();
                break;
        }
        this.borrar_Menu();
    }

    public void limpiar_Panel_Contenedor() {
        vista.Panel_Contenedor.removeAll();
    }
    
    public void borrar_Menu(){
        this.vista.set_Menu(this.menu.menu());
    }
}
