/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Mensaje;
import pojo.TipoUnidad;
import pojo.Unidad;

/**
 *
 * @author uriel
 */
public class ImpUnidad {

    public static List<Unidad> obtenerUnidades() {
        List<Unidad> unidades = null;
        SqlSession conexion = MyBatisUtil.getSession();
        unidades = conexion.selectList("unidades.obtenerUnidades");
        return unidades;
    }
    
    public static List<Unidad> historialUnidades() {
        List<Unidad> unidades = null;
        SqlSession conexion = MyBatisUtil.getSession();
        unidades = conexion.selectList("unidades.historialUnidades");
        return unidades;
    }
    
     public static List<TipoUnidad> obtenerTipoUnidad() {
        List<TipoUnidad> tipoUnidad = null;
        SqlSession conexion = MyBatisUtil.getSession();
        tipoUnidad = conexion.selectList("unidades.tipoUnidad");
        return tipoUnidad;
    }

    public static Mensaje registrarUnidad(Unidad unidad) {
        Mensaje respuesta = new Mensaje();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.insert("unidades.registrar", unidad);
                conexionBD.commit();
                if (filasAfectadas == 1) {
                    respuesta.setError(false);
                    respuesta.setContenido("Unidad registrada correctamente");
                } else {
                    respuesta.setContenido("No se pudo realizar el registro de la Unidad");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setContenido("Por el momento no hay conexión con la base de datos.");
        }
        return respuesta;
    }

    public static Mensaje editarUnidad(Unidad unidad) {
        Mensaje respuesta = new Mensaje();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                int filasAfectadas = conexionBD.update("unidades.editar", unidad);
                conexionBD.commit();
                if (filasAfectadas == 1) {
                    respuesta.setError(false);
                    respuesta.setContenido("Unidad editada correctamente");
                } else {
                    respuesta.setContenido("No se pudo editar la Unidad");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setContenido("Por el momento no hay conexión con la base de datos.");
        }
        return respuesta;
    }
    
    public static Mensaje eliminarUnidad(Integer idUnidad, String motivo){
        Mensaje respuesta = new Mensaje();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            try {
                HashMap<String, Object> data = new LinkedHashMap<>();
                data.put("idUnidad", idUnidad);
                data.put("motivo", motivo);
                int filasAfectadas = conexionBD.delete("unidades.eliminar", data);
                conexionBD.commit();
                if (filasAfectadas == 1) {
                    respuesta.setError(false);
                    respuesta.setContenido("Unidad eliminada correctamente");
                } else {
                    respuesta.setContenido("No se pudo eliminar la Unidad");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conexionBD.close();
            }
        } else {
            respuesta.setContenido("Por el momento no hay conexión con la base de datos.");
        }
        return respuesta;
    }
}
