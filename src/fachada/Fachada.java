/**IFPB - Curso SI - Disciplina de POO
 * @author Fabricio Liberato
 */

package fachada;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.TreeMap;

import modelo.Conta;
import modelo.Garcom;
import modelo.Mesa;
import modelo.Pagamento;
import modelo.PagamentoCartao;
import modelo.PagamentoDinheiro;
import modelo.Produto;
import repositorio.Restaurante;

public class Fachada {
	
	private static Restaurante restaurante = new Restaurante();
	
	/*
	 Metodo que retorna  todos  os  produtos  do  restaurante  (no  caso  de  Enter) 
	 ou  apenas  os  produtos  que  contam  parte  do  nome  fornecido.
	 */
	public static ArrayList<Produto> listarProdutos (String nome) throws Exception {
		if (restaurante.getProdutos().isEmpty())
			return null;
		ArrayList<Produto> produtos = restaurante.localizarProdutosPorNome(nome);
		if (produtos.isEmpty())
			throw new Exception("Nenhum produto encontrado com essa combinacao de caracteres (\""+ nome+"\")!");
		produtos.sort (new Comparator<Produto>() {
			public int compare(Produto produto, Produto outroProduto) {
		        return produto.getNome().compareTo(outroProduto.getNome());
		    }
		});
		return produtos;
	}
	
	// Metodo que retorna  todos  os  garcons  do  restaurante.
	public static TreeMap<String, Garcom> listarGarcons () throws Exception {
		if (restaurante.getGarcons() != null)
			return  restaurante.getGarcons();
		return null;
	}

	// Metodo que retorna  todas  as  mesas  do  restaurante.
	public static ArrayList<Mesa> listarMesas () throws Exception {
		if (restaurante.getMesas() != null)
			return restaurante.getMesas();
		return null;
	}
	
	// Metodo que retorna  todas  as  contas  do  restaurante.
	public static ArrayList<Conta> listarContas () throws Exception {
		if (restaurante.getContas().isEmpty())
			return null;
		return restaurante.getContas();			
	}
	
	// Metodo responsavel por criar todas as N mesas do restaurante  
	public static void criarMesas (int qtd) throws Exception {
		if (qtd <= 0)
			throw new Exception("Valor negativo para quantidade de Mesas invalido!");
		for (int i = 1; i <= qtd; ++i)
			restaurante.adicionar(new Mesa(geraId("Mesa")));
	}
	
	// Metodo responsavel por cadastrar o produto 
	public static Produto cadastrarProduto (String nome, double preco) throws Exception {
		Produto p = restaurante.localizarProdutoPorNome(nome);
		if (p != null)
			throw new Exception("Produto ja cadastrado!");
		if (nome.isEmpty() || preco <= 0)
			throw new Exception("Informacoes de cadastro de produto invalidas!");
		p = new Produto(nome, preco);
		restaurante.adicionar(p);
		return p;			
	}
	
	// Metodo responsavel por cadastrar garcom
	public static Garcom cadastrarGarcom (String nome, int mesainicial, int mesafinal) throws Exception {
		Garcom g = restaurante.localizarGarcom(nome);
		if (g != null)
			throw new Exception("Garcom ja cadastrado!");
		ArrayList<Mesa> mesas = new ArrayList<>();
		g = new Garcom(nome, mesas);
		if ((mesafinal - mesainicial + 1) == 5) {
			for (int i = mesainicial; i <= mesafinal; i++) {
				Mesa m = restaurante.localizarMesaPorID(i);
				if (m == null)
					throw new Exception("Numero de mesa nao encontrado!");
				else if (m.getGarcom() != null)
					throw new Exception("Mesa ja possui garcom cadastrado!");
				mesas.add(m);
			}
			for (Mesa m : mesas)
				m.adicionar(g);
			restaurante.adicionar(g);
			return g;
		} else
			throw new Exception("E necessario informar o intervalo referente a 5 mesas!");
	}
	
