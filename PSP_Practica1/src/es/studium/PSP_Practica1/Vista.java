package es.studium.PSP_Practica1;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

public class Vista extends JFrame {

	private static final long serialVersionUID = 1L;
	DlgVentanaSalir objDlgVentanaSalir = new DlgVentanaSalir();
	Modelo modelo = new Modelo();
	private JPanel contentPane;
	private JTextArea txtVentanaCmd;
	private JList<String> listaProcesos = new JList<String>();
	DefaultListModel<String> listModel = new DefaultListModel<String>();

	public Vista() {
		setTitle("PSP_Programaci\u00F3n Multiproceso");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 441, 766);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JButton btnBlocNotas = new JButton("Bloc de Notas");
		btnBlocNotas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Envía al método ejecProceso del Modelo los datos del proceso y un modelo de la lista de procesos
				modelo.ejecProceso("notepad", getListModel());
				// Envía al proceso en el Modelo el String correspondiente
				modelo.blocNota = modelo.process;
				//Deshabilita el botón Block de Notas
				btnBlocNotas.setEnabled(false);
			}
		});
		btnBlocNotas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnBlocNotas.setBounds(10, 11, 168, 37);
		contentPane.add(btnBlocNotas);

		JButton btnPaint = new JButton("Paint");
		btnPaint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Envía al método ejecProceso del Modelo los datos del proceso y un modelo de la lista de procesos
				modelo.ejecProceso("mspaint", getListModel());
				//Envía al proceso en el Modelo el String correspondiente
				modelo.paint = modelo.process;
				//Deshabilita el botón Paint
				btnPaint.setEnabled(false);
			}
		});
		btnPaint.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPaint.setBounds(10, 59, 168, 37);
		contentPane.add(btnPaint);

		JButton btnProgGestion = new JButton("Programa Gesti\u00F3n");
		btnProgGestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Envía al método ejecProceso del Modelo los datos del proceso y un modelo de la lista de procesos
				modelo.ejecProceso("java -jar programaGestion.jar", getListModel());
				// Envía al proceso en el Modelo el String correspondiente
				modelo.programaGestion = modelo.process;
				// Deshabilita el botón Programa de Gestión
				btnProgGestion.setEnabled(false);
			}
		});
		btnProgGestion.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnProgGestion.setBounds(10, 107, 168, 37);
		contentPane.add(btnProgGestion);

		JButton btnJuego = new JButton("Juego");
		btnJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Envía al método ejecProceso del Modelo los datos del proceso y un modelo de la lista de procesos
				modelo.ejecProceso("java -jar juegoSopaLetra.jar", getListModel());
				// Envía al proceso en el Modelo el String correspondiente
				modelo.juego = modelo.process;
				// Deshabilita el botón Juego
				btnJuego.setEnabled(false);
			}
		});
		btnJuego.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnJuego.setBounds(10, 155, 168, 37);
		contentPane.add(btnJuego);

		JLabel lblEtiquetaProcesos = new JLabel("PROCESOS ACTIVOS");
		lblEtiquetaProcesos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEtiquetaProcesos.setForeground(Color.BLACK);
		lblEtiquetaProcesos.setBounds(213, 10, 202, 37);
		contentPane.add(lblEtiquetaProcesos);

		listaProcesos.setModel(listModel);
		listaProcesos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaProcesos.setBounds(202, 50, 213, 94);
		contentPane.add(listaProcesos);

		JLabel lblEtiquetaCmd = new JLabel("COMANDOS CMD");
		lblEtiquetaCmd.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEtiquetaCmd.setBounds(126, 215, 168, 25);
		contentPane.add(lblEtiquetaCmd);

		JLabel lblCmd = new JLabel("Introduzca un comando cmd");
		lblCmd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCmd.setBounds(100, 239, 214, 25);
		contentPane.add(lblCmd);

		JTextArea txtCmd = new JTextArea(5, 100);
		txtCmd.setBounds(29, 275, 366, 25);
		contentPane.add(txtCmd);

		JButton btnEjecutarCmd = new JButton("Ejecutar cmd");
		btnEjecutarCmd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Envía al método ejecProceso del Modelo los datos del proceso y un modelo de la lista de procesos
				modelo.ejecCmd(txtCmd.getText(), getTxtVentanaCmd());
			}
		});
		btnEjecutarCmd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEjecutarCmd.setBounds(114, 317, 168, 37);
		contentPane.add(btnEjecutarCmd);

		JButton btnEliminarProceso = new JButton("Eliminar Proceso");
		btnEliminarProceso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Cierra el proceso seleccionado
				modelo.finalProceso(getListProcess(), modelo.process);
				// Habilita el botón correspondiente al proceso finalizado
				String procesoSelec = listaProcesos.getSelectedValue();
				if(procesoSelec == "notepad") {
					btnBlocNotas.setEnabled(true);
				}else if(procesoSelec == "mspaint") {
					btnPaint.setEnabled(true);
				}else if(procesoSelec == "java -jar programaGestion.jar") {
					btnProgGestion.setEnabled(true);
				}else if(procesoSelec == "java -jar juegoSopaLetra.jar") {
					btnJuego.setEnabled(true);
				}
				// Elimina de la lista el proceso seleccionado
				listModel.remove(getListProcess().getSelectedIndex());
			}
		});
		btnEliminarProceso.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnEliminarProceso.setBounds(227, 155, 168, 37);
		contentPane.add(btnEliminarProceso);
		txtVentanaCmd = new JTextArea(5, 100);
		txtVentanaCmd.setBounds(29, 365, 366, 290);
		contentPane.add(txtVentanaCmd);

		JButton btnCerrarApp = new JButton("Cerrar Aplicaci\u00F3n");
		btnCerrarApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				objDlgVentanaSalir.setVisible(true);
			}
		});
		btnCerrarApp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCerrarApp.setBounds(247, 679, 168, 37);
		contentPane.add(btnCerrarApp);
	}
	
	public JTextArea getTxtVentanaCmd() {
		return txtVentanaCmd;
	}
	
	public JList<String> getListProcess(){
		return listaProcesos;
	}
	
	public DefaultListModel<String> getListModel(){
		return listModel;
	}
}
