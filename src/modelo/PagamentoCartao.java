package modelo;

public class PagamentoCartao extends Pagamento {
	private String cartao;
	private int quantidadeparcelas;
	
	public PagamentoCartao(String cartao, int quantidadeparcelas) {
		// TODO Auto-generated constructor stub
		this.cartao = cartao;
		this.quantidadeparcelas = quantidadeparcelas;
	}
	public String getCartao() {
		return cartao;
	}
	public void setCartao(String cartao) {
		this.cartao = cartao;
	}
	public int getQuantidadeparcelas() {
		return quantidadeparcelas;
	}
	public void setQuantidadeparcelas(int quantidadeparcelas) {
		this.quantidadeparcelas = quantidadeparcelas;
	}
	@Override
	public void calcularPagamento(double totalconta) {
		// TODO Auto-generated method stub
		if (quantidadeparcelas > 0 && quantidadeparcelas <= 2)
			this.setValorpago(totalconta);
		else if (quantidadeparcelas == 3)
			this.setValorpago(totalconta + (totalconta * 0.1));
		else if (quantidadeparcelas == 4)
			this.setValorpago(totalconta + (totalconta * 0.2));
	}
	@Override
	public String toString() {
		return "PagamentoCartao [cartao=" + cartao + ", quantidadeparcelas=" + quantidadeparcelas + "]";
	}
	
	
	
	
	
}
