
package pojo;

/**
 *
 * @author lizet
 */
public class Paquete {
   private Integer idPaquete;
   private String descripcion;
   private float peso;
   private Integer ancho;
   private Integer altura;
   private Integer profundidad;
   private Integer idEnvio;
   

    public Paquete() {
    }

    public Paquete(Integer idPaquete, String descripcion, float peso, Integer ancho, Integer altura, Integer profundidad, Integer idEnvio) {
        this.idPaquete = idPaquete;
        this.descripcion = descripcion;
        this.peso = peso;
        this.ancho = ancho;
        this.altura = altura;
        this.profundidad = profundidad;
        this.idEnvio = idEnvio;
    }

    public Integer getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(Integer idEnvio) {
        this.idEnvio = idEnvio;
    }

   

    public Integer getIdPaquete() {
        return idPaquete;
    }

    public void setIdPaquete(Integer idPaquete) {
        this.idPaquete = idPaquete;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public Integer getAncho() {
        return ancho;
    }

    public void setAncho(Integer ancho) {
        this.ancho = ancho;
    }

    public Integer getAltura() {
        return altura;
    }

    public void setAltura(Integer altura) {
        this.altura = altura;
    }

    public Integer getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(Integer profundidad) {
        this.profundidad = profundidad;
    }

   
   
    
   
    
}
