/**IFPB - Curso SI - Disciplina de POO
 * @author Fabricio Liberato
 */
package application;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import model.Mesa;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TelaListagemMesa extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private JButton btnListar;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public TelaListagemMesa() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				btnListar.doClick();
			}
		});
		setTitle("Listar Mesas");
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
					ArrayList<Mesa> lista2 = Facade.listarMesas();
					String texto = "Listagem de Mesas: \n";
					if (lista2.isEmpty())
						texto += "n�o tem mesas cadastradas\n";
					else 
						for(Mesa m: lista2) 
							texto +=  m + "\n"; 

					textArea.setText(texto);
				}
				catch(Exception erro){
					JOptionPane.showMessageDialog(null,erro.getMessage());
				}
			}
		});
		
		textArea = new JTextArea();		
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setBounds(24, 29, 510, 140);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		contentPane.add(scroll);
	}
}
