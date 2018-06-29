package application;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/
import java.awt.EventQueue;
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
import model.Conta;
import model.Produto;

public class TelaCalcularGorjeta extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JLabel lblApelido;
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
	public TelaCalcularGorjeta() {
		setTitle("Cancelar Conta");
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

		btnCriar = new JButton("Calcular");
		btnCriar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					String dataFechamento = df.format(new Date());
					String apelido = textField.getText();
					double gorjeta = Facade.calcularGorjeta(apelido);
					lblmsg.setText("Valor total da gorjeta em "+ dataFechamento + " é de R$ " + gorjeta);					
					textField.setText("");
					textField.requestFocus();
				}
				catch(Exception erro){
					lblmsg.setText(erro.getMessage());
					erro.printStackTrace();
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
