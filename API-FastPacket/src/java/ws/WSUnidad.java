/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import com.google.gson.Gson;
import dominio.ImpUnidad;
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
import pojo.Unidad;

/**
 *
 * @author uriel
 */
@Path("unidad")
public class WSUnidad {

    @Path("listaUnidades")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Unidad> obtenerUnidades() {
        return ImpUnidad.obtenerUnidades();
    }

    @Path("registrar")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje registrarUnidad(String json) {
        if (json.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        } else {
            Gson gson = new Gson();
            Unidad unidad = gson.fromJson(json, Unidad.class);
            if (unidad != null) {
                return ImpUnidad.registrarUnidad(unidad);
            } else {
                return new Mensaje(true, "No se pudo realizar el registro correctamente");
            }
        }
    }

    @Path("editar")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje editarUnidad(String json) {
        if (json.isEmpty()) {
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        } else {
            Gson gson = new Gson();
            Unidad unidad = gson.fromJson(json, Unidad.class);
            if (unidad.getIdUnidad() != null) {
                return ImpUnidad.editarUnidad(unidad);
            } else {
                return new Mensaje(true, "ID de la unidad requerido");
            }
        }
    }

    @Path("eliminar/{idUnidad}")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Mensaje eliminarUnidad(@PathParam("idUnidad") Integer idUnidad) {
        if (idUnidad != null) {
            return ImpUnidad.eliminarUnidad(idUnidad);
        }
        throw new WebApplicationException(Response.Status.BAD_REQUEST);
    }
}