	// Metodo responsavel por criar  uma  conta  para  a  mesa   
	public static Conta criarConta (int idmesa) throws Exception {
		Mesa m = restaurante.localizarMesaPorID(idmesa);
		Conta c;
		if (m == null)
			throw new Exception("Mesa invalida!");
		if (m.localizarContaEmAberto() == null) {
			c = new Conta(geraId("Conta"), m);	
			restaurante.adicionar(c);
			m.adicionar(c);
			return c;
		}
		else {
			if (m.localizarUltimaConta().getPagamento() == null)
				throw new Exception("Conta anterior ainda nao foi paga!");
			throw new Exception("Mesa ja esta ocupada!");
		}
			
	}
	
	// Metodo utilizado para retornar a conta da mesa 
	public static Conta consultarConta (int idmesa) throws Exception {
		Mesa m = restaurante.localizarMesaPorID(idmesa);
		Conta c = m.localizarUltimaConta();
		if (c != null)
			return c;
		else
			throw new Exception("Nao existe conta na mesa indicada!");
	}
	
	// Metodo que adiciona um produto na  conta
	public static void solicitarProduto (int idmesa, String nomeproduto) throws Exception {
		Produto p = restaurante.localizarProdutoPorNome(nomeproduto);
		Mesa m = restaurante.localizarMesaPorID(idmesa);
		if (p == null)
			throw new Exception("Produto nao localizado!");
		if (m == null)
			throw new Exception("Mesa nao localizada!");
		Conta c = m.localizarContaEmAberto();
		if (c == null)
			throw new Exception("Mesa nao possui conta em aberto!");
		c.adicionar(p);
	}
	
	// Metodo que exclui a conta da mesa e do restaurante
	public static void cancelarConta (int idmesa) throws Exception {
		Mesa m = restaurante.localizarMesaPorID(idmesa);
		Conta c = m.localizarContaEmAberto();
		if (c != null) {
			restaurante.remover(c);
			m.remover(c);
		}
		else 
			throw new Exception("Mesa nao possui conta em aberto!");
	}
	
	/* Metodo que transfere todos os produtos da conta de
	origem para a conta destino e cancela a conta origem. 
	*/
	public static void transferirConta (int idmesaorigem, int idmesadestino) throws Exception {
		Mesa mesaOrigem = restaurante.localizarMesaPorID(idmesaorigem);
		Conta contaOrigem = mesaOrigem.localizarContaEmAberto();
		Mesa mesaDestino = restaurante.localizarMesaPorID(idmesadestino);
		Conta contaDestino = mesaDestino.localizarContaEmAberto();
		if (mesaOrigem.getGarcom().equals(mesaDestino.getGarcom())) {
			if (contaOrigem == null)
				throw new Exception("Conta de Origem nao esta em aberto!");
			if (contaDestino == null)
				throw new Exception("Conta de Destino nao esta em aberto!");
			for (int i = 0; i < contaOrigem.getProdutos().size(); i++) {
				contaDestino.adicionar(contaOrigem.getProdutos().get(i));
				contaOrigem.remover(contaOrigem.getProdutos().get(i));
				i--;
			}				
			cancelarConta(idmesaorigem);
		} else
			throw new Exception("Nao e possivel transferir para mesa de outro Garcom!");
	}
	
	// Metodo que atualiza  a  data  de  fechamento  da  conta (Atualizar total)
	public static void fecharConta (int idmesa) throws Exception {
		Mesa m = restaurante.localizarMesaPorID(idmesa);
		Conta c = m.localizarContaEmAberto();
		
		SimpleDateFormat df;
		df = new SimpleDateFormat("dd/MM/yyyy");
		String dataFechamento = df.format(new Date());
		if (c != null) {
			c.setDtfechamento(dataFechamento);
			m.setOcupada(false);
		} else
			throw new Exception("Mesa nao possui conta em aberto!");
	}
	
