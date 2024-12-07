package dominio;

import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;
import pojo.Mensaje;

public class ImpColaboradores {
    public static List<Colaborador> obtenerColaboradores(){
        List<Colaborador> colaboradores = null;
        SqlSession conexionDB = MyBatisUtil.getSession();
        if(conexionDB != null){
            try {
                colaboradores = conexionDB.selectList("colaborador.colaboradores");
                if(colaboradores != null){
                    return colaboradores;
                }
                conexionDB.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colaboradores;
    }
    public static Colaborador obtenerColaboradorNoPersonal(String noPersonal){
        Colaborador colaborador = null;
        SqlSession conexionDB = MyBatisUtil.getSession();
        if(conexionDB != null){
            try {
                colaborador = conexionDB.selectOne("colaborador.colaboradorPorNoPersonal",noPersonal);
                if(colaborador != null){
                    return colaborador;
                }
                conexionDB.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colaborador;
    }
    public static Mensaje agregarColaborador(Colaborador colaborador){
        Mensaje respuesta = new Mensaje();
        respuesta.setError(true);
        SqlSession conexionDB = MyBatisUtil.getSession();
        if(conexionDB != null){
            try {
               int filasAfectadas = conexionDB.insert("colaborador.registro",colaborador);
               conexionDB.commit();
               if(filasAfectadas >= 1){
                   respuesta.setError(false);
                   respuesta.setContenido("El colaborador "+colaborador.toString()+" a sido registrado");
               }else{
                   respuesta.setContenido("El colaborador no pudo ser registrado");
               }
               conexionDB.close();
            } catch (Exception e) {
                respuesta.setContenido(e.getMessage());
            }
        }else{
            respuesta.setContenido("Por el momento el servicio no se encuentra disponible");
        }
        return respuesta;
    }
    public static Mensaje editarColaborador(Colaborador colaborador){
        Mensaje respuesta = new Mensaje();
        respuesta.setError(true);
        SqlSession conexionDB = MyBatisUtil.getSession();
        if(conexionDB != null){
            try {
                int filasAfectadas = conexionDB.update("colaborador.modificar",colaborador);              
                conexionDB.commit();
                if(filasAfectadas >= 1){
                   respuesta.setError(false);
                   respuesta.setContenido("El colaborador ha sido modificado con exito");
               }else{
                   respuesta.setContenido("El colaborador no pudo ser modificado");
               }
               conexionDB.close();
            } catch (Exception e) {
                respuesta.setContenido(e.getMessage());
            }
        }else{
            respuesta.setContenido("Por el momento no es posible modificar colaboradores");
        }
        return respuesta;
    }
    
    public static Mensaje eliminarColaborador(String idColaborador){
        Mensaje respuesta = new Mensaje();
        respuesta.setError(true);
        SqlSession conexionDB = MyBatisUtil.getSession();
        if(conexionDB != null){
            try {
                int filasAfectadas = conexionDB.delete("colaborador.eliminar",idColaborador);              
                conexionDB.commit();
                if(filasAfectadas >= 1){
                   respuesta.setError(false);
                   respuesta.setContenido("El colaborador ha sido eliminado con exito");
               }else{
                   respuesta.setContenido("El colaborador no pudo ser eliminado");
               }
            } catch (Exception e) {
                respuesta.setContenido(e.getMessage());
            }
        }else{
            respuesta.setContenido("Por el momento no es posible eliminar colaboradores");
        }
        return respuesta;
    }
}
