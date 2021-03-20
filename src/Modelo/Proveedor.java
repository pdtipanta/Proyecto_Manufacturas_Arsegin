package Modelo;

public class Proveedor {
    private String id_Proveedor; 
    private String proveedor;
    private String RUC;
    private String direccion;
    private String Correo;
    private String telefono;
    private String productos;

    public Proveedor( String id_Proveedor, String proveedor, String RUC, String direccion, String correo, String telefono, String productos ){
        this.id_Proveedor = id_Proveedor;
        this.proveedor = proveedor;
        this.RUC = RUC;
        this.direccion = direccion;
        this.Correo = correo;
        this.telefono = telefono;
        this.productos = productos;
    }

    public String getId_Proveedor() {
        return id_Proveedor;
    }

    public void setId_Proveedor(String id_Proveedor) {
        this.id_Proveedor = id_Proveedor;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor( String proveedor ) {
        this.proveedor = proveedor;
    }

    public String getRUC() {
        return RUC;
    }

    public void setRUC( String RUC ) {
        this.RUC = RUC;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion( String direccion ) {
        this.direccion = direccion;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo( String Correo ) {
        this.Correo = Correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono( String telefono ) {
        this.telefono = telefono;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos( String productos ) {
        this.productos = productos;
    }
}
