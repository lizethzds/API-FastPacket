package dominio;

import java.util.HashMap;
import java.util.LinkedHashMap;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.AutenticacionColaborador;
import pojo.Colaborador;


public class ImpAutenticacion {

    public static AutenticacionColaborador validarSesionEscritorio(String noPersonal, String password){
        
        AutenticacionColaborador respuestaLogin = new AutenticacionColaborador();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuestaLogin.setError(true);
        
        if(conexionBD != null){
            try{
                // Crear el mapa de parámetros
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("noPersonal", noPersonal);
                parametros.put("password", password);
                
                System.err.println("noPersonal"+noPersonal+"  password"+password);
                // Pasar parámetros a la consulta
                Colaborador colaborador = conexionBD.selectOne("autenticacion.loginColaborador", parametros);
                
                if(colaborador != null){
                    respuestaLogin.setError(false);
                    respuestaLogin.setMensaje("Bienvenido al sistema de Fast-Packet, colaborador: " + colaborador.getNombre());
                    respuestaLogin.setColaboradorSesion(colaborador);
                } else {
                    respuestaLogin.setMensaje("Credenciales incorrectas");
                }
                
            }catch(Exception e){
                e.printStackTrace();
                respuestaLogin.setMensaje("Error en la validación de la sesión.");
            } finally {
                conexionBD.close(); 
            }
            
        }else{
            respuestaLogin.setMensaje("Por el momento no hay conexión con la base de datos.");
        }
        
        return respuestaLogin;
    }

    public static AutenticacionColaborador validarSesionMovil(String noPersonal, String password){
        
        AutenticacionColaborador respuestaLogin = new AutenticacionColaborador();
        SqlSession conexionBD = MyBatisUtil.getSession();
        respuestaLogin.setError(true);
        
        if(conexionBD != null){
            try{
               
                HashMap<String, String> parametros = new LinkedHashMap<>();
                parametros.put("noPersonal", noPersonal);
                parametros.put("password", password);
          
                Colaborador colaborador = conexionBD.selectOne("autenticacion.loginColaborador", parametros);
                
                if(colaborador != null){
                    if(colaborador.getIdRol() != 3){
                        respuestaLogin.setMensaje("Personal no autorizado.");
                    } else {
                        respuestaLogin.setError(false);
                        respuestaLogin.setMensaje("Bienvenido al sistema de Fast-Packet, conductor: " + colaborador.getNombre());
                        respuestaLogin.setColaboradorSesion(colaborador);
                    }
                } else {
                    respuestaLogin.setMensaje("Credenciales incorrectas.");
                }
                
            }catch(Exception e){
                e.printStackTrace();
                respuestaLogin.setMensaje("Error en la validación de la sesión.");
            } finally {
                conexionBD.close(); 
            }
            
        }else{
            respuestaLogin.setMensaje("Por el momento no hay conexión con la base de datos.");
        }
        
        return respuestaLogin;
    }
}
