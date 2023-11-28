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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;


import model.Assunto;
import model.Noticia;
import service.Fachada;
import javax.swing.JToolBar;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.BoxLayout;

public class TelaAssuntoNoticia {
	private JDialog frame;
	private JTable listarAssuntotable;
	private JScrollPane assuntoScrollPane;
	private JTextField resultado;
	private JTextField assuntoSelecionadoInput;
	private JButton listarAssuntosBtn;
	private JButton associarAssuntoNoticiaBtn;
	private JTextField noticiaSelecionadaInput;
	private JScrollPane noticiaScrollPane;
	private JTable listarNoticiaTable;
	private JLabel noticiaLabel;
	private JButton listarNoticiaBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAssuntoNoticia window = new TelaAssuntoNoticia();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaAssuntoNoticia() {
		initialize();
		DefaultTableModel model = (DefaultTableModel) listarNoticiaTable.getModel();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setModal(true);

		frame.setResizable(false);
		frame.setTitle("Assuntos e Notícias");
		frame.setBounds(100, 100, 900, 442);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Fachada.inicializar();
				listagemAssunto();
				listarNoticia();
			}

			@Override
			public void windowClosing(WindowEvent e) {
				Fachada.finalizar();
			}
		});

		resultado = new JTextField();
		resultado.setBackground(new Color(0, 0, 0));
		resultado.setEnabled(false);
		resultado.setEditable(false);
		resultado.setForeground(Color.BLUE);
		resultado.setBounds(10, 374, 776, 18);
		frame.getContentPane().add(resultado);

		assuntoScrollPane = new JScrollPane();
		assuntoScrollPane.setBounds(10, 30, 193, 265);
		frame.getContentPane().add(assuntoScrollPane);

		listarAssuntotable = new JTable();
		listarAssuntotable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = listarAssuntotable.getSelectedRow();
				if (selectedRow >= 0) {
					// Obtém o ID da notícia selecionada (supondo que seja a primeira coluna)

					Object idAssunto = listarAssuntotable.getValueAt(selectedRow, 0);
					assuntoSelecionadoInput.setText(idAssunto.toString());
				}
			}
		});
		listarAssuntotable.setGridColor(Color.BLACK);
		listarAssuntotable.setRequestFocusEnabled(false);
		listarAssuntotable.setFocusable(false);
		listarAssuntotable.setBackground(Color.WHITE);
		listarAssuntotable.setFillsViewportHeight(true);
		listarAssuntotable.setRowSelectionAllowed(true);
		listarAssuntotable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		assuntoScrollPane.setViewportView(listarAssuntotable);
		listarAssuntotable.setBorder(new LineBorder(new Color(0, 0, 0)));
		listarAssuntotable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listarAssuntotable.setShowGrid(true);
		listarAssuntotable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		associarAssuntoNoticiaBtn = new JButton("Associar");
		associarAssuntoNoticiaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					String noticiaSelecionadaText = noticiaSelecionadaInput.getText();
					String assuntoSelecionadoText = assuntoSelecionadoInput.getText();
					if (noticiaSelecionadaText.length() == 0 || assuntoSelecionadoText.length() == 0) // caso um dos
																										// campos esteja
																										// vazio

						throw new Exception("Faltou preencher algum dos campos.");
					int idNoticia = Integer.parseInt(noticiaSelecionadaInput.getText());
					int idAssunto = Integer.parseInt(assuntoSelecionadoInput.getText());
					associarAssunto(idNoticia,idAssunto);
					resultado.setText("Noticia: "+noticiaSelecionadaText+" foi associada ao assunto "+assuntoSelecionadoText);
				} catch (Exception ex) {
					resultado.setText(ex.getMessage());
				}
			}
		});

		associarAssuntoNoticiaBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		associarAssuntoNoticiaBtn.setBounds(672, 54, 193, 68);
		frame.getContentPane().add(associarAssuntoNoticiaBtn);

		listarAssuntosBtn = new JButton("Listar Assuntos");
		listarAssuntosBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		listarAssuntosBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagemAssunto();
			}
		});
		listarAssuntosBtn.setBounds(10, 9, 193, 23);
		frame.getContentPane().add(listarAssuntosBtn);

		JPanel Assunto = new JPanel();
		Assunto.setBounds(10, 326, 289, 37);
		frame.getContentPane().add(Assunto);
		Assunto.setLayout(new BoxLayout(Assunto, BoxLayout.X_AXIS));

		JLabel assuntoLabel = new JLabel("Assunto:    ");
		assuntoLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		Assunto.add(assuntoLabel);

		assuntoSelecionadoInput = new JTextField();
		Assunto.add(assuntoSelecionadoInput);
		assuntoSelecionadoInput.setFont(new Font("Dialog", Font.PLAIN, 12));
		assuntoSelecionadoInput.setColumns(10);

		JPanel noticiaPanel = new JPanel();
		noticiaPanel.setBounds(340, 326, 370, 37);
		frame.getContentPane().add(noticiaPanel);
		noticiaPanel.setLayout(new BoxLayout(noticiaPanel, BoxLayout.X_AXIS));

		noticiaLabel = new JLabel("Noticia:    ");
		noticiaLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		noticiaPanel.add(noticiaLabel);

		JSeparator separator = new JSeparator();
		noticiaPanel.add(separator);

		noticiaSelecionadaInput = new JTextField();
		noticiaPanel.add(noticiaSelecionadaInput);
		noticiaSelecionadaInput.setFont(new Font("Dialog", Font.PLAIN, 12));
		noticiaSelecionadaInput.setColumns(10);

		noticiaScrollPane = new JScrollPane();
		noticiaScrollPane.setBounds(210, 30, 452, 261);
		frame.getContentPane().add(noticiaScrollPane);
		listarNoticiaTable = new JTable();
		listarNoticiaTable.setGridColor(Color.BLACK);
		listarNoticiaTable.setRequestFocusEnabled(false);
		listarNoticiaTable.setFocusable(false);
		listarNoticiaTable.setBackground(Color.WHITE);
		listarNoticiaTable.setFillsViewportHeight(true);
		listarNoticiaTable.setRowSelectionAllowed(true);
		listarNoticiaTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		listarNoticiaTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		noticiaScrollPane.setColumnHeaderView(listarNoticiaTable);
		noticiaScrollPane.setViewportView(listarNoticiaTable);
		
		listarNoticiaTable.setBorder(new LineBorder(new Color(0, 0, 0)));
		listarNoticiaTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listarNoticiaTable.setShowGrid(true);

		listarNoticiaTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int selectedRow = listarNoticiaTable.getSelectedRow();
				if (selectedRow >= 0) {
					Object idNoticia = listarNoticiaTable.getValueAt(selectedRow, 0);

					noticiaSelecionadaInput.setText(idNoticia.toString());
				}
			}
		});


		JButton desassociarAssunto = new JButton("Desassociar");
		desassociarAssunto.setFont(new Font("Tahoma", Font.PLAIN, 12));
		desassociarAssunto.setBounds(672, 147, 193, 68);

		desassociarAssunto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String noticiaSelecionadaText = noticiaSelecionadaInput.getText();
					String assuntoSelecionadoText = assuntoSelecionadoInput.getText();

					if (noticiaSelecionadaText.length() == 0 || assuntoSelecionadoText.length() == 0) {
						throw new Exception("Faltou preencher algum dos campos.");
					}

					desassociarAssunto(Integer.parseInt(noticiaSelecionadaText),
					Integer.parseInt(assuntoSelecionadoText));

					resultado.setText("Noticia: " + noticiaSelecionadaText + " desasociada do assunto "
							+ assuntoSelecionadoText + " com sucesso");
				} catch (Exception ex) {
					resultado.setText(ex.getMessage());
				}
			}
		});

		frame.getContentPane().add(desassociarAssunto);
		
		listarNoticiaBtn = new JButton("Listar Noticia");
		listarNoticiaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		listarNoticiaBtn.setVerticalAlignment(SwingConstants.BOTTOM);
		listarNoticiaBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		listarNoticiaBtn.setBounds(210, 9, 219, 23);
		frame.getContentPane().add(listarNoticiaBtn);
	}

	public void listagemAssunto() {
		try {
			List<Assunto> lista = Fachada.listarAssuntos();

			// model armazena todas as linhas e colunas do table
			DefaultTableModel model = new DefaultTableModel();

			// adicionar colunas no model
			model.addColumn("ID");
			model.addColumn("nome");

			// adicionar linhas no model
			for (Assunto ass : lista) {
				model.addRow(new Object[] { ass.getId(), ass.getNome() });
			}

			// atualizar model no table (visualizacao)
			listarAssuntotable.setModel(model);

		} catch (Exception erro) {
			resultado.setText(erro.getMessage());
		}
	}

	public void listarNoticia() {
		try {
			List<Noticia> lista = Fachada.listarNoticias();
	
			// Configurar o modelo da tabela
			DefaultTableModel model = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
	
			// Adicionar colunas no modelo
			model.addColumn("ID");
			model.addColumn("Título");
			model.addColumn("Publicada");
	
			// Adicionar linhas no modelo
			for (Noticia noticia : lista) {
				model.addRow(new Object[] { Integer.toString(noticia.getId()), noticia.getTitulo(), noticia.getDataPublicacao() });
			}
	
			// Configurar o modelo na tabela
			listarNoticiaTable.setModel(model);
	
		} catch (Exception e) {
			resultado.setText(e.getMessage());
		}
	}
	public void associarAssunto(int idNoticia, int idAssunto) throws Exception {
		Fachada.associarAssuntoNoticia(idNoticia, idAssunto);
	}

	public void desassociarAssunto(int idNoticia, int idAssunto) throws Exception {
		Fachada.desassociarNoticiaAssunto(idNoticia, idAssunto);
	}
}