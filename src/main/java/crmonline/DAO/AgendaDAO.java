package crmonline.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import crmonline.DB.ConDB;
import crmonline.Entidade.Agenda;

public class AgendaDAO {

	private Connection con;
	
	public AgendaDAO() {
		con = ConDB.getConnection();
		
		
	}
	
	
	public boolean inserir(Agenda agenda) {
		
		String sql = "INSERT INTO AGENDA VALUES(0, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, agenda.getAtendente());
			ps.setString(2, agenda.getData());
			ps.setString(3, agenda.getHora());
			ps.setInt(4, agenda.getId_visitante());
			ps.setInt(5, agenda.getEstadoVisita());
			ps.setString(6, agenda.getClassificacao());
			ps.setString(6, agenda.getObservacao());
			ps.setInt(7, agenda.getId_cliente());
			ps.setInt(9, agenda.getCurso());
			
			return ps.executeUpdate() > 0;
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
