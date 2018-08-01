/**IFPB - Curso SI - Disciplina de POO
 * @author Fabricio Liberato
 */
package aplicacao;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Produto;
import modelo.TableModel;

public class TelaListagemProduto extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private TableModel tableModel;
	/**
	 * Create the frame.
	 */
	public TelaListagemProduto() {
		setTitle("Listar Produtos");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		label.setBounds(57, 21, 418, 33);
		contentPane.add(label);
		
		try{
			Object [] dado = null;
			ArrayList<Produto> lista1 = Fachada.listarProdutos("");
			if (lista1 == null) {
				label.setText("Nenhum produto cadastrado!");
			}
			else {
				dado = new Object[2];
				String [] colunas = {"Nome", "Preco"};
				tableModel = new TableModel(lista1.size(), colunas);
				for (int i = 0; i < lista1.size(); i++) {
					dado[0] = lista1.get(i).getNome();
					dado[1] = lista1.get(i).getPreco();
					tableModel.addRow(dado);
				}
				label.setText("Listagem de produtos:");
			}
		}
		catch(Exception erro){
			JOptionPane.showMessageDialog(null,erro.getMessage());
			erro.printStackTrace();
		}

		table = new JTable(tableModel);
		table.setBounds(190, 383, 191, 250);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(57, 73, 418, 208);
		contentPane.add(scroll);
		
		JButton btnFecharListagem = new JButton("Fechar Listagem");
		btnFecharListagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnFecharListagem.setBounds(310, 312, 165, 35);
		btnFecharListagem.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(btnFecharListagem);
	}
}