	// Metodo que calcula a gorjeta do garcom
	public static double calcularGorjeta (String apelido) throws Exception {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String dataFechamento = df.format(new Date());
		int totalGorjeta = 0;
		Garcom g = restaurante.localizarGarcom(apelido);
		if (g != null) {
			for (Mesa m : g.getMesas()) {
				if (!m.getContas().isEmpty())					
					for (Conta c : m.getContas())
						if (c.getDtfechamento() != null)
							if (c.getDtfechamento().equals(dataFechamento))
								if (c.getPagamento() != null)
									totalGorjeta += c.getPagamento().calcularGorjeta();				
			}
			return totalGorjeta;
		} else
			throw new Exception("Garcom nao encontrado!");
			
		
	} 
	// Criar  pagamento  para  a  conta  da  mesa,  onde  tipo  pode  ser  dinheiro  ou  cartao.
	public  static Pagamento pagarConta (int idmesa, String tipo, int percentual, String cartao, int quantidade) throws Exception {
		if (quantidade < 0 || quantidade > 4)
			throw new Exception ("Quantidade de parcelas invalida!");
		Mesa m = restaurante.localizarMesaPorID(idmesa);
		if (m == null)
			throw new Exception ("Nao existe a mesa indicada!");
		Conta c = m.localizarUltimaConta();
		if (c == null)
			throw new Exception ("Nao existe conta a mesa indicada!");
		if (c.getDtfechamento() == null)
			throw new Exception ("Precisa fechar a conta para fazer o pagamento!");
		if(c.getTotal()/quantidade < 100 && quantidade > 1)
			throw new Exception ("Valor de parcela abaixo de 100 reais nao permitido!");
		if (c.getPagamento() != null)
			throw new Exception ("Todas as contas da mesa "+ idmesa +" foram pagas!");
		Pagamento p;
		if (tipo.equalsIgnoreCase("dinheiro")) {
			p = new PagamentoDinheiro(percentual);
		} else if (tipo.equalsIgnoreCase("cartao")) {
			p = new PagamentoCartao(cartao, quantidade);
		} else
			throw new Exception ("Forma de pagamento invalida!");
		p.calcularPagamento(c.getTotal());
		c.setPagamento(p);
		return p;
	}
	
	// Exclui  o  garcom  do  restaurante.   
	public  static  void excluirGarcom (String nome) throws Exception {
		Garcom g = restaurante.getGarcons().get(nome);
		if (g == null)
			throw new Exception("Garcom nao localizado!");
		for (Mesa m : g.getMesas())
			if (m.localizarContaEmAberto() != null)
				throw new Exception("Garcom possui mesa com conta em aberto");
		for (Mesa m : g.getMesas())
			m.setGarcom(null);		
		restaurante.remover(g);
	}
	/* Retorna  o  percentual  medio  aplicado  aos    pagamentos
    em  dinheiro  das  contas  das  mesas  do  garcom.*/
	public  static  double  calcularPercentualMedio (String apelido) throws Exception {
		int quantidadeDescontoPagamentoDinheiro = 0, totalDescontoPagamentoDinheiro = 0;
		Garcom g = restaurante.localizarGarcom(apelido);
		if (g == null)
			throw new Exception("Garcom nao localizado!");
		for (Mesa m : g.getMesas())
			for (Conta c : m.getContas())
				if (c.getPagamento() instanceof PagamentoDinheiro) {
					PagamentoDinheiro p = (PagamentoDinheiro) c.getPagamento();
					quantidadeDescontoPagamentoDinheiro ++;
					totalDescontoPagamentoDinheiro += p.getPercentualdesconto();					
				}
		return totalDescontoPagamentoDinheiro / quantidadeDescontoPagamentoDinheiro;
	}
	
	// Variavel utilizada como controlador da geracao de ID
	private static int idConta;	
	private static int idMesa;
	
	// Funcao responsavel por gerar ID's sem duplicacao
	public static int geraId (String nomeClasse) {
		if (nomeClasse.equals("Mesa"))
			return ++idMesa;
		else if (nomeClasse.equals("Conta"))
			return ++idConta;
		return 0;
	}
	
	public static boolean autenticarGarcom (String nome, int idmesa) throws Exception {
		Mesa m = restaurante.localizarMesaPorID(idmesa);
		if (m == null)
			throw new Exception("Mesa nao localizada!");
		else if (m.getGarcom() == null)
			throw new Exception("Mesa nao possui garcom responsavel!");
		else if (m.getGarcom().getApelido().equalsIgnoreCase(nome))
			return true;
		else
			return false;
	}
}
