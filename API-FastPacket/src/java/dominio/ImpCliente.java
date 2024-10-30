
package dominio;

import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Cliente;

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
    
}
