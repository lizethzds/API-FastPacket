
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
     
     
  public static DatosRegistroCliente obtenerClienteId(Integer idCliente) {
    DatosRegistroCliente clienteSolicitado = new DatosRegistroCliente();
    SqlSession conexionBD = MyBatisUtil.getSession();
    try {
        clienteSolicitado = conexionBD.selectOne("clientes.obtenerDatosCliente", idCliente);
        System.out.println(clienteSolicitado.getCliente().getNombre());
    } catch (Exception e) {
        System.err.println("Error al obtener el cliente: " + e.getMessage());
        e.printStackTrace();
    } finally {
        if (conexionBD != null) {
            conexionBD.close();
        }
    }
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
    
  public static Mensaje editarCliente(DatosRegistroCliente edicionCliente){
      Mensaje msj = new Mensaje();
       msj.setError(true);
       SqlSession conexionBD = MyBatisUtil.getSession();
       
       if(conexionBD !=null){
           try{
               conexionBD.insert("clientes.editar", edicionCliente);
               conexionBD.commit();
               
               if(edicionCliente.getFilasAfectadas()>0 && edicionCliente.getError().isEmpty()){
                   msj.setError(false);
                   msj.setContenido("Los datos del cliente se han actualizado correctamente");
               }else{
                   msj.setContenido(edicionCliente.getError());
                   
               }
           }catch(Exception e){
               msj.setContenido("Por el momento no se pudo actualizar el cliente "+edicionCliente.getCliente().getNombre()+ ",intente después.");
               e.printStackTrace();
           
           }finally{
               conexionBD.close();
           }
       }else{
           msj.setContenido("Por el momento no hay conexión con la base de datos.");
       }  
       return msj;   
  }
  
  
  public static Mensaje eliminarCliente (Integer idCliente){
    Mensaje msj = new Mensaje();
    msj.setError(true);
    SqlSession conexionBD = MyBatisUtil.getSession();
    if(conexionBD!=null){
        try{
           int filasAfectadas = conexionBD.delete("clientes.eliminar", idCliente);
            conexionBD.commit();
            
            if(filasAfectadas>0){
                msj.setError(false);
                msj.setContenido("El cliente se ha eliminado correctamente del sistema.");
            }else{
                msj.setContenido("Hubo un error al intentar borrar el registro.");
            }
            
        }catch(Exception e){
            e.printStackTrace();
        
        }
    }else{
        msj.setContenido("No se logró establecer conexión con la base de datos.");
    }
    
    return msj;
  }
   
    
    
    
}
