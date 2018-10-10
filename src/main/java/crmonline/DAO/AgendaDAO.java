package crmonline.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

}
