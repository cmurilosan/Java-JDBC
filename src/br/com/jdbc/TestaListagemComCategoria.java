package br.com.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.jdbc.dao.CategoriaDAO;
import br.com.jdbc.modelo.Categoria;

public class TestaListagemComCategoria {

	public static void main(String[] args) throws SQLException {
		
		try(Connection connection = new ConnectionFactory().recuperarConexao()) {
			CategoriaDAO categoriaDAO = new CategoriaDAO(connection);
			List<Categoria> listDeCategorias = categoriaDAO.listar();
			listDeCategorias.stream().forEach(ct -> System.out.println(ct.getNome()));
		}

	}

}
