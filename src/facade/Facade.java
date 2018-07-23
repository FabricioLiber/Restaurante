/**IFPB - Curso SI - Disciplina de POO
 * @author Fabricio Liberato
 */

package facade;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import model.Conta;
import model.Garcom;
import model.Mesa;
import model.Pagamento;
import model.PagamentoCartao;
import model.PagamentoDinheiro;
import model.Produto;
import repository.Restaurante;

public class Facade {
	
	private static Restaurante restaurante = new Restaurante();
	
	/*
	 Método que retorna  todos  os  produtos  do  restaurante  (no  caso  de  Enter) 
	 ou  apenas  os  produtos  que  contém  parte  do  nome  fornecido.
	 */
	public static ArrayList<Produto> listarProdutos (String nome) throws Exception {
		if (restaurante.getProdutos().isEmpty())
			throw new Exception("Nenhum produto cadastrado!");
		ArrayList<Produto> produtos = restaurante.localizarProdutosPorNome(nome);
		if (produtos.isEmpty())
			throw new Exception("Nenhum produto encontrado com essa combinacao de caracteres (\""+ nome+"\")!");
		else
			return produtos;	
	}
	
	// Método que retorna  todos  os  garçons  do  restaurante.
	public static ArrayList<Garcom> listarGarcons () throws Exception {
		ArrayList <Garcom> garcons = new ArrayList<>();
		if (restaurante.getGarcons() != null) {
			for (Garcom g : restaurante.getGarcons().values())
				garcons.add(g);
			return garcons;
		} else
			return null;
	}
	
	// Método que retorna  todas  as  mesas  do  restaurante.
	public static ArrayList<Mesa> listarMesas () throws Exception {
		if (restaurante.getMesas() != null)
			return restaurante.getMesas();
		else
			throw new Exception("Nenhuma Mesa cadastrada!");
	}
	
	// Método que retorna  todas  as  contas  do  restaurante.
	public static ArrayList<Conta> listarContas () throws Exception {
		if (restaurante.getContas().isEmpty())
			throw new Exception("Nenhuma Conta cadastrada!");
		return restaurante.getContas();			
	}
	
	// Método responsável por criar todas as N mesas do restaurante  
	public static void criarMesas (int qtd) throws Exception {
		if (qtd <= 0)
			throw new Exception("Valor negativo para quantidade de Mesas invalido!");
		for (int i = 1; i <= qtd; ++i)
			restaurante.adicionar(new Mesa(geraIdMesa()));
	}
	
	// Método responsável por cadastrar o produto 
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
	
	// Método responsável por cadastrar garçom
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
	
	// Método responsável por criar  uma  conta  para  a  mesa   
	public static Conta criarConta (int idmesa) throws Exception {
		Mesa m = restaurante.localizarMesaPorID(idmesa);
		Conta c;
		if (m == null)
			throw new Exception("Mesa invalida!");
		if (m.localizarContaEmAberto() == null) {
			c = new Conta(geraIdConta(), m);	
			restaurante.adicionar(c);
			m.adicionar(c);
			return c;
		}
		else
			throw new Exception("Mesa ja esta ocupada!");
	}
	
	// Método utilizado para retornar a conta da mesa 
	public static Conta consultarConta (int idmesa) throws Exception {
		Mesa m = restaurante.localizarMesaPorID(idmesa);
		Conta c = m.localizarUltimaConta();
		if (c != null)
			return c;
		else
			throw new Exception("Não existe conta na mesa indicada!");
	}
	
	// Método que adiciona um produto na  conta
	public static void solicitarProduto (int idmesa, String nomeproduto) throws Exception {
		Produto p = restaurante.localizarProdutoPorNome(nomeproduto);
		Mesa m = restaurante.localizarMesaPorID(idmesa);
		if (p == null)
			throw new Exception("Produto nao localizado!");
		if (m == null)
			throw new Exception("Mesa nao localizada!");
		Conta c = m.localizarContaEmAberto();
		if (c == null)
			c = criarConta(idmesa);
		c.adicionar(p);
	}
	
	// Método que exclui a conta da mesa e do restaurante
	public static void cancelarConta (int idmesa) throws Exception {
		Mesa m = restaurante.localizarMesaPorID(idmesa);
		Conta c = m.localizarContaEmAberto();
		if (c != null) {
			restaurante.remover(c);
			m.remover(c);
		}
		else 
			throw new Exception("Mesa não possui conta em aberto!");
	}
	
	/* Método que transfere todos os produtos da conta de
	origem para a conta destino e cancela a conta origem. 
	*/
	public static void transferirConta (int idmesaorigem, int idmesadestino) throws Exception {
		Mesa mesaOrigem = restaurante.localizarMesaPorID(idmesaorigem);
		Conta contaOrigem = mesaOrigem.localizarContaEmAberto();
		Mesa mesaDestino = restaurante.localizarMesaPorID(idmesadestino);
		Conta contaDestino = mesaDestino.localizarContaEmAberto();
		if (mesaOrigem.getGarcom().equals(mesaDestino.getGarcom())) {
			if (contaOrigem == null)
				throw new Exception("Conta de Origem não está em aberto!");
			if (contaDestino == null)
				throw new Exception("Conta de Destino não está em aberto!");
			for (int i = 0; i < contaOrigem.getProdutos().size(); i++) {
				contaDestino.adicionar(contaOrigem.getProdutos().get(i));
				contaOrigem.remover(contaOrigem.getProdutos().get(i));
				i--;
			}				
			cancelarConta(idmesaorigem);
		} else
			throw new Exception("Não e possivel transferir para mesa de outro Garcom!");
	}
	
	// Método que atualiza  a  data  de  fechamento  da  conta (Atualizar total)
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
			throw new Exception("Mesa não possui conta em aberto!");
	}
	
	// Método que calcula a gorjeta do garçom
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
								totalGorjeta += c.getTotal();				
			}
			return totalGorjeta * 0.10;
		} else
			throw new Exception("Garcom nao encontrado!");
			
		
	} 
	// Criar  pagamento  para  a  conta  da  mesa,  onde  tipo  pode  ser  “dinheiro”  ou  “cartao”.
	public  static Pagamento pagarConta (int idmesa, String tipo, double percentual, String cartão, int quantidade) throws Exception {
		Mesa m = restaurante.localizarMesaPorID(idmesa);
		if (m == null)
			throw new Exception ("Não existe conta na mesa!");
		Pagamento p;
		if (tipo.equalsIgnoreCase("Dinheiro")) {
			p = new PagamentoDinheiro(2);
		} else if (tipo.equalsIgnoreCase("Cartão")) {
			p = new PagamentoCartao("2", 2);
		} else
			throw new Exception ("Forma de pagamento inválida!");
		return p;
	}
	
	// Exclui  o  garçom  do  restaurante.   
	public  static  void  excluirGarcom (String nome) throws Exception {
		// TODO
		Garcom g = restaurante.getGarcons().get(nome);
		if (g == null)
			throw new Exception("Garcom não localizado!");
		for (Mesa m : g.getMesas())
			m.setGarcom(null);
		restaurante.remover(g);
	}
	/* Retorna  o  percentual  médio  aplicado  aos    pagamentos
    em  dinheiro  das  contas  das  mesas  do  garcom.*/
	public  static  double  calcularPercentualMedio (String apelido) {
		return 0;
	}
	
	// Variavel utilizada como controlador da geracao de ID
	private static int idConta;	
	private static int idMesa;
	
	// Funcao responsavel por gerar ID's sem duplicacao
	public static int geraIdConta () {		
		return ++idConta;		
	}
	public static int geraIdMesa () {		
		return ++idMesa;		
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
