package crmonline.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import crmonline.DB.ConDB;
import crmonline.Entidade.Categoria;

public class ClienteDAO {
	
	private Connection con; 
	
	public ArrayList<Categoria> listaCategorias(){
		ArrayList<Categoria> categorias = new ArrayList<>();
		String SQL = "SELECT * FROM CATEGORIAS";
		
		try {
			PreparedStatement ps = con.prepareStatement(SQL);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Categoria c = new Categoria();
				c.setCodigo(rs.getInt("ID"));
				c.setNome(rs.getString("NOME"));
				
				categorias.add(c);
			}
		return categorias;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
