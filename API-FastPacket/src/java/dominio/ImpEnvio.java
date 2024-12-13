/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.DatosRegistroEnvio;
import pojo.Envio;
import pojo.Mensaje;

/**
 *
 * @author lizet
 */
public class ImpEnvio {
    
    
    
    public static List<Envio> obtenerEnvios(){
        List<Envio> envios;
        SqlSession conexionBD = MyBatisUtil.getSession();
        envios = conexionBD.selectList("envios.obtenerEnvios");
        return envios;
    }
    
    
    public static DatosRegistroEnvio obtenerEnvioPorId(Integer idEnvio){
        DatosRegistroEnvio envioSolicitado = new DatosRegistroEnvio();
        SqlSession conexionBD = MyBatisUtil.getSession();
        
        try{
            envioSolicitado = conexionBD.selectOne("envios.obtenerDatosEnvio", idEnvio);
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return envioSolicitado;
    
    }
    
    
    
    public static Mensaje registrarEnvio(DatosRegistroEnvio datosEnvio){
       Mensaje msj = new Mensaje();
       msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        
        if(conexionBD != null){
            try{
                conexionBD.insert("envios.registrarEnvio", datosEnvio);
                conexionBD.commit();
                
                if(datosEnvio.getFilasAfectadas() >0){
                    msj.setError(false);
                    msj.setContenido(datosEnvio.getMensaje());
                }else{
                    msj.setContenido(datosEnvio.getMensaje());
                }
            }catch(Exception e){
                msj.setContenido("Ocurrió un error al realizar el registro.");
                e.printStackTrace();
                
            }
        }else{
            msj.setContenido("No hay conexion con la BD");
        }
               
    return msj;   
        
    }
    
    
    public static Mensaje editarEnvio(DatosRegistroEnvio datosEnvio){
       Mensaje msj = new Mensaje();
       msj.setError(true);
       SqlSession conexionBD = MyBatisUtil.getSession();
        
        if(conexionBD != null){
            try{
                conexionBD.insert("envios.editarEnvio", datosEnvio);
                conexionBD.commit();
                
                if(datosEnvio.getFilasAfectadas() >0){
                    msj.setError(false);
                    msj.setContenido(datosEnvio.getMensaje());
                }else{
                    msj.setContenido(datosEnvio.getMensaje());
                }
            }catch(Exception e){
                msj.setContenido("Ocurrió un error al realizar el la acutalización.");
                e.printStackTrace();
                
            }
        }else{
            msj.setContenido("No hay conexion con la BD");
        }
               
    return msj;   
        
    }
    
    
    public static Mensaje eliminarEnvio (Integer idEnvio){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        
        if(conexionBD != null){
            int filasAfectadas = conexionBD.update("envios.eliminarEnvio", idEnvio);
            conexionBD.commit();
            if(filasAfectadas>0){
                msj.setError(false);
                msj.setContenido("El envío se ha eliminado correctamente del sistema.");
            }else{
                msj.setContenido("Hubo un error al intentar borrar el envío.");
            }
        
        
        }else{
        
            msj.setContenido("No se logro la conexion con la base de datos.");
        }
        
        return msj;
    
    }
    
    public static List<Envio> obtenerEnviosPorConductor(int idColaborador){
        List<Envio> envios = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if (conexionBD != null) {
            envios = conexionBD.selectList("envios.enviosPorConductor", idColaborador);
        }
        return  envios;
    }
    
}
