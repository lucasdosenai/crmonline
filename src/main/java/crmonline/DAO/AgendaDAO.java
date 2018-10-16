package crmonline.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import crmonline.DB.ConDB;
import crmonline.Entidade.Agenda;
import sun.security.jca.GetInstance;

public class AgendaDAO {

	private Connection con;

	public AgendaDAO() {
		con = ConDB.getConnection();

	}
	
	public boolean inserir(Agenda agenda) throws SQLException {

		String sql = "INSERT INTO AGENDA(NOME,ATENDENTE,DATAV,HORARIO,ESTADOS,CLASSFICACOES,OBSERVACOES,ID_VISITANTE,ID_CLIENTE,ID_CURSO)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, agenda.getNome());
		ps.setString(2, agenda.getAtendente());
		
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		String x = format.format(agenda.getData());
		
		ps.setString(3, x);
		ps.setString(4, agenda.getHora());
		ps.setInt(5, agenda.getEstadovisita());
		ps.setString(6, agenda.getClassificacao());
		ps.setString(7, agenda.getObservacao());
		ps.setInt(8, agenda.getId_visitante());
		ps.setInt(9, agenda.getId_cliente());
		ps.setInt(10, agenda.getCurso());

		System.out.println(ps.toString());

		return ps.executeUpdate() > 0;
	}
	
	public List<Agenda> listarAgenda() {
		
		List<Agenda> agenda = new ArrayList<>();
		
		String sql = "SELECT * FROM AGENDA";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Agenda a = new Agenda();
				a.setCodigo(rs.getInt("ID"));
				a.setNome(rs.getString("NOME"));
				a.setAtendente(rs.getString("ATENDENTE"));
				a.setData(rs.getDate("DATAV"));
				a.setHora(rs.getString("HORARIO"));
				a.setEstadovisita(rs.getInt("ESTADOS"));
				a.setClassificacao(rs.getString("CLASSIFICACOES"));
				a.setObservacao(rs.getString("OBSERVACOES"));
				a.setAtendente(rs.getString("ID_VISITANTE"));
				a.setId_cliente(rs.getInt("ID_CLIENTE"));
				a.setCurso(rs.getInt("ID_CURSO"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	
	
}
