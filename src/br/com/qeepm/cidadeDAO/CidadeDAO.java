package br.com.qeepm.cidadeDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.qeepm.Factory.*;
import br.com.qeepm.pojo.Cidade;
public class CidadeDAO {
	
	private Connection conn;
	
	public CidadeDAO() {
		this.conn = new ConnectionFactory().getConnetion();	
	}
	public void insereCidade(Cidade Cidade) throws SQLException {
		
		String sql = "INSERT INTO cidades.cidade"
				+ "(ddd, nome, nro_habitantes, renda_per_capita, capital, estado, nome_prefeito) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		PreparedStatement  stmt = conn.prepareStatement(sql);
		
		stmt.setInt(1, Cidade.getDdd());
		stmt.setString(2, Cidade.getNome());
		stmt.setInt(3, Cidade.getNroHabitantes());
		stmt.setFloat(4, Cidade.getRendaPerCapita());
		stmt.setBoolean(5, Cidade.isCapital());
		stmt.setString(6, Cidade.getEstado());
		stmt.setString(7, Cidade.getNomePrefeito());
		
		stmt.executeUpdate();
		stmt.close();
	}
	
	private void mapeiaLista(List<Cidade> cidades, ResultSet rs) throws SQLException {
		while (rs.next()) {
			Cidade cidade = new Cidade();
			
			cidade.setDdd(rs.getInt("ddd"));
			cidade.setNome(rs.getString("nome"));
			cidade.setNroHabitantes(rs.getInt("nro_habitantes"));
			cidade.setRendaPerCapita(rs.getFloat("renda_per_capita"));
			cidade.setCapital(rs.getBoolean("capital"));
			cidade.setEstado(rs.getString("estado"));
			cidade.setNomePrefeito(rs.getString("nome_prefeito"));
			
			cidades.add(cidade);
		}
	}
		
	public List<Cidade> listaCidades() {
		
		List<Cidade> cidades = new ArrayList<Cidade>();
		
		String sql = "select * from cidades.cidade";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			mapeiaLista(cidades, rs);
			
			
		} catch (SQLException e) {
			System.err.println("Erro ao listar cidades");
			System.err.println(e.getMessage());
		}
		
		return cidades;
	}
	
	public Cidade consultaCidade(int ddd) {
		
		String sql = "select * from cidades.cidade "
				+ "where ddd = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ddd);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				Cidade cidade = new Cidade();
				
				cidade.setDdd(rs.getInt("ddd"));
				cidade.setNome(rs.getString("nome"));
				cidade.setNroHabitantes(rs.getInt("nro_habitantes"));
				cidade.setRendaPerCapita(rs.getFloat("renda_per_capita"));
				cidade.setCapital(rs.getBoolean("capital"));
				cidade.setEstado(rs.getString("estado"));
				cidade.setNomePrefeito(rs.getString("nome_prefeito"));
				
				return cidade;
			}
			
		} catch (SQLException e) {
			System.err.println("Erro ao consultar cidade");
			System.err.println(e.getMessage());
		}
		
		
		return null; // Pesquisem sobre o Optional.
	}
	
	public int quantidadeCidadesPorEstado(String sigla) {
		
		String sql = "select count(*) as qtdCidades from cidades.cidade "
				+ "where estado = ?";
		
		try {
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sigla);
			
			ResultSet rs = stmt.executeQuery();
			rs.next();
			
			return rs.getInt(1);
			
		} catch (SQLException e) {
			System.err.println("Erro ao quantificar cidades");
			System.err.println(e.getMessage());
		}
		
		
		return 0;
	}
	
	public List<Cidade> listaCidadesPorSigla(String sigla) {
		
		List<Cidade> cidades = new ArrayList<Cidade>();
		
		String sql = "select * from cidades.cidade "
				+ "where estado = ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, sigla);
			ResultSet rs = stmt.executeQuery();
			mapeiaLista(cidades, rs);
			
		} catch (SQLException e) {
			System.err.println("Erro ao listar cidades por sigla");
			System.err.println(e.getMessage());
		}
		
		
		
		return cidades;
	}
	
	public List<Cidade> listaCidadesPorTexto(String inicio) {
		
		List<Cidade> cidades = new ArrayList<Cidade>();
		
		String sql = "select * from cidades.cidade where nome ilike ?";
		
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, inicio + "%");
			
			ResultSet rs = stmt.executeQuery();
			mapeiaLista(cidades, rs);
			
		} catch (SQLException e) {
			System.err.println("Erro ao listar cidades que começam com: " + inicio);
			System.err.println(e.getMessage());
		}
		
		
		
		
		return cidades;
	}
	
	public List<Cidade> listaFiltroCapital(boolean capital) {
		
		List<Cidade> cidades = new ArrayList<Cidade>();
		
		String sql = "select * from cidades.cidade "
				+ "where capital = ?";
		
		PreparedStatement stmt;
		try {
			stmt = conn.prepareStatement(sql);
			stmt.setBoolean(1, capital);
			
			ResultSet rs = stmt.executeQuery();
			mapeiaLista(cidades, rs);
		} catch (SQLException e) {
			System.err.println("Erro ao listar cidades pelo filtro de capital.");
			System.err.println(e.getMessage());
		}
		
		return cidades;
	}
	
	
	
	
}
