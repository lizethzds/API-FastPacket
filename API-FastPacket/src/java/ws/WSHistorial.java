
package ws;

import com.google.gson.Gson;
import dominio.ImpHistorial;
import java.util.List;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.HistorialEnvio;
import pojo.Mensaje;

/**
 *
 * @author lizet
 */

@Path("historial")
public class WSHistorial {
    
    public WSHistorial(){}
    
    @POST
    @Path("registrarCambioHistorial")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarCambio(String json){
        Mensaje msj = new Mensaje();
        
        if(json.isEmpty()){
            throw new BadRequestException();
            
        }else{
            Gson gson = new Gson();
            HistorialEnvio historialEnvio = gson.fromJson(json, HistorialEnvio.class);
            
            if(historialEnvio != null){
                return ImpHistorial.guardarRegistroCambios(historialEnvio);
            }
        }
        
        return msj;
    }
    
    @GET
    @Path("obtenerHistorialEnvio/{idEnvio}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<HistorialEnvio> obtenerHistorialEnvio(@PathParam("idEnvio") Integer idEnvio){
        return ImpHistorial.obtenerHistorialIdEnvio(idEnvio);
    }
    
}
