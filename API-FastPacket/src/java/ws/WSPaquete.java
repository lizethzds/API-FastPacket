
package ws;

import com.google.gson.Gson;
import dominio.ImpPaquete;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import pojo.Mensaje;
import pojo.Paquete;

/**
 *
 * @author lizet
 */


@Path("paquete")
public class WSPaquete {
    
    public WSPaquete(){}
    
    
    @GET
    @Path("obtenerPaquetesEnvio/{idEnvio}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Paquete> obtenerPaquetesEnvio (@PathParam("idEnvio") Integer idEnvio){
        
        return ImpPaquete.obtenerPaquetesEnvio(idEnvio);
    }
    
    
    @POST
    @Path("registrar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje registrarPaquete(String json){
        Mensaje msj = new Mensaje();
        
        if(json.isEmpty()){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        
        }else{
            Gson gson = new Gson();
            Paquete paquete = gson.fromJson(json, Paquete.class);
            
            if(paquete != null){
                return ImpPaquete.registrarPaquete(paquete);
            }
        
        }
        return msj;
    
    }
    
    @PUT
    @Path("editar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
   
    public Mensaje editarPaquete(String json){
        Mensaje msj = new Mensaje();
        
        if(json.isEmpty()){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }else{
            Gson gson = new Gson();
            Paquete paquete = gson.fromJson(json, Paquete.class);
            
            if(paquete != null){
                return ImpPaquete.editarPaquete(paquete);
            }
                    
        }
        return msj;
    }   
    
    
    @DELETE
    @Path("eliminar/{idPaquete}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarPaquete(@PathParam("idPaquete") Integer idPaquete){
        
        if(idPaquete>0){
            return ImpPaquete.eliminarPaquete(idPaquete);
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
    
    
    
    
    
    
    
    
}
