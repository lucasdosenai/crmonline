package crmonline.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import crmonline.DB.ConDB;
import crmonline.Entidade.Agenda;
import crmonline.Entidade.Cliente;
import crmonline.Entidade.Curso;
import crmonline.MBean.LoginMB;
import sun.security.jca.GetInstance;

public class AgendaDAO {

	private Connection con;

	public AgendaDAO() {
		con = ConDB.getConnection();

	}

	public boolean inserir(Agenda agenda) throws SQLException {
		String sql = "INSERT INTO AGENDA(NOME,ATENDENTE,DATAV,HORARIO,ESTADOS,ID_VISITANTE,ID_CLIENTE,ID_CURSO)"
				+ " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, agenda.getNome());
		ps.setString(2, agenda.getAtendente());

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Calendar c = Calendar.getInstance();
		String x = format.format(agenda.getData());

		ps.setString(3, x);
		ps.setString(4, agenda.getHora());
		ps.setInt(5, agenda.getEstadovisita());
		ps.setInt(6, agenda.getId_visitante());
		ps.setInt(7, agenda.getId_cliente());
		ps.setInt(8, agenda.getCurso() != -1 ? agenda.getCurso() : 0);
		System.out.println(ps.toString());
		return ps.executeUpdate() > 0;
	}

	public Boolean deletaVisita(Integer id) throws SQLException {
		String SQL = "DELETE FROM AGENDA WHERE ID = ?";
		PreparedStatement ps = con.prepareStatement(SQL);
		ps.setInt(1, id);
		return ps.executeUpdate() > 0;
	}

	
	/*x*/
	public List<Agenda> listarAgenda(String codigo) {
		List<Agenda> agenda = new ArrayList<>();
		String sql = "SELECT a.*, c.nome as cliente, cs.nome as curso  "
				+ " FROM AGENDA a "
				+ " INNER JOIN cliente c ON a.id_cliente = c.id"
				+ " INNER JOIN curso cs ON a.id_curso = cs.id"
				+ " WHERE ESTADOS = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, codigo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Agenda a = new Agenda();
				a.setCodigo(rs.getInt("ID"));
				a.setNome(rs.getString("NOME"));
				a.setAtendente(rs.getString("ATENDENTE"));
				
				String data = rs.getString("DATAV");
				SimpleDateFormat b = new SimpleDateFormat("dd/MM/yyyy");
				a.setData(b.parse(data));
				a.setHora(rs.getString("HORARIO"));
				a.setEstadovisita(rs.getInt("ESTADOS"));
				a.setClassificacao(rs.getString("CLASSFICACOES"));
				a.setObservacao(rs.getString("OBSERVACOES"));
				a.setId_visitante(rs.getInt("ID_VISITANTE"));
				a.setId_cliente(rs.getInt("ID_CLIENTE"));
				
				Cliente c = new Cliente();
				c.setCodigo(rs.getInt("id_cliente"));
				c.setNome(rs.getString("cliente"));
				a.setCliente(c);
				
				Curso cs = new Curso();
				cs.setNome(rs.getString("curso"));
				a.setCursoObj(cs);
				a.setCurso(rs.getInt("ID_CURSO"));
				
				
				agenda.add(a);
			}
			return agenda;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean updateVisita(Agenda a) {
		System.out.println("UpdateVisita()");
		String SQL = "UPDATE AGENDA SET NOME = ? ,ATENDENTE = ? ,DATAV = ? ,HORARIO = ? "
				+ ",ID_CLIENTE = ? ,ID_CURSO = ?  WHERE ID = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SQL);
			ps.setString(1, a.getNome());
			ps.setString(2, a.getAtendente());

			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Calendar c = Calendar.getInstance();
			String x = format.format(a.getData());
			ps.setString(3, x);
			ps.setString(4, a.getHora());
			ps.setInt(5, a.getId_cliente());
			ps.setInt(6, a.getCurso()); //  != -1 ? a.getCurso() : 0
			ps.setInt(7, a.getCodigo());

			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean realizaVisita(Agenda a) throws SQLException {
		String SQL = "UPDATE AGENDA SET ESTADOS = 1, CLASSFICACOES = ?, OBSERVACOES = ? WHERE ID = ?";
		PreparedStatement ps;
		ps = con.prepareStatement(SQL);
		
		ps.setString(1, a.getClassificacao());
		ps.setString(2, a.getObservacao());
		ps.setInt(3, a.getCodigo());
		
		return ps.executeUpdate() > 0;	
	}

	public boolean excluiVisita(Integer codigo) throws SQLException {
		String SQL = "DELETE FROM AGENDA WHERE ID = ? ";
		PreparedStatement ps = con.prepareStatement(SQL);
		ps.setInt(1, codigo);
		return ps.executeUpdate() > 0;
	}
}
