/**IFPB - Curso SI - Disciplina de POO
 * @author Fabricio Liberato
 */
package application;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import facade.Facade;
import model.Conta;
import model.Produto;
import model.TableModel;

public class TelaListagemConta extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private TableModel tableModel;
	
	private JTextField textField;
	/**
	 * Create the frame.
	 */
	public TelaListagemConta() {
		setTitle("Listar Contas");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		label.setBounds(10, 21, 286, 47);
		contentPane.add(label);
		
		JList<String> list = new JList<String>();
		list.setFont(new Font("Tahoma", Font.PLAIN, 18));
		list.setBounds(344, 153, 165, 148);
		contentPane.add(list);
		
		try{
			Object [] dado = null;
			ArrayList<Conta> lista1 = Facade.listarContas();
			if (lista1 == null) {
				label.setText("Nenhuma conta cadastrada!");
			}
			else {
				dado = new Object[5];
				String [] colunas = {"Numero", "Fechamento", "total","Mesa"};
				tableModel = new TableModel(lista1.size(), colunas);
				for (int i = 0; i < lista1.size(); i++) {
					dado[0] = lista1.get(i).getNumero();
					dado[1] = lista1.get(i).getDtfechamento();
					dado[2] = lista1.get(i).getTotal();
					dado[3] = lista1.get(i).getMesa().getId();
					tableModel.addRow(dado);
				}
				label.setText("Listagem de contas:");
			}
		}
		catch(Exception erro){
			JOptionPane.showMessageDialog(null,erro.getMessage());
			erro.printStackTrace();
		}

		table = new JTable(tableModel);
		table.setBounds(190, 383, 191, 250);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(10, 93, 286, 208);
		contentPane.add(scroll);
		
		JButton btnFecharListagem = new JButton("Fechar Listagem");
		btnFecharListagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnFecharListagem.setBounds(344, 327, 165, 35);
		btnFecharListagem.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(btnFecharListagem);
		
		textField = new JTextField();
		textField.setBounds(442, 59, 67, 25);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblDigiteONumero = new JLabel("Verifique os produtos:");
		lblDigiteONumero.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblDigiteONumero.setBounds(344, 7, 165, 47);
		contentPane.add(lblDigiteONumero);
		
		JLabel lblNumConta = new JLabel("Num Conta:");
		lblNumConta.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblNumConta.setBounds(344, 49, 94, 40);
		contentPane.add(lblNumConta);
		
		JButton button = new JButton("Listar Produtos");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					DefaultListModel<String> listagem = new DefaultListModel<>();
					ArrayList<Conta> lista1 = Facade.listarContas();
					Conta c = null;
					for (Conta conta : lista1)
						if(conta.getNumero() == Integer.parseInt(textField.getText()))
							c = conta;
					for (Produto p : c.getProdutos()) {
						listagem.addElement(p.getNome());
					}
					list.setModel(listagem);
					
				}catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		button.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		button.setBounds(344, 103, 165, 25);
		contentPane.add(button);
	}
}
