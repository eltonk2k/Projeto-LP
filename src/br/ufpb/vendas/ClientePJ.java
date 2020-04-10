package br.ufpb.vendas;

public class ClientePJ extends Cliente {

	private String CNPJ;
	
	public ClientePJ() {		
	}

	public ClientePJ(String nome, String endereco, String email, String CNPJ) {
		super(nome, endereco, email);
		this.CNPJ = CNPJ;
	}

	public void setCNPJ(String CNPJ) {
		this.CNPJ = CNPJ;
	}

	@Override
	public String getId() {
		return this.CNPJ;
	}

	@Override
	public void setId(String id) {
		this.CNPJ = id;
		
	}
	@Override
	public String getTipo() {
		
		return "ClientePJ";
	}
	

}
