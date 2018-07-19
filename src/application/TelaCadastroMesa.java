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
import model.Mesa;

public class TelaCadastroMesa extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblNome;
	private JButton btnCriar;
	private JLabel lblmsg;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TelaCadastroPrateleira frame = new TelaCadastroPrateleira();
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
	public TelaCadastroMesa() {
		setTitle("Cadastrar Mesas");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 259, 139);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(90, 11, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		lblNome = new JLabel("Quantidade");
		lblNome.setBounds(15, 14, 70, 14);
		contentPane.add(lblNome);

		btnCriar = new JButton("Cadastrar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					int tamanho = Integer.parseInt(textField.getText());
					Facade.criarMesas(tamanho);
					lblmsg.setText(tamanho + "Mesas Criadas!");
					
					textField.setText("");
					textField.requestFocus();
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());

				}
			}
		});
		btnCriar.setBounds(72, 53, 115, 23);
		contentPane.add(btnCriar);
		
		lblmsg = new JLabel("");
		lblmsg.setBounds(10, 86, 233, 14);
		contentPane.add(lblmsg);
	}
}
