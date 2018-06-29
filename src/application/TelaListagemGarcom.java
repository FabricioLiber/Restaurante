package application;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import facade.Facade;
import model.Garcom;

public class TelaListagemGarcom extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnListar;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TelaListagemGarcom() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				btnListar.doClick();
			}
		});
		setTitle("Listar Garcons");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 242);		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					ArrayList<Garcom> lista2 = Facade.listarGarcons();
					String texto = "Listagem de Garcons: \n";
					if (lista2.isEmpty())
						texto += "não tem garcons cadastrados\n";
					else 
						for(Garcom g: lista2) 
							texto +=  g + "\n"; 

					textArea.setText(texto);
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		btnListar.setBounds(224, 180, 115, 23);
		contentPane.add(btnListar);
		
		textArea = new JTextArea();		
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(24, 29, 510, 140);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll);
	}
}
