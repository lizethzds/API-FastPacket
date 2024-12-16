/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dominio.ImpColaboradores;
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
import pojo.Colaborador;
import pojo.Mensaje;
import pojo.Unidad;

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
        return ImpColaboradores.obtenerColaboradores();
    }
    
    @GET
    @Path("obtenerColaboradorNoPersonal/{noPersonal}")
    @Produces(MediaType.APPLICATION_JSON)
    public Colaborador obtenerColaboradorNoPersonal(@PathParam("noPersonal") String noPersonal){
        return ImpColaboradores.obtenerColaboradorNoPersonal(noPersonal);
    }
    
    @POST
    @Path("registrar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Mensaje agregarColaborador(String jsonColaborador){
        try{
            Gson gson = new Gson();
            Colaborador colaborador = gson.fromJson(jsonColaborador, Colaborador.class);
            return ImpColaboradores.agregarColaborador(colaborador);
        }catch(Exception e){  
            e.printStackTrace();
            throw new BadRequestException();
        }
    }
    
    @PUT
    @Path("modificar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarColaborador(String jsonColaborador){
        try{
            Gson gson = new Gson();
            Colaborador colaborador = gson.fromJson(jsonColaborador, Colaborador.class);
            return ImpColaboradores.editarColaborador(colaborador);
        }catch(Exception e){  
            e.printStackTrace();
            throw new BadRequestException();
        }
    }
    
    @DELETE
    @Path("eliminar/{idColaborador}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarColaborador(@PathParam("idColaborador") Integer idColaborador){
        return ImpColaboradores.eliminarColaborador(idColaborador);
    }
    
    @GET
    @Path("obtenerFotografia/{idColaborador}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje obtenerFotografia(@PathParam("idColaborador") Integer idColaborador){
        return ImpColaboradores.obtenerFotografia(idColaborador);
    }
    
    @POST
    @Path("comprobarValoresRepetidos")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje comprobarValores(String jsonColaborador){
        try{            
            Gson gson = new Gson();
            Colaborador colaboraor = gson.fromJson(jsonColaborador, Colaborador.class);
            return ImpColaboradores.comprobarValoresRepetidos(colaboraor);
        }catch(Exception e){
            e.printStackTrace();
            throw new BadRequestException();
        }
    }
    
    @GET
    @Path("comprobarEnvios/{idColaborador}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje comprobarEnvios(@PathParam("idColaborador") Integer idColaborador){
        return ImpColaboradores.comprobarEnvios(idColaborador);
    }
    
    @GET
    @Path("asignarUnidad/{idColaborador}/{idUnidad}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje asignarUnidad(@PathParam("idColaborador") Integer idColaborador, @PathParam("idUnidad") Integer idUnidad){
        if(idColaborador > 0 || idUnidad > 0){
            return ImpColaboradores.asignarUnidad(idColaborador, idUnidad);
        }else{
            throw new BadRequestException();
        }
    }
    
    @GET
    @Path("comprobarColaboradorUnidad/{idColaborador}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje comprobarColaboradorUnidad(@PathParam("idColaborador") Integer idColaborador){
        if(idColaborador > 0){
            return ImpColaboradores.comprobarColaboradorUnidad(idColaborador);
        }else{
            throw new BadRequestException();
        }
    }
    
    @DELETE
    @Path("quitarAsignacionUnidad/{idColaborador}")
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje quitarAsignacionUnidad(@PathParam("idColaborador") Integer idColaborador){
        if(idColaborador > 0){
            return ImpColaboradores.quitarAsignacionUnidad(idColaborador);
        }else{
            throw new BadRequestException();
        }
    }
    
    @GET
    @Path("obtenerUnidad/{idColaborador}")
    @Produces(MediaType.APPLICATION_JSON)
    public Unidad obtenerUnidad(@PathParam("idColaborador") Integer idColaborador){
        if(idColaborador > 0){
            return ImpColaboradores.obtenerUnidad(idColaborador);
        }else{
            throw new BadRequestException();
        }
    }
}
