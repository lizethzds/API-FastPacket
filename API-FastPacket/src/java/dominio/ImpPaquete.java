
package dominio;

import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Mensaje;
import pojo.Paquete;

/**
 *
 * @author lizet
 */
public class ImpPaquete {
    
    
    public static List<Paquete> obtenerPaquetesEnvio(Integer idEnvio){
        
        List<Paquete> paquetes;
        SqlSession conexionBD = MyBatisUtil.getSession();
        paquetes = conexionBD.selectList("paquetes.obtenerPaquetesEnvio", idEnvio);
        
        return paquetes;
        
    }
    
    
    public static Mensaje registrarPaquete(Paquete paquete){
    
       Mensaje msj = new Mensaje();
       msj.setError(true);
       SqlSession conexionBD = MyBatisUtil.getSession();
       
       if(conexionBD != null){
           try{
              int filasAfectadas =  conexionBD.insert("paquetes.registrar", paquete);
               conexionBD.commit();
               
               if(filasAfectadas>0){
                   msj.setError(false);
                   msj.setContenido("Registro de paquete exitoso");
               }else{
                   msj.setContenido("Hubo un error, intente de nuevo");
               }
           
           }catch(Exception e){
               e.printStackTrace();
           }
       }else{
           msj.setContenido("No se logró establecer conexión con la base de datos.");
       }
       
       return msj;
    }
    
    public static Mensaje editarPaquete(Paquete paquete){
    
       Mensaje msj = new Mensaje();
       msj.setError(true);
       SqlSession conexionBD = MyBatisUtil.getSession();
       
       if(conexionBD != null){
           try{
              int filasAfectadas =  conexionBD.insert("paquetes.editar", paquete);
               conexionBD.commit();
               
               if(filasAfectadas>0){
                   msj.setError(false);
                   msj.setContenido("Actualización de datos de paquete exitoso");
               }else{
                   msj.setContenido("Hubo un error, intente de nuevo");
               }
           
           }catch(Exception e){
               e.printStackTrace();
           }
       }else{
           msj.setContenido("No se logró establecer conexión con la base de datos.");
       }
       
       return msj;
    }
    
    
    
    public static Mensaje eliminarPaquete(Integer idPaquete){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        
        if(conexionBD != null){
            try{
                int filasAfectadas = conexionBD.delete("paquetes.eliminar", idPaquete);
                conexionBD.commit();
                if(filasAfectadas>0){
                    msj.setError(false);
                    msj.setContenido("El paquete ha sido eliminado correctamente del sistema. ");
                }else{
                    msj.setContenido("Ocurrió un error al intentar eliminar el registro.");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }else{
            msj.setContenido("No se logró establecer conexión con la base de datos");
        }
        return msj;
    }
    
    
}
