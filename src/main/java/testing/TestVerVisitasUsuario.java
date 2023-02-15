package testing;

import java.sql.SQLException;

import dao.DaoVisitas;
import entidades.Visitas;

public class TestVerVisitasUsuario {

	public static void main(String[] args) throws SQLException, Exception {
		// TODO Auto-generated method stub
        DaoVisitas dao = new DaoVisitas();
        Visitas visita = dao.mostrarVisitasUsuario("David");
        System.out.print(visita.getUsuario()+", has visitado esto: "+visita.getContador()+" vez/veces.");
	}

}
