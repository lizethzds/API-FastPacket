
package pojo;

/**
 *
 * @author lizet
 */
public class HistorialEnvio {
    
    private Integer idHistorial;
    private Integer idColaborador;
    private Integer idEnvio;
    private Integer idEstadoEnvio;
    private String comentario;
    private String horaModificacion;
    private String fechaModificacion;
    private String estadoEnvio;
    private String colaborador;
    private String estatus;

    public HistorialEnvio() {
    }

    public HistorialEnvio(Integer idHistorial, Integer idColaborador, Integer idEnvio, Integer idEstadoEnvio, String comentario, 
            String horaModificacion, String fechaModificacion, String estadoEnvio, String colaborador, String estatus) {
        this.idHistorial = idHistorial;
        this.idColaborador = idColaborador;
        this.idEnvio = idEnvio;
        this.idEstadoEnvio = idEstadoEnvio;
        this.comentario = comentario;
        this.horaModificacion = horaModificacion;
        this.fechaModificacion = fechaModificacion;
        this.estadoEnvio = estadoEnvio;
        this.colaborador = colaborador;
        this.estatus = estatus;
    }

    

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Integer getIdColaborador() {
        return idColaborador;
    }

    public void setIdColaborador(Integer idColaborador) {
        this.idColaborador = idColaborador;
    }

    public Integer getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

    public Integer getIdEstadoEnvio() {
        return idEstadoEnvio;
    }

    public void setIdEstadoEnvio(Integer idEstadoEnvio) {
        this.idEstadoEnvio = idEstadoEnvio;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getHoraModificacion() {
        return horaModificacion;
    }

    public void setHoraModificacion(String horaModificacion) {
        this.horaModificacion = horaModificacion;
    }

    public String getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(String fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public String getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(String estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public String getColaborador() {
        return colaborador;
    }

    public void setColaborador(String colaborador) {
        this.colaborador = colaborador;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
    
    
    
    
}
