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

import model.Noticia;
import service.Fachada;

public class TelaNoticia {
	private JDialog frame;
	private JTable noticiaTable;
	private JScrollPane noticiaScrollPane;
	private JTextField textField_2;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JLabel label;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JLabel label_data;
	private JLabel label_link;
	private JTextField textField_1;
	private JTextField textField_3;
	private JLabel label_1;
	private JLabel label_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaNoticia tela = new TelaNoticia();
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
		frame.setModal(true);

		frame.setResizable(false);
		frame.setTitle("Notícia");
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

		noticiaScrollPane = new JScrollPane();
		noticiaScrollPane.setBounds(21, 43, 674, 148);
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
		noticiaTable.setBackground(Color.YELLOW);
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
		label.setBounds(25, 299, 688, 14);
		frame.getContentPane().add(label);

		label_4 = new JLabel("resultados:");
		label_4.setBounds(21, 190, 431, 14);
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
					// 	label.setText("campo vazio");
					// 	return;
					// }
					String titulo = textField_2.getText();
					String dataPublicacao = textField_3.getText();
					String link = textField_2.getText();
					Fachada.adicionarNoticia(titulo, dataPublicacao, link);
					label.setText("Notícia criada: " + titulo);
					listagem();
				} catch (Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(291, 270, 153, 23);
		frame.getContentPane().add(button_1);

		button = new JButton("Listar");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();
			}
		});
		button.setBounds(308, 11, 89, 23);
		frame.getContentPane().add(button);

		label_3 = new JLabel("Título:");
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_3.setBounds(40, 247, 63, 14);
		frame.getContentPane().add(label_3);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_2.setColumns(10);
		textField_2.setBounds(81, 244, 168, 20);
		frame.getContentPane().add(textField_2);

		button_2 = new JButton("Apagar selecionado");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (noticiaTable.getSelectedRow() >= 0) {
						label.setText("nao implementado ");
						int id = (int) noticiaTable.getValueAt(noticiaTable.getSelectedRow(), 0);

						Fachada.removerNoticia(id);
						label.setText("Notícia apagada");
						listagem();
					} else
						label.setText("nao selecionada");
				} catch (Exception ex) {
					label.setText(ex.getMessage());
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(281, 213, 171, 23);
		frame.getContentPane().add(button_2);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_1.setColumns(10);
		textField_1.setBounds(81, 272, 168, 20);
		frame.getContentPane().add(textField_1);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		textField_3.setColumns(10);
		textField_3.setBounds(81, 303, 168, 20);
		frame.getContentPane().add(textField_3);
		
		label_1 = new JLabel("Link:");
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(40, 274, 63, 14);
		frame.getContentPane().add(label_1);
		
		label_5 = new JLabel("Data:");
		label_5.setHorizontalAlignment(SwingConstants.LEFT);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_5.setBounds(40, 307, 63, 14);
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

			// adicionar linhas no model
			for (Noticia not : lista) {
				model.addRow(new Object[] { not.getId(), not.getTitulo(), not.getLink() });
			}

			// atualizar model no noticiaTable (visualizacao)
			noticiaTable.setModel(model);

			label_4.setText("resultados: " + lista.size() + " objetos");
		} catch (Exception erro) {
			label.setText(erro.getMessage());
		}
	}
}
