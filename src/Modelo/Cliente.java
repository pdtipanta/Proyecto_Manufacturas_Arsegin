package Modelo;

public class Cliente {
    private String codigo_Cliente;
    private String cliente;
    private String direccion;
    private String ciudad;
    private String telefono;
    private String celular;
    private String RUC;
    private String correo;
    private String persona_Contacto;
    private String empleado;
    
    public Cliente( String codigo_Cliente, String cliente, String direccion, String ciudad, String telefono, String celular, String RUC, String correo, String persona_Contacto, String empleado ){
        this.codigo_Cliente = codigo_Cliente;
        this.cliente = cliente;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.celular = celular;
        this.RUC = RUC;
        this.correo = correo;
        this.persona_Contacto = persona_Contacto;
        this.empleado = empleado;
    }


    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getCodigo_Cliente() {
        return codigo_Cliente;
    }

    public void setCodigo_Cliente(String codigo_Cliente) {
        this.codigo_Cliente = codigo_Cliente;
    }
    
    public String getCliente() {
        return cliente;
    }

    public void setCliente( String cliente ) {
        this.cliente = cliente;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad( String ciudad ) {
        this.ciudad = ciudad;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono( String telefono ) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo( String correo ) {
        this.correo = correo;
    }

    public String getPersona_Contacto() {
        return persona_Contacto;
    }

    public void setPersona_Contacto( String persona_Contacto ) {
        this.persona_Contacto = persona_Contacto;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
