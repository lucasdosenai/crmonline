package crmonline.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crmonline.DB.ConDB;
import crmonline.Entidade.Cargo;

public class CargoDAO {

	private Connection con;
	public CargoDAO() {
		con = ConDB.getConnection();

	}
	
    public boolean inserircargo (Cargo cargo) throws SQLException {
    	String sql = "INSERT INTO cargo(nome)"
    			+ " VALUES (?)";
    	
    	PreparedStatement ps = con.prepareStatement(sql);
    	ps.setString(1, cargo.getNome());
    	
    	return ps.executeUpdate() > 0;
    }
	
    public List<Cargo> listarcargo() {
    	List<Cargo> cargo = new ArrayList<>();
    	
    	String sql = "SELECT * FROM cargo";
    	try {
    	PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()){
			Cargo c = new Cargo();
			c.setNome(rs.getString("nome"));
			
			cargo.add(c);
			
		}
		
		return cargo;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
	
}