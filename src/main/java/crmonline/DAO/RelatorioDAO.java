package crmonline.DAO;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import crmonline.DB.ConDB;
import crmonline.Entidade.Agenda;
import crmonline.Entidade.ClasseGenerica;
import crmonline.Entidade.Cliente;
import crmonline.Entidade.Curso;
import crmonline.Entidade.RelatorioFiltro;

public class RelatorioDAO {
	
	private Connection con;
	public RelatorioDAO() {
		con = ConDB.getConnection();
	}
	
	public List<Agenda> listaFiltro(RelatorioFiltro filtro) throws SQLException{
		String SQL = "SELECT * from agenda";
		List<Agenda> agendas = new ArrayList<>();
		int contador = 0;
		for(Field var : filtro.getClass().getDeclaredFields()) {
			try {
				Object value = var.get(filtro);
				if(value != null) {
					if(contador == 0) { 
						if (var.getName().equals("ID_CLIENTE")) {
							SQL += " WHERE " + var.getName() + " = " + value ;  
						} else
							SQL += " WHERE " + var.getName() + " LIKE '" + value + "%'";  
						}
					else {
						SQL += " AND " + var.getName() + " LIKE '" + value + "%'";  
					}
					contador++;
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(contador > 0) { 
			SQL +=  " AND ESTADOS = 1";
		}
		PreparedStatement ps = con.prepareStatement(SQL);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
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
			
			a.setCliente(clientePorID(a.getId_cliente()));
			a.setCurso(rs.getInt("ID_CURSO"));
			a.setCursoObj(cursoPorID(a.getCurso()));
			
			agendas.add(a);
		}
		return agendas;
	}
	
	public Cliente clientePorID(Integer codigo) throws SQLException{
		Cliente c = new Cliente();
		String SQL = "SELECT * FROM CLIENTE WHERE ID = " + codigo;
		
		PreparedStatement ps = con.prepareStatement(SQL);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			c.setNome(rs.getString("NOME"));
		}
		return c;
	}
	
	public Curso cursoPorID(Integer codigo) throws SQLException{
		Curso c = new Curso();
		String SQL = "SELECT * FROM CURSO WHERE ID = " + codigo;
		
		PreparedStatement ps = con.prepareStatement(SQL);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			c.setNome(rs.getString("NOME"));
			c.setVAGAS_TOTAL(rs.getInt("VAGAS_TOTAL"));
		}
		return c;
	}
	
	public List<Agenda> listaRelatorioPorTipo(Integer codigo,String acao) throws SQLException, ParseException{
		List<Agenda> relatorios = new ArrayList<>();
		Agenda a = new Agenda();
		String SQL = "SELECT * FROM AGENDA";
		
		if(acao == "0") {
			SQL = "select a.*,c.*,cs.* from agenda as a" + 
					"	inner join cliente as c on c.id = a.id_cliente" + 
					"   inner join curso as cs on cs.id = a.id_curso" + 
					"   where a.estados = 1 AND a.id_cliente = ?";
		}else if(acao == "cargo") {
			SQL = "SELECT * FROM AGENDA WHERE ID_CURSO = ?";
		}
		
		PreparedStatement ps = con.prepareStatement(SQL);
		ps.setInt(1, codigo);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
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
			c.setCodigo(rs.getInt("C.ID"));
			c.setNome(rs.getString("C.NOME"));
			a.setCliente(c);
			
			Curso cs = new Curso();
			cs.setNome(rs.getString("CS.NOME"));
			cs.setId(rs.getInt("CS.ID"));
			cs.getCATEGORIA().setId(rs.getInt("CS.ID_CATEGORIA"));
			a.setCursoObj(cs);
			a.setCurso(rs.getInt("ID_CURSO"));
			
			relatorios.add(a);
		}
		
		return relatorios;
	}
	
	public List<Agenda> listaAgendaKeyDown(String busca) {
		String SQL;
		if(busca != "") { 
			SQL = "SELECT * FROM AGENDA WHERE DATAV LIKE ? ESTADOS = 1";
			}
		else {
			SQL = "SELECT * FROM AGENDA ESTADOS = 1";
			}
		List<Agenda> listaCompleta = new ArrayList<>();
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(SQL);
			ps.setString(1, "%"+busca);
			ResultSet rs = ps.executeQuery();
		
			while(rs.next()) {
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
					a.setData(new Date(0));
				}
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
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
