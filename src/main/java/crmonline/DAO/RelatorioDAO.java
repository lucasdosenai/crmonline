package crmonline.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import crmonline.DB.ConDB;

public class RelatorioDAO {
	
	private Connection con;
	public RelatorioDAO() {
		con = ConDB.getConnection();
	}
	
	public String clienteConverte(String codigo) {
		String SQL = "SELECT*FROM CLIENTE WHERE ID = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(SQL);
			ps.setString(1, codigo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return codigo;
	}
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
}
