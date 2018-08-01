/**IFPB - Curso SI - Disciplina de POO
 * @author Fabricio Liberato
 */
package aplicacao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import fachada.Fachada;

public class TelaSolicitarProduto extends JFrame {
	private JPanel contentPane;
	private JLabel lblId;
	private JLabel lblNome;
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
	public TelaSolicitarProduto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Solicitar produto");
		setBounds(100, 100, 345, 229);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblGarcom = new JLabel("Garcom Responsavel");
		lblGarcom.setBounds(19, 28, 130, 14);
		contentPane.add(this.lblGarcom);
		lblId = new JLabel("Id Mesa");
		lblId.setBounds(19, 56, 102, 14);
		contentPane.add(this.lblId);
		lblNome = new JLabel("Nome do Produto");
		lblNome.setBounds(19, 84, 102, 14);
		contentPane.add(this.lblNome);
		textField_2 = new JTextField();
		textField_2.setBounds(150, 25, 86, 20);
		contentPane.add(this.textField_2);
		textField_2.setColumns(10);
		textField = new JTextField();
		textField.setBounds(124, 81, 86, 20);
		contentPane.add(this.textField);
		textField.setColumns(10);
		textField_1 = new JTextField();
		textField_1.setBounds(124, 53, 40, 20);
		contentPane.add(this.textField_1);
		textField_1.setColumns(10);
		btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String garcomApelido = textField_2.getText();
					String nome = textField.getText();
					int id = Integer.parseInt(textField_1.getText());
					if (Fachada.autenticarGarcom(garcomApelido, id)) {
						Fachada.solicitarProduto(id, nome);
						lblmsg.setText("produto inserido ");
					} else
						lblmsg.setText("Garcom não serve essa mesa!");
				} catch (NumberFormatException e) {
					lblmsg.setText("campo id deve ser numerico");
				} catch (Exception e) {
					lblmsg.setText(e.getMessage());
				}
			}
		});
		btnInserir.setBounds(19, 125, 136, 23);
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
		btnLimpar.setBounds(172, 125, 141, 23);
		contentPane.add(this.btnLimpar);

	}
}
