
package dominio;

import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.HistorialEnvio;
import pojo.Mensaje;

/**
 *
 * @author lizet
 */
public class ImpHistorial {
    
    
    public static Mensaje guardarRegistroCambios(HistorialEnvio historialEnvio){
        Mensaje msj = new Mensaje();
        msj.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        
        if(conexionBD != null){
            try{
                int filasAfectadas = conexionBD.insert("historial.guardarCambioEstatus", historialEnvio);
                conexionBD.commit();
                if(filasAfectadas>0){
                    msj.setError(false);
                    msj.setContenido("Se ha guardado el cambio en el historial");
                }else{
                msj.setContenido("Ocurrió un error al intentar guardar el registro de historial");
                }
            
            }catch(Exception e){
                msj.setContenido("Hubo un error al guardar el registro de cambio");
                e.printStackTrace();
            }
        
        }else{
           msj.setContenido("No hay conexión con la base de datos.");
        }
        
        return msj;
    
    }
    
}
