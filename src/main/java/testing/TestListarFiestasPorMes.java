package testing;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoFiestas;
import entidades.Fiestas;

public class TestListarFiestasPorMes {

	public static void main(String[] args) throws SQLException, Exception {
		// TODO Auto-generated method stub
         DaoFiestas dao = new DaoFiestas();
         ArrayList<Fiestas> fiestas = dao.listarFiestasPorMes("03");
         for(Fiestas f:fiestas) {
        	 System.out.print("\n"+f.getNombre());
         }
	}

}
