package br.com.jdbc.modelo;

public class Categoria {
	
	private Integer id;
	private String nome;
	
	public Categoria(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public String getNome() {
		return this.nome;
	}

	public int getId() {
		return this.id;
	}
	
	

}
