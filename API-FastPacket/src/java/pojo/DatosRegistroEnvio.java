/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author lizet
 */
public class DatosRegistroEnvio {
    
    private Envio envio;
    private Direccion direccion;
    private String conductor;
    private String cliente;
    private Direccion direccionCliente;
    private String estadoEnvio;
    private String lugarOrigen;
    private String lugarDestino;
    private String correoCliente;
    private String telefonoCliente;
    private Integer filasAfectadas;
    private String mensaje;

    public DatosRegistroEnvio() {
    }

    public DatosRegistroEnvio(Envio envio, Direccion direccion, String conductor, String cliente, Direccion direccionCliente, String estadoEnvio, String lugarOrigen, String lugarDestino, String correoCliente, String telefonoCliente, Integer filasAfectadas, String mensaje) {
        this.envio = envio;
        this.direccion = direccion;
        this.conductor = conductor;
        this.cliente = cliente;
        this.direccionCliente = direccionCliente;
        this.estadoEnvio = estadoEnvio;
        this.lugarOrigen = lugarOrigen;
        this.lugarDestino = lugarDestino;
        this.correoCliente = correoCliente;
        this.telefonoCliente = telefonoCliente;
        this.filasAfectadas = filasAfectadas;
        this.mensaje = mensaje;
    }

   

    


    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Integer getFilasAfectadas() {
        return filasAfectadas;
    }

    public void setFilasAfectadas(Integer filasAfectadas) {
        this.filasAfectadas = filasAfectadas;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Direccion getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(Direccion direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public String getLugarOrigen() {
        return lugarOrigen;
    }

    public void setLugarOrigen(String lugarOrigen) {
        this.lugarOrigen = lugarOrigen;
    }

    public String getLugarDestino() {
        return lugarDestino;
    }

    public void setLugarDestino(String lugarDestino) {
        this.lugarDestino = lugarDestino;
    }

    public String getCorreoCliente() {
        return correoCliente;
    }

    public void setCorreoCliente(String correoCliente) {
        this.correoCliente = correoCliente;
    }

    public String getTelefonoCliente() {
        return telefonoCliente;
    }

    public void setTelefonoCliente(String telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }
    
    

    

   
    
    

   
    
}
