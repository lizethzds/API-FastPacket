
package ws;

import com.google.gson.Gson;
import dominio.ImpEnvio;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.DatosRegistroEnvio;
import pojo.Direccion;
import pojo.Envio;
import pojo.Mensaje;

/**
 *
 * @author lizet
 */

@Path("envio")
public class WSEnvio {
    
    public WSEnvio(){
        
    }
    
    @Path("obtenerEnvios")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Envio> obtenerEnvios(){
        
    return ImpEnvio.obtenerEnvios();
    
    }
    
    
    @Path("obtenerEnvioPorId/{idEnvio}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DatosRegistroEnvio obtenerEnvioPorId(@PathParam("idEnvio") Integer idEnvio){
        if(idEnvio > 0){
            return ImpEnvio.obtenerEnvioPorId(idEnvio);
        }else{

            throw new BadRequestException();
        }
    }
    
    @Path("obtenerEnvioPorNoGuia/{noGuia}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Envio obtenerEnvioPorNumGuia(@PathParam("noGuia") String noGuia){
        if(noGuia.contains("FP")){
            return ImpEnvio.obtenerEnvioPorNumGuia(noGuia);
        }else{
          throw new BadRequestException();
        }
    }
    
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje registrarEnvio(String json){
        Mensaje msj = new Mensaje();
        
        if(json.isEmpty()){
            throw new BadRequestException();
        }else{
            Gson gson = new Gson();
            DatosRegistroEnvio datosEnvio = gson.fromJson(json, DatosRegistroEnvio.class);
            
            Envio envio = datosEnvio.getEnvio();
            Direccion direccion = datosEnvio.getDireccion();
            
            if(envio != null && direccion != null){
                return ImpEnvio.registrarEnvio(datosEnvio);
            }
            
        }
        
        return msj;
        
    }
    
    
    @Path("editar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje editarEnvio(String json){
        Mensaje msj = new Mensaje();
        
        if(json.isEmpty()){
            throw new BadRequestException();
        }else{
            Gson gson = new Gson();
            DatosRegistroEnvio datosEnvio = gson.fromJson(json, DatosRegistroEnvio.class);
            
            Envio envio = datosEnvio.getEnvio();
            Direccion direccion = datosEnvio.getDireccion();
            
            if(envio != null && direccion != null){
                return ImpEnvio.editarEnvio(datosEnvio);
            }
            
        }
        
        return msj;
        
    }
    
    
    @Path("editarEstatus")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarEstatusEnvio(String json){
        Mensaje msj = new Mensaje();
        if(json.isEmpty()){
            throw new BadRequestException();
        }else{
        Gson gson = new Gson();
        Envio envio = gson.fromJson(json, Envio.class);
        
        msj =  ImpEnvio.editarEstatusEnvio(envio);
       
        }
        return msj;
    }
    
    
    
   @DELETE
   @Path("eliminar/{idEnvio}")
   @Produces(MediaType.APPLICATION_JSON)
   public Mensaje eliminarEnvio (@PathParam("idEnvio") Integer idEnvio){
   
       if(idEnvio>0){
           return ImpEnvio.eliminarEnvio(idEnvio);
       }else{
           throw new BadRequestException();
       }
       
   }
   
   @Path("enviosPorConductor/{idColaborador}")
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   public List<Envio> enviosPorCondutor (@PathParam("idColaborador") Integer idColaborador){
       if (idColaborador > 0) {
           return ImpEnvio.obtenerEnviosPorConductor(idColaborador);
       }else{
           throw new BadRequestException();
       }
   }
    
}
