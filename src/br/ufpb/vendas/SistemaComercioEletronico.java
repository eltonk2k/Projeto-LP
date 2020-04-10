package br.ufpb.vendas;

import java.util.List;

public interface SistemaComercioEletronico {

	boolean existeProduto(Produto produto);

	Produto pesquisaProduto(String codigoProduto)throws ProdutoNaoExisteException;
	
	Pedido pesquisaPedido(String numeroPedido)throws PedidoNaoExisteException;

	boolean cadastraProduto(Produto produto);

	boolean cadastraPedido(Pedido pedido);

	boolean existePedido(Pedido pedido);

	boolean cadastraCliente(Cliente cliente);

	boolean existeCliente(Cliente cliente);
	
	public void setCliente(List<Cliente> clientes);
	
	public void setProduto(List<Produto> produtos);
	
	public void setPedido(List<Pedido> produtos);

	Cliente pesquisaCliente(String id) throws ClienteNaoExisteException ;

	Cliente pesquisaEmail(String email)throws EmailNaoExisteException;
	
	
	List<Cliente> pesquisaClientesComNomeComecandoCom(String prefixo);

	List<Pedido> pesquisaPedidosIncluindoProduto(String codigoProduto);

	public List<Cliente> getClientes();
	
	public List<Produto> getProdutos();

	public List<Pedido> getPedido();

}