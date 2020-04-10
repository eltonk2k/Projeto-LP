package br.ufpb.vendas;

import java.util.ArrayList;
import java.util.List;

public class SistemaComercioEletronicoList implements SistemaComercioEletronico {
	private List<Cliente> clientes;
	private List<Pedido> pedidos;
	private List<Produto> produtos;

	public SistemaComercioEletronicoList() {
		this.clientes = new ArrayList<Cliente>();
		this.produtos = new ArrayList<Produto>();
		this.pedidos = new ArrayList<Pedido>();
	}

	@Override
	public boolean existeProduto(Produto produto) {
		for (Produto p : this.produtos) {
			if (p.getCodigo().equals(produto.getCodigo())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Produto pesquisaProduto(String codigoProduto)throws ProdutoNaoExisteException {
		for (Produto p : this.produtos) {
			if (p.getCodigo().equals(codigoProduto)) {
				return p;
			}
		}
		throw new ProdutoNaoExisteException("Produto não Existe!");
	}

	@Override
	public boolean cadastraProduto(Produto produto) {
		if (existeProduto(produto)) {
			return false;
		} else {
			this.produtos.add(produto);
			return true;
		}
	}

	@Override
	public boolean cadastraPedido(Pedido pedido) {
		if (existePedido(pedido)) {
			return false;
		} else {
			this.pedidos.add(pedido);
			return true;
		}
	}

	@Override
	public boolean existePedido(Pedido pedido) {
		for (Pedido p : this.pedidos) {
			if (p.getNumero().equals(pedido.getNumero())) {
				return true;
			}
		}
		return false;
	}
	
	
	@Override
	public Pedido pesquisaPedido(String numeroPedido)throws PedidoNaoExisteException{
		for (Pedido p : this.pedidos) {
			if (p.getNumero().equals(numeroPedido)) {
				return p;
			}
		}
		throw new PedidoNaoExisteException("Pedido não encontrado!");
	}
	

	@Override
	public boolean cadastraCliente(Cliente cliente) {
		if (existeCliente(cliente)) {
			return false;
		} else {
			this.clientes.add(cliente);
			return true;
		}
	}

	@Override
	public boolean existeCliente(Cliente cliente) {
		for (Cliente c : this.clientes) {
			if (c.getId().equals(cliente.getId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Cliente pesquisaCliente(String id)throws ClienteNaoExisteException {
		for (Cliente p : this.clientes) {
			if (p.getId().equals(id)) {
				return p;
			}
		}
		
		throw new ClienteNaoExisteException("Cliente não Existe!");
	}
	@Override
	public Cliente pesquisaEmail(String email)throws EmailNaoExisteException {
		for (Cliente a : this.clientes) {
			if (a.getEmail().equals(email)) {
				return a;
			}
		}
		
		throw new EmailNaoExisteException("E-mail não Existe!");		
	}
	
	@Override
	public List<Cliente> pesquisaClientesComNomeComecandoCom(String prefixo) {
		List<Cliente> clientesEncontrados = new ArrayList<>();
		for (Cliente c : clientes) {
			if (c.getNome().startsWith(prefixo)) {
				clientesEncontrados.add(c);
			}
		}

		return clientesEncontrados;
	}

	
	@Override
	public List<Pedido> pesquisaPedidosIncluindoProduto(String codigoProduto) {
		List<Pedido> procurarProdutos = new ArrayList<>();
		for (Pedido p : pedidos) {
			List<ItemDoPedido> pedidos = p.getItens();
			for (ItemDoPedido pe : pedidos) {
				Produto produto = pe.getProduto();
				if (produto.getCodigo().equals(codigoProduto)) {
					procurarProdutos.add(p);
				}
			}
		}

		return procurarProdutos;
	}
	

	public List<Cliente> getClientes() {
		return clientes;
	}

	@Override
	public void setCliente(List<Cliente> clientes) {
		this.clientes = clientes;
		
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	@Override
	public void setProduto(List<Produto> produtos) {
		this.produtos = produtos;
	}

	@Override
	public List<Pedido> getPedido() {
		return pedidos;
	}
	public void setPedido(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

}
