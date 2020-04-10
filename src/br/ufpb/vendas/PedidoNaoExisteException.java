package br.ufpb.vendas;

public class PedidoNaoExisteException extends Exception {
	
	
private static final long serialVersionUID =1L;
	
	public PedidoNaoExisteException(String msg){
		super(msg);
		}
	
}
