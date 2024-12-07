/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import dominio.ImpCatalogo;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import pojo.Estado;
import pojo.Municipio;
import pojo.Rol;

/**
 *
 * @author lizet
 */


@Path("catalogo")
public class WSCatalogo {
    
    public WSCatalogo(){}
    
    
    @Path("obtenerEstados")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estado> cargarEstados(){
        return ImpCatalogo.obtenerEstados();
    }
    
    
    @Path("obtenerMunicipiosEstado/{idEstado}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Municipio> cargarMunicipios(@PathParam("idEstado") Integer idEstado){
        return ImpCatalogo.obtenerMunicipiosEstado(idEstado);
    }
    
    @Path("obtenerEstadoMunicipio/{idMunicipio}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Estado obtenerEstadoMunicipio(@PathParam("idMunicipio") Integer idMunicipio){
        return ImpCatalogo.obtenerEstadoPorMunicipio(idMunicipio);
    }
    
    @Path("obtenerRoles")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Rol> obtenerRoles(){
        return ImpCatalogo.obtenerRoles();
    }
    
    
    
}
