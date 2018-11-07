package crmonline.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import crmonline.DB.ConDB;
import crmonline.Entidade.Agenda;
import crmonline.Entidade.Cliente;

public class RelatorioDAO {
	
	private Connection con;
	public RelatorioDAO() {
		con = ConDB.getConnection();
	}
	public List<Agenda> listaAgendaKeyDown(String busca) throws SQLException, ParseException{
		String SQL = "SELECT * FROM AGENDA WHERE DATAV LIKE ?%";
		List<Agenda> listaCompleta = new ArrayList<>();
		PreparedStatement ps = con.prepareStatement(SQL);
		ps.setString(1, busca);
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
			a.setCurso(rs.getInt("ID_CURSO"));
			listaCompleta.add(a);
		}
		return listaCompleta;
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
