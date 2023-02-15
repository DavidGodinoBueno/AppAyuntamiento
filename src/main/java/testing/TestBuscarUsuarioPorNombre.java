package testing;

import java.sql.SQLException;

import dao.DaoUsuarios;
import entidades.Usuarios;

public class TestBuscarUsuarioPorNombre {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
        DaoUsuarios dao = new DaoUsuarios();
        Usuarios usuario = dao.findUsuarioByName("David");
        System.out.print(usuario.getNombre()+", "+usuario.getRol());
	}

}
