package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.Carro;

public class CarroDAO extends DAO {
	
	public CarroDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	
	public boolean insert(Carro carro) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO carro (codigo, marca, modelo, ano) "
				       + "VALUES ("+carro.getCodigo()+ ", '" + carro.getMarca() + "', '"  
				       + carro.getModelo() + "', '" + carro.getAno() + "');";
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Carro get(int codigo) {
		Carro carro = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM produto WHERE id=" + codigo;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	 carro = new Carro(rs.getInt("codigo"), rs.getString("marca"), rs.getString("modelo"), rs.getInt("ano"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return carro;
	}
	
	
	public List<Carro> get() {
		return get("");
	}

	
	public List<Carro> getOrderByCodigo() {
		return get("codigo");		
	}
	
	
	private List<Carro> get(String orderBy) {	
	
		List<Carro> carros = new ArrayList<Carro>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM carro" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Carro u = new Carro(rs.getInt("codigo"), rs.getString("marca"), rs.getString("modelo"), rs.getInt("ano"));
	            carros.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return carros;
	}


	
	
	public boolean update(int codigo, String marca, String modelo, int ano) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE carro SET marca = '" + marca + "', modelo = '"  
				       + modelo + "', ano = '" + ano + "'"
					   + " WHERE codigo = " + codigo;
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean delete(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM carro WHERE codigo = " + codigo;
			st.executeUpdate(sql);
			st.close();
			status = true;
			System.out.println("Carro de código " + codigo + " deletado com sucesso!");
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}	
}