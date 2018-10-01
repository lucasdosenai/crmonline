package crmonline.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import crmonline.Entidade.Categoria;

public class CategoriaDAO {
	
	private Connection con;
	
	public boolean novaCategoria(Categoria c) {
		String SQL = "INSERT INTO CATEGORIA VALUES(?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setString(1, c.getNome());
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
