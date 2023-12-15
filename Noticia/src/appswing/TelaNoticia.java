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

import model.Noticia;
import service.Fachada;

public class TelaNoticia {
	private JDialog frame;
	private JTable noticiaTable;
	private JScrollPane noticiaScrollPane;
	private JTextField tituloInput;
	private JButton listarButton;
	private JButton button_1;
	private JButton button_2;
	private JLabel label;
	private JLabel label_3;
	private JLabel label_4;
	private JTextField linkInput;
	private JTextField dataInput;
	private JLabel label_1;
	private JLabel label_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new TelaNoticia();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaNoticia() {
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
		frame.setTitle("Notícia");
		frame.setBounds(100, 100, 898, 456);
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

		noticiaScrollPane = new JScrollPane();
		noticiaScrollPane.setBounds(10, 43, 872, 158);
		frame.getContentPane().add(noticiaScrollPane);

		noticiaTable = new JTable();
		noticiaTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				label_4.setText("selecionado=" + (String) noticiaTable.getValueAt(noticiaTable.getSelectedRow(), 0));
			}
		});
		noticiaTable.setGridColor(Color.BLACK);
		noticiaTable.setRequestFocusEnabled(false);
		noticiaTable.setFocusable(false);
		noticiaTable.setBackground(Color.LIGHT_GRAY);
		noticiaTable.setFillsViewportHeight(true);
		noticiaTable.setRowSelectionAllowed(true);
		noticiaTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		noticiaScrollPane.setViewportView(noticiaTable);
		noticiaTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		noticiaTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		noticiaTable.setShowGrid(true);
		noticiaTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel(""); // label de mensagem
		label.setForeground(Color.BLUE);
		label.setBounds(28, 392, 688, 14);
		frame.getContentPane().add(label);

		label_4 = new JLabel("resultados:");
		label_4.setBounds(21, 212, 431, 14);
		frame.getContentPane().add(label_4);

		// label_2 = new JLabel("ID:");
		// label_2.setHorizontalAlignment(SwingConstants.LEFT);
		// label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		// label_2.setBounds(21, 269, 71, 14);
		// frame.getContentPane().add(label_2);

		// textField = new JTextField();
		// textField.setFont(new Font("Dialog", Font.PLAIN, 12));
		// textField.setColumns(10);
		// textField.setBounds(68, 264, 195, 20);
		// frame.getContentPane().add(textField);

		button_1 = new JButton("Criar nova notícia");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// if (textField.getText().isEmpty()) {
					// label.setText("campo vazio");
					// return;
					// }
					String titulo = tituloInput.getText();
					String dataPublicacao = dataInput.getText();
					String link = linkInput.getText();

					Fachada.adicionarNoticia(titulo, dataPublicacao, link);
					label.setText("Notícia criada: " + titulo);
					listagem();

				} catch (Exception ex) {
					System.out.println(ex);
					label.setText(ex.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(439, 257, 173, 52);
		frame.getContentPane().add(button_1);

		listarButton = new JButton("Listar");
		listarButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		listarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		listarButton.setBounds(10, 11, 872, 32);
		frame.getContentPane().add(listarButton);

		label_3 = new JLabel("Título:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(28, 270, 63, 14);
		frame.getContentPane().add(label_3);

		tituloInput = new JTextField();
		tituloInput.setFont(new Font("Dialog", Font.PLAIN, 12));
		tituloInput.setColumns(10);
		tituloInput.setBounds(81, 261, 291, 32);
		frame.getContentPane().add(tituloInput);

		button_2 = new JButton("Apagar selecionado");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (noticiaTable.getSelectedRow() >= 0) {
						label.setText("nao implementado ");
						//System.out.println(noticiaTable.getValueAt(noticiaTable.getSelectedRow(), 0).getClass());
						String id = (String) noticiaTable.getValueAt(noticiaTable.getSelectedRow(), 0);

						Fachada.removerNoticia(id);
						label.setText("Notícia apagada");
						listagem();
					} else
						label.setText("nao selecionada");
				} catch (Exception ex) {
					System.out.println(ex);
					label.setText(ex.getMessage());
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(650, 257, 192, 52);
		frame.getContentPane().add(button_2);

		linkInput = new JTextField();
		linkInput.setFont(new Font("Dialog", Font.PLAIN, 12));
		linkInput.setColumns(10);
		linkInput.setBounds(81, 304, 291, 32);
		frame.getContentPane().add(linkInput);

		dataInput = new JTextField();
		dataInput.setFont(new Font("Dialog", Font.PLAIN, 12));
		dataInput.setColumns(10);
		dataInput.setBounds(81, 347, 247, 34);
		frame.getContentPane().add(dataInput);

		label_1 = new JLabel("Link:");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(28, 313, 63, 14);
		frame.getContentPane().add(label_1);

		label_5 = new JLabel("Data:");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_5.setBounds(21, 357, 63, 14);
		frame.getContentPane().add(label_5);
	}

	public void listagem() {
		try {
			List<Noticia> lista = Fachada.listarNoticias();

			// model armazena todas as linhas e colunas do noticiaTable
			DefaultTableModel model = new DefaultTableModel();

			// adicionar colunas no model
			model.addColumn("ID");
			model.addColumn("Título");
			model.addColumn("Link");
			model.addColumn("Publicação");
			model.addColumn("Assuntos");

			// adicionar linhas no model
			for (Noticia not : lista) {
				model.addRow(new Object[] {
						Integer.toString(not.getId()),
						not.getTitulo(),
						not.getLink(),
						not.getDataPublicacao(),
						not.getAssuntosNome()
				});
			}

			// atualizar model no noticiaTable (visualizacao)
			noticiaTable.setModel(model);

			// Definir tamanhos fixos para as colunas
			TableColumn column;
			for (int i = 0; i < noticiaTable.getColumnCount(); i++) {
				column = noticiaTable.getColumnModel().getColumn(i);
				if (i == 0) {
					column.setPreferredWidth(30); // Tamanho desejado para a coluna ID
				} else if( i==1) {
					column.setPreferredWidth(350); // Tamanho desejado para as outras colunas
				}else if( i==2) {
					column.setPreferredWidth(250); // Tamanho desejado para as outras colunas
				}
				else if( i==4) {
					column.setPreferredWidth(350); // Tamanho desejado para as outras colunas
				}
				 else {
					column.setPreferredWidth(150); // Tamanho desejado para as outras colunas
				}
			}

			label_4.setText("resultados: " + lista.size() + " objetos");
		} catch (Exception erro) {
			label.setText(erro.getMessage());
		}

	}
}
