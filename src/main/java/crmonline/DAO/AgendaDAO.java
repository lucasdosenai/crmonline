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

	public Agenda teste() {
		Agenda c = new Agenda();
		c.setAtendente("LUCAS");
		c.setClassificacao("5 Pontos");
		c.setCodigo(01);
		c.setCurso(02);
		c.setData("23:11");
		c.setEstadovisita(02);
		c.setHora("23:11");
		c.setId_cliente(1);
		c.setId_visitante(5);
		c.setObservacao("dsadadasd");
		return c;
	}

	public boolean inserir(Agenda agenda) throws SQLException {

		String sql = "INSERT INTO AGENDA(NOME,ATENDENTE,DATAV,HORARIO,ID_VISITANTE,ESTADO,CLASSFICACOES,OBSERVACOES,ID_CLIENTE,ID_CURSO)" 
		+" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, agenda.getNome());
		ps.setString(2, agenda.getAtendente());
		ps.setString(3, agenda.getData());
		ps.setString(4, agenda.getHora());
		ps.setInt(5, agenda.getId_visitante());
		ps.setInt(6, agenda.getEstadovisita());
		ps.setString(7, agenda.getClassificacao());
		ps.setString(8, agenda.getObservacao());
		ps.setInt(9, agenda.getId_cliente());
		ps.setInt(10, agenda.getCurso());
		
		System.out.println(ps.toString());

		return ps.executeUpdate() > 0;
	}

}
