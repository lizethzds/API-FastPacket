package pojo;

public class Rol {
    private Integer idRol;
    private String nombre;

    public Rol() {
    }

    public Rol(Integer idRol, String nombre) {
        this.idRol = idRol;
        this.nombre = nombre;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getRol() {
        return nombre;
    }

    public void setRol(String rol) {
        this.nombre = rol;
    }
    
    
}
