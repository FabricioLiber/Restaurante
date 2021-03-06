/**IFPB - Curso SI - Disciplina de POO
 * @author Fabr�cio Liberato
 */

package modelo;

import java.util.ArrayList;

public class Conta {

	private int numero;
	private String dtfechamento;
	private double total;
	private Mesa mesa;
	private ArrayList<Produto> produtos;
	private Pagamento pagamento;
	
	public Conta(int numero, Mesa mesa) {
		this.numero = numero;
		this.mesa = mesa;
		this.produtos = new ArrayList<>();
	}

	

	@Override
	public String toString() {
		return "Conta [numero=" + numero + ", dtfechamento=" + dtfechamento + ", total=" + total + ", mesa=" + mesa
				+ ", produtos=" + produtos + ", pagamento=" + pagamento + "]";
	}



	public String getDtfechamento() {
		return dtfechamento;
	}
	
	public void setDtfechamento(String dtfechamento) {
		this.dtfechamento = dtfechamento;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}

	public int getNumero() {
		return numero;
	}

	public double getTotal() {
		return total;
	}
	
	public Pagamento getPagamento() {
		return pagamento;
	}

	public void setPagamento(Pagamento pagamento) {
		this.pagamento = pagamento;
	}

	public void adicionar (Produto p) {
		this.produtos.add(p);
		this.total += p.getPreco();
	}
	
	public void remover (Produto p) {
		this.produtos.remove(p);
	}
	
	public void adicionar (ArrayList<Produto> produtos) {
		this.produtos.addAll(produtos);
		for (Produto p: produtos)
			this.total += p.getPreco();
	}
	
		
	
	
}
