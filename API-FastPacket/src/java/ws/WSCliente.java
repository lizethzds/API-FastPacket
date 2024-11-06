/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dominio.ImpCliente;
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
import pojo.Cliente;
import pojo.DatosRegistroCliente;
import pojo.Direccion;
import pojo.Mensaje;

/**
 *
 * @author lizet
 */

@Path("cliente")
public class WSCliente {
    
    
    public WSCliente(){
        
    }
    
    
    @Path("obtenerLista")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> obtenerListaClientes(){
        return ImpCliente.obtenerClientes();
    }
    
    @Path("obtenerClientePorId/{idCliente}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public DatosRegistroCliente obtenerClienteId(@PathParam("idCliente") Integer idCliente){
        if(idCliente > 0 && idCliente != null){
         
           return ImpCliente.obtenerClienteId(idCliente);
           
        }else{
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
    
    
   @POST
   @Path("registrar")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Mensaje registrarCliente(String json){
       Mensaje mensaje = new Mensaje();
       
       if(json.isEmpty()){
           throw new WebApplicationException(Response.Status.BAD_REQUEST);
       }else{
           Gson gson = new Gson();
           DatosRegistroCliente datosCliente = gson.fromJson(json, DatosRegistroCliente.class);
           
           Cliente cliente = datosCliente.getCliente();
           Direccion direccion = datosCliente.getDireccion();
           
           if(cliente != null && direccion != null){
               return ImpCliente.registrarCliente(datosCliente);
               
           }
       }
       return mensaje;
   }
   
   @PUT
   @Path("editar")
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   public Mensaje editarCliente(String json){
       Mensaje mensaje = new Mensaje();
       
       if(json.isEmpty()){
           throw new WebApplicationException(Response.Status.BAD_REQUEST);
       }else{
           Gson gson = new Gson();
           DatosRegistroCliente datosCliente = gson.fromJson(json, DatosRegistroCliente.class);
           
           Cliente cliente = datosCliente.getCliente();
           Direccion direccion = datosCliente.getDireccion();
           
           if(cliente != null && direccion != null){
               return ImpCliente.editarCliente(datosCliente);
               
           }
       }
       
       return mensaje;
       
   }
   
   
   
   @DELETE
   @Path("eliminar/{idCliente}")
   @Produces(MediaType.APPLICATION_JSON)
   public Mensaje eliminarCliente (@PathParam("idCliente") Integer idCliente){
   
       if(idCliente>0){
           return ImpCliente.eliminarCliente(idCliente);
       }else{
           throw new WebApplicationException(Response.Status.BAD_REQUEST);
       }
       
   }
   
    
}
