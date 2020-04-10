package br.ufpb.vendas;

public class EmailNaoExisteException extends Exception {

	private static final long serialVersionUID =1L;
	
	public EmailNaoExisteException(String msg){
		super(msg);
		}	
}

