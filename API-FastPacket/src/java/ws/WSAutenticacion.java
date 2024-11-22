
package ws;

import dominio.ImpAutenticacion;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pojo.AutenticacionColaborador;

/**
 *
 * @author lizet
 */


@Path("autenticacion")
public class WSAutenticacion {
    
    
    public WSAutenticacion(){}
    
    
    @Path("loginEscritorio")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public AutenticacionColaborador inicioSesionEscritorio (@FormParam("noPersonal") String noPersonal,
            @FormParam("password") String password){
        AutenticacionColaborador respuesta = new AutenticacionColaborador();
        
       if(noPersonal != null && !noPersonal.isEmpty() && 
                        (password != null && !password.isEmpty()) && noPersonal.length()<=10){
                    
                    respuesta = ImpAutenticacion.validarSesionEscritorio(noPersonal, password);
                }else{
                    throw new WebApplicationException(Response.Status.BAD_REQUEST);
                }
                
                return respuesta;
         }
    
    
    
    @Path("loginMovil")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public AutenticacionColaborador inicioSesionMovil (@FormParam("noPersonal") String noPersonal,
            @FormParam("password") String password){
        AutenticacionColaborador respuesta = new AutenticacionColaborador();
        
       if(noPersonal != null && !noPersonal.isEmpty() && 
                        (password != null && !password.isEmpty()) && noPersonal.length()<=10){
                    
                    respuesta = ImpAutenticacion.validarSesionMovil(noPersonal, password);
                }else{
                    throw new WebApplicationException(Response.Status.BAD_REQUEST);
                }
                
                return respuesta;
         }
    
    
    
    
}
