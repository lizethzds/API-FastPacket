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
    private Integer filasAfectadas;
    private String mensaje;

    public DatosRegistroEnvio() {
    }

    public DatosRegistroEnvio(Envio envio, Direccion direccion, Integer filasAfectadas, String mensaje) {
        this.envio = envio;
        this.direccion = direccion;
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
    
    

   
    
}
