package testing;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoNoticias;
import entidades.Noticias;

public class TestBuscarNoticias {

	public static void main(String[] args) throws SQLException, Exception {
		// TODO Auto-generated method stub
        DaoNoticias dao = new DaoNoticias();
        ArrayList<Noticias> noticias = dao.buscarNoticias("ser");
        for(Noticias n:noticias) {
        	System.out.print(n.getTitulo()+"\n");
        }
	}

}
