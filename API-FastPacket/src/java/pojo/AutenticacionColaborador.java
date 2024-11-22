
package pojo;

/**
 *
 * @author lizet
 */
public class AutenticacionColaborador {
    
    private boolean error;
    private String mensaje;
    private Colaborador colaboradorSesion;

    public AutenticacionColaborador() {
    }

    public AutenticacionColaborador(boolean error, String mensaje, Colaborador colaboradorSesion) {
        this.error = error;
        this.mensaje = mensaje;
        this.colaboradorSesion = colaboradorSesion;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Colaborador getColaboradorSesion() {
        return colaboradorSesion;
    }

    public void setColaboradorSesion(Colaborador colaboradorSesion) {
        this.colaboradorSesion = colaboradorSesion;
    }
    
    
    
}
