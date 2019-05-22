package vicand.finaleda;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import vicand.finaleda.controllers.AppController;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Rectangle;

public class MainWindow {

	private JFrame frmProyectoFinalEda;
	private JTextField textField;
	private JTable table;
	JFileChooser chooser;
	String choosertitle;
	private JTextField txtIndexar;
	AppController appController;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow(null);
					window.frmProyectoFinalEda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow(AppController appController) {
		initialize();
		frmProyectoFinalEda.setVisible(true);
		this.appController = appController;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProyectoFinalEda = new JFrame();
		frmProyectoFinalEda.setForeground(SystemColor.control);
		frmProyectoFinalEda.setFont(new Font("Algerian", Font.BOLD, 12));
		frmProyectoFinalEda.setTitle("Proyecto Final EDA II");
		frmProyectoFinalEda.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/resources/seo.png")));
		frmProyectoFinalEda.setBounds(100, 100, 544, 641);
		frmProyectoFinalEda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(frmProyectoFinalEda.getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 506, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(13, Short.MAX_VALUE))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
						.addContainerGap())
				);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Indexar", null, panel, null);

		JButton btnDirectory = new JButton("");
		btnDirectory.setForeground(new Color(0, 0, 0));
		btnDirectory.setIcon(new ImageIcon(MainWindow.class.getResource("/resources/folder (1).png")));
		btnDirectory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				chooser = new JFileChooser(); 
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle(choosertitle);
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				chooser.setAcceptAllFileFilterUsed(false);
				//    
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) { 
					txtIndexar.setText(chooser.getSelectedFile().getPath());

				}
				else {
					System.out.println("No Selection ");
				}

			}
		});

		JButton btnIndexar = new JButton("INDEXAR");
		btnIndexar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO - LLAMAR A CONTROLADOR E INDEXAR
				appController.StartIndex(txtIndexar.getText());
			}
		});

		txtIndexar = new JTextField();
		txtIndexar.setBounds(new Rectangle(25, 0, 0, 0));
		txtIndexar.setForeground(new Color(0, 0, 0));
		txtIndexar.setEditable(false);
		txtIndexar.setToolTipText("");
		txtIndexar.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(txtIndexar, GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnDirectory, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(btnIndexar, GroupLayout.PREFERRED_SIZE, 134, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
				);
		gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
						.addGap(200)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnIndexar, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnDirectory, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
								.addComponent(txtIndexar, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(298, Short.MAX_VALUE))
				);
		panel.setLayout(gl_panel);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Buscar", null, panel_1, null);

		textField = new JTextField();
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Buscar");

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
						.addContainerGap(67, Short.MAX_VALUE)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
										.addComponent(textField, GroupLayout.PREFERRED_SIZE, 267, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 371, GroupLayout.PREFERRED_SIZE))
						.addGap(63))
				);
		gl_panel_1.setVerticalGroup(
				gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
						.addGap(54)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 24, Short.MAX_VALUE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addGap(37)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE)
						.addGap(38))
				);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
					{null, null, null},
				},
				new String[] {
						"Nombre", "Tipo", "Ruta de archivo"
				}
				));
		table.getColumnModel().getColumn(0).setPreferredWidth(170);
		table.getColumnModel().getColumn(1).setPreferredWidth(81);
		table.getColumnModel().getColumn(2).setPreferredWidth(160);
		scrollPane.setViewportView(table);
		panel_1.setLayout(gl_panel_1);
		frmProyectoFinalEda.getContentPane().setLayout(groupLayout);
	}
}
