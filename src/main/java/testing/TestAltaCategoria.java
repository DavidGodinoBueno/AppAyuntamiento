package testing;

import java.sql.SQLException;

import dao.DaoCategoriasServicios;
import entidades.CategoriasServicios;

public class TestAltaCategoria {

	public static void main(String[] args) throws SQLException, Exception {
		// TODO Auto-generated method stub
        DaoCategoriasServicios dao = new DaoCategoriasServicios();
        CategoriasServicios nueva = new CategoriasServicios();
        nueva.setDescripcion("Ejemplo");
        nueva.setFotocategoria(null);
        dao.altaCategoria(nueva);
        System.out.print("Cat insertada");
	}

}
