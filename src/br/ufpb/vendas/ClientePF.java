package br.ufpb.vendas;

public class ClientePF extends Cliente {
	private String CPF;
	
	public ClientePF() {		
	}

	public ClientePF(String nome, String endereco, String email, String CPF) {
		super(nome, endereco, email);
		this.CPF = CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	@Override
	public String getId() {
		return this.CPF;
	}

	@Override
	public void setId(String id) {
		CPF = id;	
	}
	
	@Override
	public String getTipo() {
		
		return "ClientePF";
	}
	
}
