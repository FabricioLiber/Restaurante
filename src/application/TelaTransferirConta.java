
/**IFPB - Curso SI - Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */
package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import facade.Facade;

public class TelaTransferirConta extends JFrame {
	private JPanel contentPane;
	private JLabel lblIdOrigem;
	private JLabel lblIdDestino;
	private JLabel lblGarcom;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnInserir;
	private DefaultListModel<String> model = new DefaultListModel<String>();	
	private JLabel lblmsg;
	private JButton btnLimpar;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaInserirProdutoPrateleira window = new TelaInserirProdutoPrateleira();
//					window.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public TelaTransferirConta() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Transferir conta");
		setBounds(100, 100, 345, 229);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_2 = new JTextField();
		textField_2.setBounds(150, 25, 86, 20);
		contentPane.add(this.textField_2);
		textField_2.setColumns(10);

		lblGarcom = new JLabel("Garcom Responsavel");
		lblGarcom.setBounds(19, 28, 130, 14);
		contentPane.add(this.lblGarcom);
		
		lblIdDestino = new JLabel("Id Mesa de Destino");
		lblIdDestino.setBounds(19, 84, 120, 14);
		contentPane.add(this.lblIdDestino);
		lblIdOrigem = new JLabel("Id Mesa de Origem");
		lblIdOrigem.setBounds(19, 56, 120, 14);
		contentPane.add(this.lblIdOrigem);
		textField = new JTextField();
		textField.setBounds(130, 53, 50, 20);
		contentPane.add(this.textField);
		textField.setColumns(10);
		textField_1 = new JTextField();
		textField_1.setBounds(130, 81, 50, 20);
		contentPane.add(this.textField_1);
		textField_1.setColumns(10);
		btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String garcomApelido = textField_2.getText();
					int idOrigem = Integer.parseInt(textField.getText());
					if (Facade.autenticarGarcom(garcomApelido, idOrigem)) {
						int idDestino = Integer.parseInt(textField_1.getText());
						Facade.transferirConta(idOrigem, idDestino);					
						lblmsg.setText("Conta da Mesa"+ idOrigem +" transferida para Conta da Mesa "+ idDestino);
					}
				} catch (NumberFormatException e) {
					lblmsg.setText("campo id deve ser numerico");
				} catch (Exception e) {
					lblmsg.setText(e.getMessage());
				}
			}
		});
		btnInserir.setBounds(19, 130, 136, 23);
		contentPane.add(this.btnInserir);
		lblmsg = new JLabel("");
		lblmsg.setBounds(19, 164, 310, 14);
		contentPane.add(this.lblmsg);
		btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField.requestFocus();
			}
		});
		btnLimpar.setBounds(172, 130, 141, 23);
		contentPane.add(this.btnLimpar);

	}
}
