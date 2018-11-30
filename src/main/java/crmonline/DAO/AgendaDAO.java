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
		String sql = "SELECT a.*, c.*,ct.*, cs.*  "
				+ " FROM AGENDA a "
				+ " INNER JOIN cliente c ON a.id_cliente = c.id"
				+ " INNER JOIN curso cs ON a.id_curso = cs.id"
				+ " INNER JOIN categoria ct ON c.id_categoria = ct.id"
				+ " WHERE ESTADOS = ? ORDER BY DATAV ASC";
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
				c.setNome(rs.getString("c.nome"));
				c.setCidade(rs.getString("c.CIDADE"));
				
				c.getCategoria().setId(rs.getInt("c.ID_CATEGORIA"));
				c.getCategoria().setNome(rs.getString("ct.nome"));
				
				c.setLogradouro(rs.getString("c.LOGRADOURO"));
				
				a.setCliente(c);
				
				Curso cs = new Curso();
				cs.setId(rs.getInt("ID_CURSO"));
				cs.setNome(rs.getString("cs.nome"));
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
			// Calendar c = Calendar.getInstance();
			String x = format.format(a.getData());
			ps.setString(3, x);
			ps.setString(4, a.getHora());
			ps.setInt(5, a.getId_cliente());
			ps.setInt(6, a.getCurso()); // == null? 0 : a.getCurso());
			ps.setInt(7, a.getCodigo());

			if(ps.executeUpdate() > 0)
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
	
	public List<Agenda> filtroListarCliente(Cliente ct) throws SQLException{
		List<Agenda> agendas = new ArrayList<>();
		String SQL = "SELECT * FROM AGENDA WHERE ID_CLIENTE = ? AND ESTADOS = 0"
				+ "INNER JOIN CLIENTE AS C ON C.ID = AGENDA.ID_CLIENTE"
				+ "INNER JOIN CURSO AS CS ON CS.ID = AGENDA.ID_CURSO";
		PreparedStatement ps;
		ps = con.prepareStatement(SQL);
			
		ps.setInt(1, ct.getCodigo());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Agenda a = new Agenda();
			a.setCodigo(rs.getInt("ID"));
			a.setNome(rs.getString("NOME"));
			a.setAtendente(rs.getString("ATENDENTE"));
			
			String data = rs.getString("DATAV");
			SimpleDateFormat b = new SimpleDateFormat("dd/MM/yyyy");
			try {
				a.setData(b.parse(data));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			a.setHora(rs.getString("HORARIO"));
			a.setEstadovisita(rs.getInt("ESTADOS"));
			a.setClassificacao(rs.getString("CLASSFICACOES"));
			a.setObservacao(rs.getString("OBSERVACOES"));
			a.setId_visitante(rs.getInt("ID_VISITANTE"));
			a.setId_cliente(rs.getInt("ID_CLIENTE"));
			
			Cliente c = new Cliente();
			c.setCodigo(rs.getInt("id_cliente"));
			c.setNome(rs.getString("C.NOME"));
			a.setCliente(c);
			
			Curso cs = new Curso();
			cs.setNome(rs.getString("CS.NOME"));
			a.setCursoObj(cs);
			a.setCurso(rs.getInt("ID_CURSO"));
			
			agendas.add(a);
		}
		return agendas;
	}
	public List<Agenda> filtroListarCurso(Curso curso) throws SQLException, ParseException {
		List<Agenda> agendas = new ArrayList<>();
		String SQL = "SELECT * FROM AGENDA WHERE ID_CURSO = ? AND ESTADOS = 0"
				+ "INNER JOIN CLIENTE AS C ON C.ID = AGENDA.ID_CLIENTE"
				+ "INNER JOIN CURSO AS CS ON CS.ID = AGENDA.ID_CURSO";
		PreparedStatement ps;
		ps = con.prepareStatement(SQL);	
		ps.setInt(1, curso.getId());
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
			c.setNome(rs.getString("C.NOME"));
			a.setCliente(c);
			
			Curso cs = new Curso();
			cs.setNome(rs.getString("CS.NOME"));
			a.setCursoObj(cs);
			a.setCurso(rs.getInt("ID_CURSO"));
			
			
			agendas.add(a);
		}
		return agendas;
	}
	
	public boolean excluiVisita(Integer codigo) throws SQLException {
		String SQL = "DELETE FROM AGENDA WHERE ID = ? ";
		PreparedStatement ps = con.prepareStatement(SQL);
		ps.setInt(1, codigo);
		return ps.executeUpdate() > 0;
	}
	
	public ArrayList<Agenda> filtrocliente(Integer codigo) throws ParseException{
		ArrayList<Agenda> agendas = new ArrayList<>();
		String SQL = "SELECT * FROM  AGENDA WHERE ID_CLIENTE = ? AND ESTADOS = 0";
		try {
			PreparedStatement ps = con.prepareStatement(SQL);
		
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
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
				agendas.add(a);
			}
			return agendas;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return null;
	}
	
	
	public ArrayList<Agenda> filtroCURSO(Integer codigo) throws ParseException{
		ArrayList<Agenda> agendas = new ArrayList<>();
		String SQL = "SELECT * FROM  AGENDA WHERE ID_CURSO = ? AND ESTADOS = 0";
		try {
			PreparedStatement ps = con.prepareStatement(SQL);
		
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Agenda a = new Agenda();
				
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
				a.getCursoObj().setId(rs.getInt(""));
				agendas.add(a);
			}
			return agendas;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return null;
	}
	
	
	
}
