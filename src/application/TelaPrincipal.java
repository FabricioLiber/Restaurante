/**IFPB - Curso SI - Disciplina de POO
 * @author Fabricio Liberato
 */
package application;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import facade.Facade;
import model.Garcom;
import model.Produto;

public class TelaPrincipal {

	private JFrame frmPrincipal;
	private JMenuItem mntmCadastrar;
	private JMenuItem mntmListar;
	private JMenu mnProduto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {	
					TelaPrincipal window = new TelaPrincipal();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPrincipal = new JFrame();
		frmPrincipal.setTitle("Restaurante");
		try {
			frmPrincipal.setContentPane(new FundoTela("japones.png"));
		} catch (IOException e1) {
		}	

		frmPrincipal.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				try{
					//  pre-cadastro
					Produto p;
					p = Facade.cadastrarProduto("temaki", 12.0);
					p = Facade.cadastrarProduto("sushi", 26.0);
					p = Facade.cadastrarProduto("sashimi", 22.0);
					p = Facade.cadastrarProduto("coca-cola", 12.0);
					p = Facade.cadastrarProduto("suco", 10.0);
					p = Facade.cadastrarProduto("nigiri", 21.0);
					p = Facade.cadastrarProduto("yakisoba", 10.0);
					p = Facade.cadastrarProduto("chop suey", 10.0);
					Facade.criarMesas(20);
					Garcom g;					
					g = Facade.cadastrarGarcom("Fabricio", 1, 5);
					g = Facade.cadastrarGarcom("Monitora", 6, 10);
					g = Facade.cadastrarGarcom("Rafael", 11, 15);
					g = Facade.cadastrarGarcom("Builder", 16, 20);
				}catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
			@Override
			public void windowClosing(WindowEvent e) {
				JOptionPane.showMessageDialog(frmPrincipal, "at� breve !");
			}
		});

		frmPrincipal.setBounds(100, 100, 450, 300);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.getContentPane().setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		frmPrincipal.setJMenuBar(menuBar);

		mnProduto = new JMenu("Produto");
		menuBar.add(mnProduto);

		mntmCadastrar = new JMenuItem("Cadastrar");
		mntmCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroProduto j = new TelaCadastroProduto();
				j.setVisible(true);
			}
		});
		mnProduto.add(mntmCadastrar);

		mntmListar = new JMenuItem("Listar");
		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListagemProduto j = new TelaListagemProduto();
				j.setVisible(true);
			}
		});
		mnProduto.add(mntmListar);

		JMenu mnMesa = new JMenu("Mesa");
		menuBar.add(mnMesa);
		
		JMenuItem mntmcriarMesa = new JMenuItem("Criar");
		mntmcriarMesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroMesa j = new TelaCadastroMesa();
				j.setVisible(true);
			}
		});
		mnMesa.add(mntmcriarMesa);

		JMenuItem mntmListar_1 = new JMenuItem("Listar");
		mntmListar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaListagemMesa j = new TelaListagemMesa();
				j.setVisible(true);
			}
		});
		mnMesa.add(mntmListar_1);

		JMenuItem mntmSolicitarProduto = new JMenuItem("Solicitar produto");
		mntmSolicitarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaSolicitarProduto j = new TelaSolicitarProduto();
				j.setVisible(true);
			}
		});
		mnMesa.add(mntmSolicitarProduto);
		
		JMenuItem mntmTransferirConta = new JMenuItem("Transferir Conta");
		mntmTransferirConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaTransferirConta j = new TelaTransferirConta();
				j.setVisible(true);
			}
		});
		mnMesa.add(mntmTransferirConta);
		
		JMenu mnConta = new JMenu("Conta");
		menuBar.add(mnConta);

		JMenuItem mntmCriarConta = new JMenuItem("Criar");
		mntmCriarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroConta j = new TelaCadastroConta();
				j.setVisible(true);
			}
		});
		mnConta.add(mntmCriarConta);
		
		JMenuItem mntmListarContas = new JMenuItem("Listar");
		mntmListarContas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListagemConta j = new TelaListagemConta();
				j.setVisible(true);
			}
		});
		mnConta.add(mntmListarContas);
		
		JMenuItem mntmFecharConta = new JMenuItem("Fechar");
		mntmFecharConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaFecharConta j = new TelaFecharConta();
				j.setVisible(true);
			}
		});
		mnConta.add(mntmFecharConta);
		
		JMenuItem mntmCancelarConta = new JMenuItem("Cancelar");
		mntmCancelarConta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCancelarConta j = new TelaCancelarConta();
				j.setVisible(true);
			}
		});
		mnConta.add(mntmCancelarConta);
		
		JMenu mnGarcom = new JMenu("Gar�om");
		menuBar.add(mnGarcom);

		JMenuItem mntmCadastroGarcom = new JMenuItem("Criar");
		mntmCadastroGarcom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCadastroGarcom j = new TelaCadastroGarcom();
				j.setVisible(true);
			}
		});
		mnGarcom.add(mntmCadastroGarcom);
		
		JMenuItem mntmListarGarcons = new JMenuItem("Listar");
		mntmListarGarcons.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaListagemGarcom j = new TelaListagemGarcom();
				j.setVisible(true);
			}
		});
		mnGarcom.add(mntmListarGarcons);
		
		JMenuItem mntmCalcularGorjeta = new JMenuItem("Calcular Gorjeta");
		mntmCalcularGorjeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaCalcularGorjeta j = new TelaCalcularGorjeta();
				j.setVisible(true);
			}
		});
		mnGarcom.add(mntmCalcularGorjeta);
		
		JMenuItem mntmExclusaoGarcom = new JMenuItem("Deletar");
		mntmExclusaoGarcom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaExclusaoGarcom j = new TelaExclusaoGarcom();
				j.setVisible(true);
			}
		});
		mnGarcom.add(mntmExclusaoGarcom);
		
		JMenu mnConsulta = new JMenu("Consulta");
		mnConsulta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				TelaConsulta j = new TelaConsulta();
				j.setVisible(true);
			}
		});
		menuBar.add(mnConsulta);
		
		
	
	}
}
