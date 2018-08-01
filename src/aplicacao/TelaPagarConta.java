/**IFPB - Curso SI - Disciplina de POO
 * @author Fabricio Liberato
 */
package aplicacao;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;
import modelo.Pagamento;

public class TelaPagarConta extends JFrame {
	private JPanel contentPane;
	private JTextField textFieldIdMesa;
	private JTextField textFieldDesconto;
	private JTextField textFieldNumCartao;
	private JTextField textfieldQtdParcelas;
	private JButton btnNewButton;
	private JComboBox<String> comboBox;

	/**
	 * Create the application.
	 */
	public TelaPagarConta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Solicitar produto");
		setBounds(100, 100, 450, 550);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Pagamento");
		lblNewLabel.setBounds(173, 12, 109, 38);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		contentPane.add(lblNewLabel);
		
		JLabel lblFormaDePagamento = new JLabel("Forma de pagamento:");
		lblFormaDePagamento.setBounds(66, 102, 163, 30);
		lblFormaDePagamento.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(lblFormaDePagamento);
		
		comboBox = new JComboBox<>();
		comboBox.setBounds(239, 102, 128, 30);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Selecione aqui", "Dinheiro", "Cartao"}));
		comboBox.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(comboBox);
		
		JLabel lblIdMesa = new JLabel("ID Mesa:");
		lblIdMesa.setBounds(66, 61, 80, 30);
		lblIdMesa.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		contentPane.add(lblIdMesa);
		
		textFieldIdMesa = new JTextField();
		textFieldIdMesa.setBounds(156, 62, 211, 30);
		textFieldIdMesa.setColumns(10);
		contentPane.add(textFieldIdMesa);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setLayout(null);
		panel_1.setBounds(-3, 143, 449, 103);
		contentPane.add(panel_1);
		
		JLabel label = new JLabel("Para pagamento em dinheiro:");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		label.setBounds(73, 11, 220, 30);
		panel_1.add(label);
		
		textFieldDesconto = new JTextField();
		textFieldDesconto.setBounds(277, 52, 96, 30);
		panel_1.add(textFieldDesconto);
		textFieldDesconto.setColumns(10);
		
		JLabel lblPorcentagemDeDesconto = new JLabel("Porcentagem de desconto:");
		lblPorcentagemDeDesconto.setBounds(74, 53, 195, 30);
		panel_1.add(lblPorcentagemDeDesconto);
		lblPorcentagemDeDesconto.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_2.setBounds(2, 250, 444, 160);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label_1 = new JLabel("Para pagamento com cartao:");
		label_1.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		label_1.setBounds(72, 11, 220, 30);
		panel_2.add(label_1);
		
		JLabel label_2 = new JLabel("Numero do cartao:");
		label_2.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_2.setBounds(72, 62, 134, 30);
		panel_2.add(label_2);
		
		textFieldNumCartao = new JTextField();
		textFieldNumCartao.setColumns(10);
		textFieldNumCartao.setBounds(205, 64, 163, 30);
		panel_2.add(textFieldNumCartao);
		
		textfieldQtdParcelas = new JTextField();
		textfieldQtdParcelas.setColumns(10);
		textfieldQtdParcelas.setBounds(234, 105, 134, 30);
		panel_2.add(textfieldQtdParcelas);
		
		JLabel label_3 = new JLabel("Quantidade de Parcelas:");
		label_3.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		label_3.setBounds(72, 103, 152, 30);
		panel_2.add(label_3);
		
		btnNewButton = new JButton("Confirmar");
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		btnNewButton.setBounds(227, 427, 140, 38);
		contentPane.add(btnNewButton);
	
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (comboBox.getSelectedItem().equals(new String("Dinheiro"))) {
					textFieldDesconto.setEnabled(true);
					btnNewButton.setEnabled(true);
				} else if (comboBox.getSelectedItem().equals(new String("Cartao"))) {
					textfieldQtdParcelas.setEnabled(true);
					textFieldNumCartao.setEnabled(true);
					btnNewButton.setEnabled(true);
				} else {
					situacaoCampos(false);
				}
			}
		});		

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pagamento p;
				try {
					if (comboBox.getSelectedItem().equals(new String("Dinheiro"))) {
						p = Fachada.pagarConta(Integer.parseInt(textFieldIdMesa.getText()), "dinheiro", Integer.parseInt(textFieldDesconto.getText()), "", 0);
						JOptionPane.showMessageDialog(contentPane, "Pagamento realizado!");
					} else if (comboBox.getSelectedItem().equals(new String("Cartao"))) {
						p = Fachada.pagarConta(Integer.parseInt(textFieldIdMesa.getText()), "cartao", 0, textFieldNumCartao.getText(), Integer.parseInt(textfieldQtdParcelas.getText()));
						JOptionPane.showMessageDialog(contentPane, "Pagamento realizado!");
						System.out.println(p.getValorpago());
					}
					situacaoCampos(false);
				} catch (Exception erro) {
					JOptionPane.showMessageDialog(contentPane, erro.getMessage());
					situacaoCampos(false);
				}
			}
		});
		situacaoCampos(false);
	}
	public void situacaoCampos (boolean situacao) {
		comboBox.setSelectedIndex(0);
		textFieldIdMesa.setText("");
		textFieldNumCartao.setEnabled(situacao);
		textFieldNumCartao.setText("");
		textfieldQtdParcelas.setEnabled(situacao);
		textfieldQtdParcelas.setText("");
		textFieldDesconto.setEnabled(situacao);
		textFieldDesconto.setText("");
		btnNewButton.setEnabled(situacao);
	}
}
