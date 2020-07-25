import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestaInsercaoComParametro {
	
	public static void main(String[] args) throws SQLException {
		
		ConnectionFactory factory = new ConnectionFactory();
		try(Connection connection = factory.recuperarConexao()){
		
			connection.setAutoCommit(false);	//Assume o controle da transação e tira a responsabilidade do JDBC
			
			try (PreparedStatement stm = connection.prepareStatement(
						"INSERT INTO PRODUTO (nome, descricao)"	+ "VALUES (?, ?)", 
						Statement.RETURN_GENERATED_KEYS);
					){
				
				
				adicionarVariavel("SmartTV", "60 polegadas", stm);
				adicionarVariavel("Celular", "Iphone 11", stm);
				
				connection.commit();
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ROLLBACK EXECUTADO");
				connection.rollback();
			}
		}
		
	}

	private static void adicionarVariavel(String nome, String descricao, PreparedStatement stm) throws SQLException {
		stm.setString(1, nome);
		stm.setString(2, descricao);
		
		if (nome.equals("Celular")) {
			throw new RuntimeException("Não foi possível adicionar o produto");
		}
		
		stm.execute();
		
		try(ResultSet rst = stm.getGeneratedKeys()) {
			while(rst.next()) {
				Integer id = rst.getInt(1);
				System.out.println("O id criado foi: " + id);
			}
		}
	}

}
