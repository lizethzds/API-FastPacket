
package dominio;

import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Cliente;
import pojo.DatosRegistroCliente;
import pojo.Mensaje;

/**
 *
 * @author lizet
 */
public class ImpCliente {
    
     public static List<Cliente> obtenerClientes(){ 
        List<Cliente> clientes;
        SqlSession conexionBD = MyBatisUtil.getSession();
        clientes = conexionBD.selectList("clientes.obtenerLista");
        
        return clientes;
    }
     
     
    public static DatosRegistroCliente obtenerClientePorId(Integer id)
    {
        DatosRegistroCliente clienteSolicitado;
        SqlSession conexionBD = MyBatisUtil.getSession();
        clienteSolicitado = conexionBD.selectOne("clientes.obtenerClientePorId",  id);
     
        return clienteSolicitado;
        
    }
    
    
    public static Mensaje registrarCliente(DatosRegistroCliente registroCliente){
       Mensaje msj = new Mensaje();
       msj.setError(true);
       SqlSession conexionBD = MyBatisUtil.getSession();
       
       if(conexionBD !=null){
           try{
               conexionBD.insert("clientes.registrar", registroCliente);
               conexionBD.commit();
               
               if(registroCliente.getFilasAfectadas()>0 && registroCliente.getError().isEmpty()){
                   msj.setError(false);
                   msj.setContenido("Registro exitoso");
               }else{
                   msj.setContenido(registroCliente.getError());
                   
               }
           }catch(Exception e){
               msj.setContenido("Por el momento no se pudo realizar el registro, intente después.");
               e.printStackTrace();
           
           }finally{
               conexionBD.close();
           }
       }else{
           msj.setContenido("Por el momento no hay conexión con la base de datos.");
       }
       
       return msj;
       
   }
   
    
    
    
}
