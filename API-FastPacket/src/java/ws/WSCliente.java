/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import dominio.ImpCliente;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Cliente;

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
    
}
