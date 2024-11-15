/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Colaborador;
import pojo.Mensaje;

/**
 *
 * @author david
 */
@Path("colaboradores")
public class WSColaboradores {
    
    @GET
    @Path("obtenerColaboradores")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Colaborador> obtenerColaboradores(){
        return null;
    }
    
    @GET
    @Path("obtenerColaboradorNoPersonal/{noPersonal}")
    @Produces(MediaType.APPLICATION_JSON)
    public Colaborador obtenerColaboradorNoPersonal(@PathParam("noPersonal") Integer noPersonal){
        return null;
    }
    
    @POST
    @Path("registrar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje agregarColaborador(String jsonColaborador){
        return null;
    }
    
    @PUT
    @Path("editar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarColaborador(String jsonColaborador){
        return null;
    }
    
    @DELETE
    @Path("obtenerColaboradores")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarColaborador(Integer idColaborador){
        return null;
    }
}
