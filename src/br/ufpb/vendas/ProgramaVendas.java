package br.ufpb.vendas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ProgramaVendas {

	public static void main(String[] args) throws ClienteNaoExisteException, ProdutoNaoExisteException {
		SistemaComercioEletronico sistema = new SistemaComercioEletronicoList();

		try {
			recuperarDados(sistema);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Não foi possível ler os arquivos");
		}

		boolean sair = false;
		while (!sair) {
			int opcao = Integer.parseInt(JOptionPane.showInputDialog("Digite uma opção:\n1.Cadastrar cliente\n"
					+ "2.Cadastrar produto\n" 
					+ "3.Pesquisar cliente pelo ID\n" 
					+ "4.Pesquisar clientes pelo nome\n"
					+ "5.Pesquisar pedidos com produto\n" 
					+ "6.Cadastrar pedido\n"   
					+ "7.Pesquisar produto\n" 
					+ "8.Pesquisar pedido\n" 
					+ "9.Pesquisar E-mail do Cliente\n"
					+ "10.Gravar DADOS\n" 
					+ "11.Sair\n"));
			switch (opcao) {
			case 1:
				String nome = JOptionPane.showInputDialog("Qual o nome do cliente?");
				String endereco = JOptionPane.showInputDialog("Qual o endereco do cliente?");
				String email = JOptionPane.showInputDialog("Qual o email do cliente?");
				String tipo = JOptionPane
						.showInputDialog("Qual o tipo do cliente?1. Pessoa Física ou 2.Pessoa Jurídica");
				Cliente cliente = null;
				if (tipo.equals("1")) {
					String cpf = JOptionPane.showInputDialog("Qual o CPF do cliente?");
					cliente = new ClientePF(nome, endereco, email, cpf);
				} else if (tipo.equals("2")) {
					String cnpj = JOptionPane.showInputDialog("Qual o CNPJ do cliente?");
					cliente = new ClientePJ(nome, endereco, email, cnpj);
				} else {
					JOptionPane.showMessageDialog(null, "Opção invalida");
				}
				if (cliente != null) {
					boolean cadastrou = sistema.cadastraCliente(cliente);
					if (cadastrou) {
						JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso");
					} else {
						JOptionPane.showMessageDialog(null, "Cliente já estava cadastrado");
					}
				}
				break;
			case 2:

				String codigo = JOptionPane.showInputDialog("Informe o codigo: ");
				String descricao = JOptionPane.showInputDialog("Informe a descrição: ");
				double precoVenda = Double.parseDouble(JOptionPane.showInputDialog("Informe o preço: "));
				int quantidadeEmEstoque = Integer
						.parseInt(JOptionPane.showInputDialog("Informe a quantidade disponível: "));
				String categoria = JOptionPane.showInputDialog("Informe a categoria:");

				Produto p1 = new Produto(codigo, descricao, precoVenda, quantidadeEmEstoque, categoria);
				sistema.cadastraProduto(p1);
				break;
				
			case 3:
				String id = JOptionPane.showInputDialog("Informe o ID: ");
				try {
					Cliente clienteEncontrado = sistema.pesquisaCliente(id);
					JOptionPane.showMessageDialog(null, clienteEncontrado.toString());
				} catch (ClienteNaoExisteException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				break;
			case 4:
				String nome1 = JOptionPane.showInputDialog("Informe o nome do cliente que deseja encontrar: ");

				List<Cliente> clientesPrefixo = sistema.pesquisaClientesComNomeComecandoCom(nome1);

				for (Cliente p : clientesPrefixo) {
					if (p.getNome().equals(nome1)) {
						JOptionPane.showMessageDialog(null, p.toString());
					}
				}
				break;
			case 5:
				String codigoPesq = JOptionPane.showInputDialog("Qual o codigo do produto a pesquisar?");
				List<Pedido> pedidosAchados = sistema.pesquisaPedidosIncluindoProduto(codigoPesq);
				JOptionPane.showMessageDialog(null, "PEDIDOS ACHADOS:");
				for (Pedido p : pedidosAchados) {
					JOptionPane.showMessageDialog(null, p.toString());
				}
				break;
			case 6:
				String idCliente = JOptionPane.showInputDialog("Qual o ID do cliente (e.g. CNPJ/CPF?");
				Cliente clientePedido = sistema.pesquisaCliente(idCliente);
				String dataPedido = JOptionPane.showInputDialog("Qual a data do pedido?");
				String numero = JOptionPane.showInputDialog("Qual o numero do pedido?");
				String formaPagamento = JOptionPane
						.showInputDialog("Qual a forma de pagamento?1. Boleto ou 2.Cartão de crédito");
				String formaPagamentoStr = "";
				if (formaPagamento.equals("1")) {
					formaPagamentoStr = Pedido.PAGAMENTO_BOLETO;
				} else if (formaPagamento.equals("2")) {
					formaPagamentoStr = Pedido.PAGAMENTO_CARTAO;
				}
				int quantItens = Integer.parseInt(JOptionPane.showInputDialog("Quantos tipos de itens há no pedido?"));
				List<ItemDoPedido> itens = new ArrayList<ItemDoPedido>();
				for (int k = 0; k < quantItens; k++) {
					String codigoProd = JOptionPane.showInputDialog("Qual o código do produto?");
					int quantidade = Integer
							.parseInt(JOptionPane.showInputDialog("Qual a quantidade de itens deste produto?"));
					ItemDoPedido item = new ItemDoPedido(quantidade, sistema.pesquisaProduto(codigoProd));
					itens.add(item);
				}
				Pedido ped = new Pedido(dataPedido, Pedido.STATUS_REALIZADO, numero, formaPagamentoStr, itens,
						clientePedido);
				sistema.cadastraPedido(ped);
				break;			
			case 7:
				String pesqProduto = JOptionPane.showInputDialog("Informe o codigo do PRODUTO: ");

				try {
					Produto produtoEncontrado = sistema.pesquisaProduto(pesqProduto);
					JOptionPane.showMessageDialog(null, produtoEncontrado.toString());

				} catch (ProdutoNaoExisteException e2) {

					JOptionPane.showMessageDialog(null, e2.getMessage());
				}				
				break;						
			case 8:	
				String pesqPedido = JOptionPane.showInputDialog("Informe o número do PEDIDO: ");

				try {
					Pedido pedidoEncontrado = sistema.pesquisaPedido(pesqPedido);
					JOptionPane.showMessageDialog(null, pedidoEncontrado.toString());

				} catch (PedidoNaoExisteException e2) {

					JOptionPane.showMessageDialog(null, e2.getMessage());
				}				
				break;				
			case 9:
				
				String pesqEmail = JOptionPane.showInputDialog("Informe o E-mail a Pesquisar: ");

				try {
					Cliente emailEncontrado = sistema.pesquisaEmail(pesqEmail);
					JOptionPane.showMessageDialog(null, emailEncontrado.toString());

				} catch (EmailNaoExisteException e3) {

					JOptionPane.showMessageDialog(null, e3.getMessage());
				}				
				break;			
			
			case 10:
				try {
					gravarDados(sistema);
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Erro ao gravar dados");
					e.printStackTrace();
				}
				break;	
			case 11:
				sair = true;
				break;
			}
		}
		JOptionPane.showMessageDialog(null, "FIM DO PROGRAMA");

	}// MAIN FIM

	public static void gravarDados(SistemaComercioEletronico sistema) throws IOException {

		GravadorDeDados gravador = new GravadorDeDados();

		List<Produto> produtos = sistema.getProdutos();
		List<Cliente> clientes = sistema.getClientes();
		List<Pedido> pedidos = sistema.getPedido();

		List<String> linhasProdutos = new ArrayList<String>();

		String linhaProduto;

		for (Produto p : produtos) {

			linhaProduto = p.getCodigo() + "#" + p.getDescricao() + "#" + p.getPrecoVenda() + "#"
					+ p.getQuantidadeEmEstoque() + "#" + p.getCategoria();
			linhasProdutos.add(linhaProduto);
		}

		gravador.gravaTextoEmArquivo(linhasProdutos, "produtos.txt");
		List<String> linhas = new ArrayList<String>();

		String linha;

		for (Cliente c : clientes) {

			if (c instanceof ClientePF) {

				linha = "ClientPF" + "#" + c.getNome() + "#" + c.getEndereco()+ "#" + c.getEmail() + "#" + c.getId();
			} else {
				linha = "ClientPJ" + "#" + c.getNome() + "#"+c.getEndereco()+ "#" + c.getEmail() + "#" + c.getId();
			}

			linhas.add(linha);
		}
		gravador.gravaTextoEmArquivo(linhas, "usuarios.txt");

		List<String> linhasPedidos = new ArrayList<String>();

		String linhaPedidos;

		for (Pedido pedido : pedidos) {

			String linhaitensDePedido = "";

			List<ItemDoPedido> pedidosDeitens = pedido.getItens();

			for (ItemDoPedido item : pedidosDeitens) {

				String codig = item.getProduto().getCodigo();
				String descricao = item.getProduto().getDescricao();
				double precoVenda = item.getProduto().getPrecoVenda();
				int quantidadeEmEstoque = item.getProduto().getQuantidadeEmEstoque();
				String categoria = item.getProduto().getCategoria();

				linhaitensDePedido += item.getQuantidade() + "#" + codig + "#" + descricao + "#" + precoVenda + "#"
						+ quantidadeEmEstoque + "#" + categoria+ "#";
			}

			linhaPedidos = pedido.getData() + "#" + pedido.getStatus() + "#" + pedido.getNumero() + "#"
					+ pedido.getFormaPagamento() + "#" + pedido.getCliente().getTipo() + "#"
					+ pedido.getCliente().getNome() + "#" + pedido.getCliente().getEndereco() + "#"
					+ pedido.getCliente().getEmail() + "#" + pedido.getCliente().getId() + "#" + linhaitensDePedido;

			linhasPedidos.add(linhaPedidos);
		}

		gravador.gravaTextoEmArquivo(linhasPedidos, "pedidos.txt");

	}

	public static void recuperarDados(SistemaComercioEletronico sistema) throws IOException {

		GravadorDeDados gravador = new GravadorDeDados();

		List<Cliente> clientes = new ArrayList<>();
		List<Pedido> pedidos = new ArrayList<>();
		List<Produto> produtos = new ArrayList<>();

		List<String> linhasProdutos = gravador.recuperaTextoEmArquivo("produtos.txt");

		for (String linha : linhasProdutos) {

			String[] dados = linha.split("#");
			produtos.add(new Produto(dados[0], dados[1], Double.parseDouble(dados[2]), Integer.parseInt(dados[3]),
					dados[4]));

		}
		sistema.setProduto(produtos);
		List<String> linhas = gravador.recuperaTextoEmArquivo("usuarios.txt");

		for (String linha : linhas) {

			String[] dados = linha.split("#");

			if (dados[0].equals("ClientPF")) {

				Cliente u = new ClientePF();

				u.setNome(dados[1]);
				u.setEndereco(dados[2]);
				u.setEmail(dados[3]);
				u.setId(dados[4]);

				clientes.add(u);
			} else {
				Cliente u = new ClientePJ();

				u.setNome(dados[1]);
				u.setEndereco(dados[2]);
				u.setEmail(dados[3]);
				u.setId(dados[4]);

				clientes.add(u);
			}

		}
		sistema.setCliente(clientes);

		List<String> linhasPedidos = gravador.recuperaTextoEmArquivo("pedidos.txt");

		for (String linha : linhasPedidos) {

			String[] dados = linha.split("#");
			
			Cliente clientet;
			if (dados[4].equals("ClientePF")) {
				clientet = new ClientePF(dados[5], dados[6], dados[7], dados[8]);
			} else {
				clientet = new ClientePJ(dados[5], dados[6], dados[7], dados[8]);
			}

			List<ItemDoPedido> listadeItensDoPedido = new ArrayList<ItemDoPedido>();
			for (int x = 9; x < dados.length - 5; x+=6) {
				Produto produto = new Produto(dados[x + 1], dados[x + 2], Double.parseDouble(dados[x + 3]),
						Integer.parseInt(dados[x + 4]), dados[x + 5]);
				listadeItensDoPedido.add(new ItemDoPedido(Integer.parseInt(dados[x]), produto));
			}
			Pedido p = new Pedido(dados[0], dados[1], dados[2], dados[3], listadeItensDoPedido, clientet);
			pedidos.add(p);

		}
		sistema.setPedido(pedidos);
	}

}
