package it.polito.s223833;

import java.io.File;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Controller 
{
	// the FXML button.
	@FXML private Button chooseOutputDirectory, ChooseFer2013File, Start;
	
	// the FXML label.
	@FXML private TextArea logTextArea;
	
	private Stage primaryStage=null;
	
	private String outputDirectory="", fer2013File="";
	
	public Controller()
	{
		
	}
	
	//Funzione di set dello stage principale.
	public void setStage(Stage stage)
	{
		primaryStage=stage;
	}
	
	//Funzione richiamata dal bottone chooseOutputDirectory.
	public void chooseDirectory()
	{
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Select Root Directory");
		directoryChooser.setInitialDirectory(
		  	new File(System.getProperty("user.home"))
		); 
		File selectedDirectory = directoryChooser.showDialog(primaryStage);
		if(selectedDirectory!=null)
		{
			outputDirectory=selectedDirectory.getAbsolutePath();
			logTextArea.setText(logTextArea.getText()+"\nOutput directory position: "+outputDirectory);
		}		
	}	
	
	//Funzione richiamata dal bottone ChooseFer2013File.
	public void chooseFile()
	{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Root Directory");
		fileChooser.setInitialDirectory(
		 	new File(System.getProperty("user.home"))
		); 
		File selectedFile = fileChooser.showOpenDialog(primaryStage);
		if(selectedFile!=null)
		{
			fer2013File=selectedFile.getAbsolutePath();
			logTextArea.setText(logTextArea.getText()+"\nFer2013 file position: "+fer2013File);		
		}		
	}
	
	//Funzione richiamata dal bottone Start.
	public void startWorkerThread()
	{
		//Se è stato selezionato il file csv e la directory di output istanzio un nuovo thread di preparazione dati.
		if(!fer2013File.equals("") && !outputDirectory.equals(""))
		{
			if(fer2013File.contains(".csv"))
			{
				//Disabilito tutti i pulsanti.
				chooseOutputDirectory.setDisable(true);
				ChooseFer2013File.setDisable(true);
		     	Start.setDisable(true);
		     	//Istanzio il worker thread.
				PrepareData pd=new PrepareData(this,fer2013File,outputDirectory);
				Thread workerThread = new Thread(pd);
				workerThread.start();
			}
			else
			{
				logTextArea.setText(logTextArea.getText()+"\nAttention: this is not a .csv file. Please choose the fer2013 .csv file.");		
			}
		}
	}
	
	//Funzione di scrittura testo.
	public void setText(String text)
	{
		logTextArea.appendText(text);
	}
	
	//Funzione di riabilitazione dei pulsanti.
	public void enableButton()
	{
		chooseOutputDirectory.setDisable(false);
		ChooseFer2013File.setDisable(false);
     	Start.setDisable(false);
	}
}
