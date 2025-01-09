package dominio;

import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Colaborador;
import pojo.Mensaje;
import pojo.RegistroColaboradorUnidad;
import pojo.Unidad;

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

    public static List<Colaborador> obtenerConductores() {
        List<Colaborador> conductores = null;
        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
            try {
                conductores = conexionDB.selectList("colaborador.conductores");
                if (conductores != null) {
                    return conductores;
                }
                conexionDB.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conductores;
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
                if (enviosAsignados > 0) {
                    String plural = enviosAsignados == 1 ? " envio" : " envios";
                    respuesta.setContenido("El colaborador esta asignado a " + enviosAsignados + plural);
                } else {
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

    public static Mensaje asignarUnidad(Integer idColaborador, Integer idUnidad) {
        Mensaje respuesta = new Mensaje();
        respuesta.setError(true);
        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
                try {
                    HashMap<String, Integer> parametros = new LinkedHashMap<>();
                    parametros.put("idColaborador", idColaborador);
                    parametros.put("idUnidad", idUnidad);
                    int filasAfectadas = conexionDB.insert("colaborador.asignarUnidad", parametros);
                    conexionDB.commit();
                    if (filasAfectadas > 0) {
                        respuesta.setError(false);
                        respuesta.setContenido("Colaborador asignado con exito");
                        return respuesta;
                    } else {
                        respuesta.setContenido("El colaborador/unidad no existe");
                    }
                    conexionDB.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        } else {
            respuesta.setContenido("Por el momento no es posible asignar colaboradores");
        }
        return respuesta;
    }

    public static Mensaje comprobarColaboradorUnidad(Integer idColaborador, Integer idUnidad) {
        Mensaje respuesta = new Mensaje();
        respuesta.setError(true);
        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
            String contenidoMensaje = "";
            Unidad asignacionesColaborador = conexionDB.selectOne("colaborador.comprobarColaboradorUnidad", idColaborador);
            if (asignacionesColaborador != null) {
                contenidoMensaje = "El colaborador ya se encuentra asignado a una unidad con el vin "+asignacionesColaborador.getVin()+"\n";
            }

            Colaborador asignacionesUnidad = conexionDB.selectOne("colaborador.comprobarUnidadColaborador", idUnidad);
            if (asignacionesUnidad != null) {
                contenidoMensaje = contenidoMensaje + "La unidad ya se encuentra asignada a un colaborador con el número de personal "
                                   +asignacionesUnidad.getNoPersonal();
            }
            respuesta.setContenido(contenidoMensaje);
            if (asignacionesColaborador == null && asignacionesUnidad == null) {
                respuesta.setError(false);
            }
        }
        return respuesta;
    }

    public static Mensaje quitarAsignacionUnidad(Integer idColaborador, Integer idUnidad) {
        Mensaje respuesta = new Mensaje();
        respuesta.setError(true);
        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
            try { 
                int filasAfectadas = conexionDB.delete("colaborador.quitarAsignacionColaborador", idColaborador);
                conexionDB.commit();
                if (filasAfectadas > 0) {
                    respuesta.setError(false);
                    respuesta.setContenido("La asignación del colaborador fue eliminada  con exito \n");
                }
                
                filasAfectadas = conexionDB.delete("colaborador.quitarAsignacionUnidad", idUnidad);
                conexionDB.commit();
                if(filasAfectadas > 0){
                    respuesta.setError(false);
                    respuesta.setContenido(respuesta.getContenido() + "La asignacion de la unidad fue eliminada con exito");
                }
                
                conexionDB.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return respuesta;
    }

    public static Unidad obtenerUnidad(int idColaborador) {
        Unidad respuesta = null;
        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
            Unidad unidad = conexionDB.selectOne("colaborador.obtenerUnidad", idColaborador);
            if (unidad != null) {
                return unidad;
            }
        }
        return respuesta;
    }

    public static Mensaje guardarFoto(Integer idCliente, byte[] foto) {
        Mensaje respuesta = new Mensaje();
        respuesta.setError(true);
        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
            try {
                HashMap<String, Object> parametros = new LinkedHashMap<>();
                String base64Foto = bytesToBase64(foto);
                parametros.put("idColaborador", idCliente);
                parametros.put("fotografia", base64Foto);
                int filasAfectadas = conexionDB.update("colaborador.guardarFoto", parametros);
                conexionDB.commit();
                if (filasAfectadas >= 1) {
                    respuesta.setError(false);
                    respuesta.setContenido(" La Fotografia del colaborador se ha guardado correctamente");
                } else {
                    respuesta.setContenido("La foto del colaborador no pudo ser agregada");
                }
                conexionDB.close();
            } catch (Exception e) {
                respuesta.setContenido(e.getMessage());
            }
        } else {
            respuesta.setContenido("Por el momento no es posible modificar colaboradoress ");
        }
        return respuesta;
    }
    public static String bytesToBase64(byte[] byteArray) {
        try {
            if (byteArray != null && byteArray.length > 0) {
                return Base64.getEncoder().encodeToString(byteArray);
            } else {
                System.err.println("El array de bytes está vacío o es nulo.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static List<Unidad> obtenerUnidadesPorAsignacion() {
        List<Unidad> unidades = null;
        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
            try {
                unidades = conexionDB.selectList("colaborador.obtenerAsignacionesUnidad");
                if (unidades != null) {
                    return unidades;
                }
                conexionDB.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return unidades;
    }

    public static List<Colaborador> obtenerColaboradoresPorAsignacion() {
        List<Colaborador> colaboradores = null;
        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
            try {
                colaboradores = conexionDB.selectList("colaborador.obtenerAsignacionesColaborador");
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

    public static List<RegistroColaboradorUnidad> obtenerRegistrosColaboradorUnidad() {
        List<RegistroColaboradorUnidad> unidades = null;
        SqlSession conexionDB = MyBatisUtil.getSession();
        if (conexionDB != null) {
            try {
                unidades = conexionDB.selectList("colaborador.obtenerRegistrosColaboradorUnidad");
                if (unidades != null) {
                    return unidades;
                }
                conexionDB.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return unidades;
    }
}
