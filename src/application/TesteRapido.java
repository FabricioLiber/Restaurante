/**IFPB - Curso SI - Disciplina de POO
 * @author Fabricio Liberato
 */
package application;
import java.util.ArrayList;

import facade.Facade;
import model.Produto;
import model.Garcom;
import model.Mesa;
import model.Conta;

public class TesteRapido {
	
	public static void main (String[] args) {
		parte1();
		parte2();
		System.out.println("fim do teste");
	}

	public static void parte1(){
		try {	
			Produto p;
 			p = Facade.cadastrarProduto("feijoada", 25.0);
 			p = Facade.cadastrarProduto("bode guisado", 20.0);
 			p = Facade.cadastrarProduto("galinhada", 15.0);
 			p = Facade.cadastrarProduto("cerveja", 6.0);
 			p = Facade.cadastrarProduto("refrigerante", 5.0);
 			p = Facade.cadastrarProduto("agua", 2.0);
			ArrayList<Produto> produtos = Facade.listarProdutos("");
			System.out.println("produtos cadastrados:");
			System.out.println(produtos);

			Facade.criarMesas(20);		// 20 mesas
			ArrayList<Mesa> mesas = Facade.listarMesas();
			System.out.println("mesas criadas:");
			System.out.println(mesas);
			
			Garcom g;
			g = Facade.cadastrarGarcom("baixinho", 1,5);
			g = Facade.cadastrarGarcom("esperto", 6,10);
			g = Facade.cadastrarGarcom("zezinho", 11,15);
			g = Facade.cadastrarGarcom("zezinho", 16,20);
			ArrayList<Garcom> garcons = Facade.listarGarcons();
			System.out.println("garcons cadastrados:");
			System.out.println(garcons);
		}catch (Exception e) {
			System.out.println(e.getMessage());
			//e.printStackTrace();
		}
	}


	public static void parte2() {
		try {
			Facade.criarConta(1);	//mesa 1
			Facade.solicitarProduto(1, "galinhada");
			Facade.solicitarProduto(1, "cerveja");
			Facade.solicitarProduto(1, "refrigerante");
			System.out.println("conta da mesa 1: \n"+ Facade.consultarConta(1)); 
			Facade.solicitarProduto(1, "cerveja");
			Facade.solicitarProduto(1, "cerveja");
			Facade.fecharConta(1);			
			System.out.println("conta da mesa 1: \n"+ Facade.consultarConta(1)); 


			Facade.criarConta(5);	//mesa 5
			Facade.solicitarProduto(5, "feijoada");
			Facade.solicitarProduto(5, "cerveja");
			
			Facade.fecharConta(5);
			System.out.println("conta da mesa 5: \n"+ Facade.consultarConta(5)); 


			double gorjeta = Facade.calcularGorjeta("baixinho");
			System.out.println("gorjeta do baixinho="+gorjeta);

			ArrayList<Conta> contas = Facade.listarContas();
			System.out.println("contas existentes:");
			System.out.println(contas);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	


}
