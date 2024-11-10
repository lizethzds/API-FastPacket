
package dominio;

import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Estado;
import pojo.Municipio;

/**
 *
 * @author lizet
 */
public class ImpCatalogo {
    
    public static List<Estado> obtenerEstados(){
        List<Estado> estados;
        SqlSession conexionBD = MyBatisUtil.getSession();
        estados = conexionBD.selectList("catalogo.obtenerEstados"); 
        return estados;
    }
    
    
    public static List<Municipio> obtenerMunicipiosEstado(Integer idEstado){
        List<Municipio> municipios;
        SqlSession conexionBD = MyBatisUtil.getSession();
        municipios = conexionBD.selectList("catalogo.obtenerMunicipiosEstado", idEstado);
        return municipios;
    }
    
    public static Estado obtenerEstadoPorMunicipio(Integer idMunicipio){
        Estado estado = new Estado();
        SqlSession conexionBD = MyBatisUtil.getSession();
        estado = conexionBD.selectOne("catalogo.obtenerEstadoMunicipio", idMunicipio);
        return estado;
    }
    
}
