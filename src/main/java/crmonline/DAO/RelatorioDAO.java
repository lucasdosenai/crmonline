package crmonline.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import crmonline.DB.ConDB;
import crmonline.Entidade.Cliente;

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
			ResultSet rs = ps.executeQuery();
			Cliente c = new Cliente();
			if(rs.next()) {	
				c.setNome(rs.getString("NOME"));
			}
			return c.getNome();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Sem informações";
	}
	
	
	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}
	
}
