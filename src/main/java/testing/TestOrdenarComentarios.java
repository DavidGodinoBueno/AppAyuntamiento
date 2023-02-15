package testing;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoComentarios;
import entidades.Comentarios;

public class TestOrdenarComentarios {

	public static void main(String[] args) throws SQLException, Exception {
		// TODO Auto-generated method stub
        DaoComentarios dao = new DaoComentarios();
        ArrayList<Comentarios> comentarios = dao.ordenFechaComentarios("ASC");
        for(Comentarios c:comentarios) {
        	System.out.print(c.getDescripcion()+", "+c.getFechacomentario()+"\n");
        }
	}

}
