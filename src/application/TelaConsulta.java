/**IFPB - Curso SI - Disciplina de POO
 * @author Fabricio Liberato
 */
package application;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import facade.Facade;
import model.Conta;
import model.Produto;

public class TelaConsulta extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnConsulta_1;
	private JButton btnConsulta_2;
	private JButton btnConsulta_3;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaConsulta frame = new TelaConsulta();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public TelaConsulta() {
		setTitle("Consultar");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 744, 289);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textArea = new JTextArea();
		//textArea.setLineWrap(true);
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(24, 11, 348, 228);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll);

		btnConsulta_2 = new JButton("Produtos");
		btnConsulta_2.setBounds(414, 47, 271, 23);
		contentPane.add(btnConsulta_2);
		btnConsulta_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pesquisa = JOptionPane.showInputDialog(contentPane, "Nome para pesquisa:");
				String texto = null;
				ArrayList<Produto> lista1;
				try {
					lista1 = Facade.listarProdutos(pesquisa);
					texto = "Listagem de produtos com a palavra ("+ pesquisa +"): \n";
					if (lista1.isEmpty())
						texto += "n�o tem\n";
					else 	
						for(Produto p: lista1) 
							texto +=  p + "\n"; 

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					texto = e1.getMessage();
					e1.printStackTrace();
				}
				textArea.setText(texto);
			}
		});
		
		btnConsulta_3 = new JButton("Conta da Mesa");
		btnConsulta_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String texto = null;
				int idmesa = Integer.parseInt(JOptionPane.showInputDialog(contentPane, "Id da Mesa:"));
				try {
					Conta c = Facade.consultarConta(idmesa);
					texto = c.toString();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					texto = e1.getMessage();
				}
				textArea.setText(texto);
			}
		});
		btnConsulta_3.setBounds(414, 81, 271, 23);
		contentPane.add(btnConsulta_3);
	}
}
