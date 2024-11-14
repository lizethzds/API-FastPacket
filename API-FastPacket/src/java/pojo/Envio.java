
package pojo;

/**
 *
 * @author lizet
 */
public class Envio {
    
    private Integer idEnvio;
    private String numGuia;
    private float costoEnvio;
    private Integer idEstadoEnvio;
    private Integer idCliente;
    private Integer idColaborador;
    private Integer idDireccionDestino;

    public Envio() {
    }

    public Envio(Integer idEnvio, String numGuia, float costoEnvio, Integer idEstadoEnvio, Integer idCliente, Integer idColaborador, Integer idDireccionDestino) {
        this.idEnvio = idEnvio;
        this.numGuia = numGuia;
        this.costoEnvio = costoEnvio;
        this.idEstadoEnvio = idEstadoEnvio;
        this.idCliente = idCliente;
        this.idColaborador = idColaborador;
        this.idDireccionDestino = idDireccionDestino;
    }

    public Integer getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    public String getNumGuia() {
        return numGuia;
    }

    public void setNumGuia(String numGuia) {
        this.numGuia = numGuia;
    }

    public float getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(float costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public Integer getIdEstadoEnvio() {
        return idEstadoEnvio;
    }

    public void setIdEstadoEnvio(Integer idEstadoEnvio) {
        this.idEstadoEnvio = idEstadoEnvio;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public Integer getIdDireccionDestino() {
        return idDireccionDestino;
    }

    public void setIdDireccionDestino(Integer idDireccionDestino) {
        this.idDireccionDestino = idDireccionDestino;
    }
    
    
    
    
    
    
}
