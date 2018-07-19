/**IFPB - Curso SI - Disciplina de POO
 * @author Fabricio Liberato
 */
package application;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import facade.Facade;
import model.Conta;
import model.Produto;

public class TelaFecharConta extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_2;
	private JLabel lblGarcom;
	private JLabel lblIdMesa;
	private JButton btnCriar;
	private JLabel lblmsg;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCancelarConta frame = new TelaCancelarConta();
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
	public TelaFecharConta() {
		setTitle("Fechar Conta");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 340, 160);
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

		textField = new JTextField();
		textField.setBounds(150, 53, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		lblIdMesa = new JLabel("Id mesa");
		lblIdMesa.setBounds(19, 56, 130, 14);
		contentPane.add(lblIdMesa);

		btnCriar = new JButton("Fechar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int id = Integer.parseInt(textField.getText());
					Facade.fecharConta(id);
					lblmsg.setText("Conta fechada na mesa "+ id);					
					textField.setText("");
					textField.requestFocus();
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
					erro.printStackTrace();
				}
			}
		});
		btnCriar.setBounds(150, 80, 115, 23);
		contentPane.add(btnCriar);
		
		lblmsg = new JLabel("");
		lblmsg.setBounds(10, 110, 300, 14);
		contentPane.add(lblmsg);
	}
}
