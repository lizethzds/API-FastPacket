/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

import java.util.List;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojo.Rol;

/**
 *
 * @author david
 */
public class ImpRoles {
    public static List<Rol> obtenerRoles(){
        List<Rol> roles = null;
        SqlSession conexionDB = MyBatisUtil.getSession();
        if(conexionDB != null){
            try {
                roles = conexionDB.selectList("roles.obtenerRoles");
                return roles;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return roles;
    }
}
