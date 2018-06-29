package repository;

import java.util.ArrayList;

import model.Conta;
import model.Garcom;
import model.Mesa;
import model.Produto;

public class Restaurante {
	
	private ArrayList<Produto> produtos;
	private ArrayList<Mesa> mesas;
	private ArrayList<Conta> contas;
	private ArrayList<Garcom> garcons;
	
	public Restaurante () {
		produtos = new ArrayList<>();
		contas = new ArrayList<>();
		mesas = new ArrayList<>();
		garcons = new ArrayList<>();
	}

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}

	public ArrayList<Mesa> getMesas() {
		return mesas;
	}

	public void setMesas(ArrayList<Mesa> mesas) {
		this.mesas = mesas;
	}

	public ArrayList<Conta> getContas() {
		return contas;
	}

	public void setContas(ArrayList<Conta> contas) {
		this.contas = contas;
	}

	public ArrayList<Garcom> getGarcons() {
		return garcons;
	}

	public void setGarcons(ArrayList<Garcom> garcons) {
		this.garcons = garcons;
	}
	
	// Adicionar e Remover mesa
	public void adicionar (Mesa m) {
		this.mesas.add(m);
	}
	public void remover (Mesa m) {
		this.mesas.remove(m);
	}
	
	// Adicionar e remover produto
	public void adicionar (Produto p) {
		this.produtos.add(p);
	}
	public void remover (Produto p) {
		this.produtos.remove(p);
	}
	
	// Adicionar e remover garçom
	public void adicionar (Garcom g) {
		this.garcons.add(g);
	}
	public void remover (Garcom g) {
		this.garcons.remove(g);
	}
	
	// Adicionar e remover conta
	public void adicionar (Conta c) {
		this.contas.add(c);
	}
	public void remover (Conta c) {
		this.contas.remove(c);
	}
	
	// Metodo responsavel por encontrar mesa por ID
	public Mesa localizarMesaPorID (int id) {
		for (int i = 0; i < this.getMesas().size(); i++) {
			if (this.getMesas().get(i).getId() == id)
				return this.getMesas().get(i);
		}		
		return null;
	}
	
	public ArrayList<Produto> localizarProdutosPorNome (String nome) {
		ArrayList<Produto> produtos = new ArrayList<>();
		if (nome.isEmpty())
			return this.getProdutos();
		for (Produto p : this.getProdutos())
			if (p.getNome().contains(nome))
				produtos.add(p);
		return produtos;	
	}
	
	public Produto localizarProdutoPorNome (String nome) {
		for (Produto p : this.getProdutos())
			if (nome.equalsIgnoreCase(p.getNome()))
				return p;
		return null;			
	}
	
	public Conta localizarContaEmAberto (Mesa m) {
		for (int i = 0; i < m.getContas().size(); i ++) {
			if (m.getContas().get(i).getDtfechamento() == null)
				return m.getContas().get(i);
		}
		return null;
	}
	
	public Conta localizarUltimaConta (Mesa m) {
		return m.getContas().get(m.getContas().size() - 1);
	}
	
	public Garcom localizarGarcom (String apelido) {
		for (Garcom g : this.getGarcons())
			if (apelido.equalsIgnoreCase(g.getApelido()))
				return g;
		return null;			
	}
}
