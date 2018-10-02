package crmonline.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crmonline.DB.ConDB;
import crmonline.Entidade.Categoria;

public class CategoriaDAO {
	
	private Connection con;
	
	public CategoriaDAO() {
		con = ConDB.getConnection();
	}
	
	public List<Categoria> todasCategorias(){
		List<Categoria> categorias = new ArrayList<>();
		String SQL = "SELECT * FROM CATEGORIA";
		
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
	
	public boolean novaCategoria(Categoria c) {
		String SQL = "INSERT INTO CATEGORIA VALUES(0 , ?)";
		
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
