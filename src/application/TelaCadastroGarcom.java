/**IFPB - Curso SI - Disciplina de POO
 * @author Fabrício Liberato
 */
package application;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import facade.Facade;
import model.Garcom;

public class TelaCadastroGarcom extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblNome;
	private JLabel lblMesaInicial;
	private JLabel lblMesaFinal;
	private JButton btnCriar;
	private JLabel lblmsg;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCadastroGarcom frame = new TelaCadastroGarcom();
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
	public TelaCadastroGarcom() {
		setTitle("Cadastrar Garcom");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 400, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(110, 11, 80, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 14, 80, 14);
		contentPane.add(lblNome);

		lblMesaInicial = new JLabel("Mesa Inicial");
		lblMesaInicial.setBounds(10, 52, 80, 14);
		contentPane.add(lblMesaInicial);
		
		lblMesaFinal = new JLabel("Mesa Final");
		lblMesaFinal.setBounds(10, 90, 80, 14);
		contentPane.add(lblMesaFinal);

		textField_1 = new JTextField();
		textField_1.setBounds(110, 49, 40, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(110, 87, 40, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);


		btnCriar = new JButton("Cadastrar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String nome = textField.getText();
					int mesainicial = Integer.parseInt(textField_1.getText());
					int mesafinal = Integer.parseInt(textField_2.getText());
					Garcom g = Facade.cadastrarGarcom(nome, mesainicial, mesafinal);
					lblmsg.setText("cadastrado "+g.getApelido());					
					textField.setText("");
					textField_1.setText("");
					textField.requestFocus();
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnCriar.setBounds(210, 70, 115, 23);
		contentPane.add(btnCriar);
		
		lblmsg = new JLabel("");
		lblmsg.setBounds(10, 130, 273, 14);
		contentPane.add(lblmsg);
	}
}
