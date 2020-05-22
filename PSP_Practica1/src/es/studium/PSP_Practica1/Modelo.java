package es.studium.PSP_Practica1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextArea;

public class Modelo {
	Process process;
	Process blocNota;
	Process paint;
	Process programaGestion;
	Process juego;

	// Ejecuta un proceso seleccionado
	public void ejecProceso(String proceso, DefaultListModel<String> listaProcesos){
		Runtime runtime = Runtime.getRuntime();
		try {
			process = runtime.exec(proceso);
			listaProcesos.addElement(proceso);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

	//Finaliza un proceso seleccionado
	public void finalProceso(JList<String> listaProcesos, Process proceso) { 
		String procesoSelec = listaProcesos.getSelectedValue();
		if(procesoSelec == "notepad") {
			blocNota.destroy();
		}
		else if(procesoSelec == "mspaint") {
			paint.destroy();
		}
		else if(procesoSelec == "java -jar programaGestion.jar") {
			programaGestion.destroy();
		}
		else if(procesoSelec == "java -jar juegoSopaLetra.jar") {
			juego.destroy();
		}
	}

	//Ejecuta un comando cmd
	public void ejecCmd(String txtCmd, JTextArea txtVentanaCmd) {
		try {
			ProcessBuilder pb = new ProcessBuilder().command("cmd.exe","/c ", txtCmd);
			process = pb.start();
			process.waitFor();
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String entrada = null;
			String salida = "";
			while((entrada = br.readLine()) != null){
				salida = salida + entrada + "\n";
			}
			txtVentanaCmd.setText(salida);
			br.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}