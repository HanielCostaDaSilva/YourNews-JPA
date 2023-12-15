/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */
package appswing;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import model.Assunto;
import service.Fachada;

public class TelaAssunto {
	private JDialog frame;
	private JTable assuntoTable;
	private JScrollPane scrollPane;
	private JTextField nomeField;
	private JButton button;
	private JButton adicionarAssuntoButton;
	private JButton button_2;
	private JLabel resultado;
	private JLabel label_3;
	private JLabel label_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAssunto tela = new TelaAssunto();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaAssunto() {
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		//frame.setModal(true);

		frame.setResizable(false);
		frame.setTitle("Assunto");
		frame.setBounds(100, 100, 729, 385);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
				listagem();
			}

			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}
		});

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 43, 674, 148);
		frame.getContentPane().add(scrollPane);

		assuntoTable = new JTable();
		assuntoTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label_4.setText("selecionado=" + (String) assuntoTable.getValueAt(assuntoTable.getSelectedRow(), 1));
			}
		});
		assuntoTable.setGridColor(Color.BLACK);
		assuntoTable.setRequestFocusEnabled(false);
		assuntoTable.setFocusable(false);
		assuntoTable.setBackground(Color.LIGHT_GRAY);
		assuntoTable.setFillsViewportHeight(true);
		assuntoTable.setRowSelectionAllowed(true);
		assuntoTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(assuntoTable);
		assuntoTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		assuntoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		assuntoTable.setShowGrid(true);
		assuntoTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		resultado = new JLabel(""); // label de mensagem
		resultado.setForeground(Color.BLUE);
		resultado.setBounds(21, 321, 688, 14);
		frame.getContentPane().add(resultado);

		label_4 = new JLabel("resultados:");
		label_4.setBounds(21, 190, 431, 14);
		frame.getContentPane().add(label_4);


		adicionarAssuntoButton = new JButton("Criar novo assunto");
		adicionarAssuntoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String nome = nomeField.getText();
					Fachada.adicionarAssunto(nome);
					resultado.setText("Assuto criado: " + nome);
					listagem();
					
				} catch (Exception ex) {
					resultado.setText(ex.getMessage());
				}
			}
		});
		adicionarAssuntoButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		adicionarAssuntoButton.setBounds(293, 244, 203, 39);
		frame.getContentPane().add(adicionarAssuntoButton);

		button = new JButton("Listar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button.setBounds(21, 11, 674, 34);
		frame.getContentPane().add(button);

		label_3 = new JLabel("nome:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(37, 256, 63, 14);
		frame.getContentPane().add(label_3);

		nomeField = new JTextField();
		nomeField.setFont(new Font("Dialog", Font.PLAIN, 12));
		nomeField.setColumns(10);
		nomeField.setBounds(92, 244, 168, 39);
		frame.getContentPane().add(nomeField);

		button_2 = new JButton("Apagar selecionado");
		button_2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					if (assuntoTable.getSelectedRow() >= 0) {
						

						String assuntoNome = (String) assuntoTable.getValueAt(assuntoTable.getSelectedRow(), 1);
						resultado.setText("removendo Noticia:  "+ assuntoNome);


						Fachada.removerAssunto(assuntoNome);
						resultado.setText("assunto apagado");
						listagem();
					} else
						resultado.setText("nao selecionado");
				} catch (Exception ex) {
					resultado.setText(ex.getMessage());
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(506, 244, 203, 39);
		frame.getContentPane().add(button_2);
	}

	public void listagem() {
		try {
			List<Assunto> lista = Fachada.listarAssuntos();

			// model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			// adicionar colunas no model
			model.addColumn("ID");
			model.addColumn("nome");
			model.addColumn("Quantidade Noticias");
			
			// adicionar linhas no model
			for (Assunto ass : lista) {
				model.addRow(new Object[] { ass.getId(), ass.getNome(), ass.getListaNoticia().size()});
			}

			assuntoTable.setModel(model);

			TableColumn column;

			for (int i = 0; i < assuntoTable.getColumnCount(); i++) {
				column = assuntoTable.getColumnModel().getColumn(i);
				if (i == 0) {
					column.setPreferredWidth(30); // Tamanho desejado para a coluna ID
				} else if (i == 1) {
					column.setPreferredWidth(350);
				} else {
					column.setPreferredWidth(150);
				}
			}

			label_4.setText("resultados: " + lista.size() + " objetos");

		} catch (Exception erro) {
			resultado.setText(erro.getMessage());
			System.err.println(erro.getMessage());
		}
	}
}
