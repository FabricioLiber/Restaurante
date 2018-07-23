/**IFPB - Curso SI - Disciplina de POO
 * @author Fabricio Liberato
 */
package application;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import facade.Facade;

public class TelaExclusaoGarcom extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblApelido;
	private JButton btnCriar;
	private JLabel lblmsg;

	/**
	 * Create the frame.
	 */
	public TelaExclusaoGarcom() {
		setTitle("Calcular Gorjeta");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 320, 147);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setBounds(150, 11, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		lblApelido = new JLabel("Apelido do Garcom");
		lblApelido.setBounds(10, 14, 150, 14);
		contentPane.add(lblApelido);

		btnCriar = new JButton("Deletar");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					String apelido = textField.getText();
					Facade.excluirGarcom(apelido);
					lblmsg.setText("Garcom "+ apelido +" excluido do restaurante!");					
					textField.setText("");
					textField.requestFocus();
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
				}
			}
		});
		btnCriar.setBounds(150, 48, 86, 20);
		contentPane.add(btnCriar);
		
		lblmsg = new JLabel("");
		lblmsg.setBounds(10, 94, 273, 14);
		contentPane.add(lblmsg);
	}
}
