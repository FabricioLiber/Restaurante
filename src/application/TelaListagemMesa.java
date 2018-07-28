/**IFPB - Curso SI - Disciplina de POO
 * @author Fabricio Liberato
 */
package application;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import facade.Facade;
import model.Conta;
import model.Mesa;
import model.Produto;
import model.TableModel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaListagemMesa extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private TableModel tableModel;
	/**
	 * Create the frame.
	 */
	public TelaListagemMesa() {
		setTitle("Listar Mesas");
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
			String [] dado = null;
			ArrayList<Mesa> mesas = Facade.listarMesas();
			if (mesas.isEmpty()) {
				label.setText("Nenhuma mesa cadastrada!");
			}
			else {
				dado = new String[4];
				String [] colunas = {"Id", "Situacao", "Garcom", "Contas"};
				tableModel = new TableModel(mesas.size(), colunas);
				for (int i = 0; i < mesas.size(); i++) {
					dado[0] = String.valueOf(mesas.get(i).getId());
					if (mesas.get(i).isOcupada())
						dado[1] = "Ocupada"; 
					else
						dado[1] = "Desocupada";
					if (mesas.get(i).getGarcom() == null)
						dado[2] = "---";
					else
						dado[2] = mesas.get(i).getGarcom().getApelido();
					dado[3] = "";
					for (Conta c : mesas.get(i).getContas())
						dado[3]= dado[3].concat((String.valueOf(c.getNumero())+" - "));
					tableModel.addRow(dado);
				}
				label.setText("Listagem de mesas:");
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


