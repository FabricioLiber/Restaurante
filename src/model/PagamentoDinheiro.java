package model;

public class PagamentoDinheiro extends Pagamento {
	private int percentualdesconto;

	public int getPercentualdesconto() {
		return percentualdesconto;
	}
	
	public PagamentoDinheiro (int percentualdesconto) {
		this.percentualdesconto = percentualdesconto;
	}

	public void setPercentualdesconto(int percentualdesconto) {
		this.percentualdesconto = percentualdesconto;
	}

	@Override
	public void calcularPagamento(double totalconta) {
		// TODO Auto-generated method stub
		this.setValorpago(totalconta - (totalconta * (percentualdesconto / 100)));
	}
	
	
}