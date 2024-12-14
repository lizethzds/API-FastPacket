package dominio;

import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;
import pojo.Mensaje;

public class ImpColaboradores {

    public static List<Colaborador> obtenerColaboradores() {
        List<Colaborador> colaboradores = null;
        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
            try {
                colaboradores = conexionDB.selectList("colaborador.colaboradores");
                if (colaboradores != null) {
                    return colaboradores;
                }
                conexionDB.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colaboradores;
    }

    public static Colaborador obtenerColaboradorNoPersonal(String noPersonal) {
        Colaborador colaborador = null;
        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
            try {
                colaborador = conexionDB.selectOne("colaborador.colaboradorPorNoPersonal", noPersonal);
                if (colaborador != null) {
                    return colaborador;
                }
                conexionDB.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return colaborador;
    }

    public static Mensaje agregarColaborador(Colaborador colaborador) {
        Mensaje respuesta = new Mensaje();
        respuesta.setError(true);
        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
            try {
                int filasAfectadas = conexionDB.insert("colaborador.registro", colaborador);
                conexionDB.commit();
                if (filasAfectadas >= 1) {
                    respuesta.setError(false);
                    respuesta.setContenido("El colaborador " + colaborador.toString() + " a sido registrado");
                } else {
                    respuesta.setContenido("El colaborador no pudo ser registrado");
                }
                conexionDB.close();
            } catch (Exception e) {
                respuesta.setContenido(e.getMessage());
            }
        } else {
            respuesta.setContenido("Por el momento el servicio no se encuentra disponible");
        }
        return respuesta;
    }

    public static Mensaje editarColaborador(Colaborador colaborador) {
        Mensaje respuesta = new Mensaje();
        respuesta.setError(true);
        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
            try {
                int filasAfectadas = conexionDB.update("colaborador.modificar", colaborador);
                conexionDB.commit();
                if (filasAfectadas >= 1) {
                    respuesta.setError(false);
                    respuesta.setContenido("El colaborador ha sido modificado con exito");
                } else {
                    respuesta.setContenido("El colaborador no pudo ser modificado");
                }
                conexionDB.close();
            } catch (Exception e) {
                respuesta.setContenido(e.getMessage());
            }
        } else {
            respuesta.setContenido("Por el momento no es posible modificar colaboradores");
        }
        return respuesta;
    }

    public static Mensaje eliminarColaborador(int idColaborador) {
        Mensaje respuesta = new Mensaje();
        respuesta.setError(true);
        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
            try {
                int filasAfectadas = conexionDB.delete("colaborador.eliminar", idColaborador);
                conexionDB.commit();
                if (filasAfectadas >= 1) {
                    respuesta.setError(false);
                    respuesta.setContenido("El colaborador ha sido eliminado con exito");
                } else {
                    respuesta.setContenido("El colaborador no pudo ser eliminado");
                }
            } catch (Exception e) {
                respuesta.setContenido(e.getMessage());
            }
        } else {
            respuesta.setContenido("Por el momento no es posible eliminar colaboradores");
        }
        return respuesta;
    }

    public static Mensaje obtenerFotografia(int idColaborador) {
        Mensaje respuesta = new Mensaje();
        respuesta.setError(true);
        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
            try {
                String fotografiaBase64 = conexionDB.selectOne("colaborador.obtenerFotografia", idColaborador);
                if (fotografiaBase64 != null) {
                    respuesta.setError(false);
                    respuesta.setContenido(fotografiaBase64);
                    return respuesta;
                } else {
                    respuesta.setContenido("El colaborador no existe o no cuenta con fotografía");
                }
                conexionDB.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            respuesta.setContenido("Por el momento no es posible obtner la fotografía de colaboradores");
        }
        return respuesta;
    }

    public static Mensaje comprobarValoresRepetidos(Colaborador colaborador) {
        Mensaje respuesta = new Mensaje();
        respuesta.setError(true);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB != null) {
                StringBuilder errores = new StringBuilder();
                String correo = conexionDB.selectOne("colaborador.comprobarCorreo", colaborador.getCorreo());
                if (correo != null) {
                    errores.append("El correo electrónico ya se encuentra registrado.\n");
                }
                String curp = conexionDB.selectOne("colaborador.comprobarCURP", colaborador.getCurp());
                if (curp != null) {
                    errores.append("La CURP ya se encuentra registrada.\n");
                }
                String noPersonal = conexionDB.selectOne("colaborador.comprobarNoPersonal", colaborador.getNoPersonal());
                if (noPersonal != null) {
                    errores.append("El número de personal ya se encuentra registrado.\n");
                }
                if ("Conductor".equals(colaborador.getRol())) {
                    String noLicencia = conexionDB.selectOne("colaborador.comprobarLicencia", colaborador.getNoLicencia());
                    if (noLicencia != null) {
                        errores.append("El número de licencia ya se encuentra registrado.\n");
                    }
                }
                if (errores.length() > 0) {
                    respuesta.setContenido(errores.toString().trim());
                } else {
                    respuesta.setError(false);
                    respuesta.setContenido("Los valores no están repetidos.");
                }
            } else {
                respuesta.setContenido("No fue posible establecer conexión con la base de datos.");
            }
        } catch (Exception e) {
            respuesta.setContenido("Ocurrió un error al comprobar los valores: " + e.getMessage());
            e.printStackTrace();
        }

        return respuesta;
    }

    public static Mensaje comprobarEnvios(int idColaborador) {
        Mensaje respuesta = new Mensaje();
        respuesta.setError(true);

        try (SqlSession conexionDB = MyBatisUtil.getSession()) {
            if (conexionDB != null) {
                int enviosAsignados = conexionDB.selectOne("colaborador.comprobarEnvios", idColaborador);
                if(enviosAsignados > 0){
                    String plural = enviosAsignados == 1 ? " envio" : " envios"; 
                    respuesta.setContenido("El colaborador esta asignado a " + enviosAsignados + plural);
                }else{
                    respuesta.setContenido("El colaborador no cuenta con envios asignados");
                }
                respuesta.setError(false);
            } else {
                respuesta.setContenido("No fue posible establecer conexión con la base de datos.");
            }
        } catch (Exception e) {
            respuesta.setContenido("Ocurrió un error al comprobar los valores: " + e.getMessage());
            e.printStackTrace();
        }

        return respuesta;
    }
}
