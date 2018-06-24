package model;

import java.util.ArrayList;

public class Mesa {
	
	private int id;
	private boolean ocupada;
	private ArrayList<Conta> contas;
	private Garcom garcom;
	
	public Mesa(int id) {
		this.id = id;
		this.ocupada = false;
		this.contas = new ArrayList<>();
	}
	
	public boolean isOcupada() {
		return ocupada;
	}
	
	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;
	}

	public ArrayList<Conta> getContas() {
		return contas;
	}

	public void setContas(ArrayList<Conta> contas) {
		this.contas = contas;
	}

	public Garcom getGarcom() {
		return garcom;
	}

	public void setGarcom(Garcom garcom) {
		this.garcom = garcom;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		String contas = new String();
		for (Conta c : this.contas)
			contas += (c.getNumero() + " ");
		if (this.garcom == null)
			return "Mesa [id=" + id + ", ocupada=" + ocupada + ", contas= [" + contas +"] ]";
		else
			return "Mesa [id=" + id + ", ocupada=" + ocupada + ", contas=" + contas +", garcom="+ garcom.getApelido() + "]";
	}
	
	public void adicionar (Conta c) {
		this.contas.add(c);
		this.ocupada = true;
	}
	
	public void remover (Conta c) {
		this.contas.remove(c);
	}
	
	public void adicionar (Garcom g) {
		this.setGarcom(g);
	}
	
	
	public Conta localizarContaEmAberto () {
		if (this.ocupada)
			return this.localizarUltimaConta();
		return null;
		/*for (int i = 0; i < this.getContas().size(); i ++) {
			if (this.getContas().get(i).getDtfechamento() == null)
				return this.getContas().get(i);
		}
		return null;
		*/
	}
	
	public Conta localizarUltimaConta () {
		if (this.getContas().size() <= 0)
			return null;
		return this.getContas().get(this.getContas().size() - 1);
	}
	

}
