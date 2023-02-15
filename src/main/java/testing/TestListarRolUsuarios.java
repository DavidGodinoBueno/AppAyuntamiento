package testing;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoUsuarios;
import entidades.Usuarios;

public class TestListarRolUsuarios {

	public static void main(String[] args) throws SQLException, Exception {
		// TODO Auto-generated method stub
        DaoUsuarios dao = new DaoUsuarios();
        ArrayList<Usuarios> roles = dao.listarRolesUsuarios();
        for(Usuarios rol:roles) {
        	System.out.print(rol.getRol()+"\n");
        }
	}

}
