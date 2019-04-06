package it.polito.s223833;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javafx.application.Platform;

import org.opencv.core.CvType;


public class PrepareData implements Runnable
{
	//Riferimento al controller
	private Controller controller;
	
	//Dimensione immagine;
	private int width = 48;
	private int height = 48;
	
	//Contatore di immagini.
	int imageCounter=0;
	
	//Stringa contenente il path del dataset fer2013 in formato .csv.
	private String fer2013File;
	//Stringa contenente la cartella di output dove salvare le immagini.
	private String outputDirectory;
	
	@Override
	public void run() 
	{
		createImagesFromCSV();	
	}	

	public PrepareData(Controller controller, String fer2013File, String outputDirectory)
	{
		this.controller = controller;		
		this.fer2013File=fer2013File;
		this.outputDirectory=outputDirectory;
	}
	
	//Funzione che crea le immagini a partire dal file .csv.
	public void createImagesFromCSV()
	{ 
		//Aggiorno il log.
		Platform.runLater( () -> controller.setText("\nStart creating images...\n"));				
        String line = "";
		BufferedReader br;
		int j,k;
		
		//Creazione della cartelle di training, validation e test.
		File trainingDirectory = new File(outputDirectory, "Training");
		trainingDirectory.mkdir();
		File validationDirectory = new File(outputDirectory, "Validation");
		validationDirectory.mkdir();
		File testDirectory = new File(outputDirectory, "Test");
		testDirectory.mkdir();
		//Creazione delle sottocartelle di training, validation e test.
		CreateClassificationSubfolders(trainingDirectory);
		CreateClassificationSubfolders(validationDirectory);
		CreateClassificationSubfolders(testDirectory);
		
		try 
		{
			br = new BufferedReader(new FileReader(fer2013File));
			//Salto la prima riga.
			line = br.readLine();
			//Ciclo.
			while ((line = br.readLine()) != null) 
			{
			    String[] firstSplit = line.split(",");
			    String[] secondSplit = firstSplit[1].split(" ");
			    //Calcolo la dimensione della foto.
			    int size=secondSplit.length;
			    //Mi assicuro che le immagini siano di dimensione 48*48.
			    if(size == width*height)
			    {			    
				    //Creo un'immagine dal ritaglio del volto.
					Mat image = Mat.eye(width,height,CvType.CV_8UC1);
					for(int i=0;i<size; i++)
					{
						//Calcolo la riga relativa al valore corrente.
						j=i/width;
						//Calcolo la colonna relativa al valore corrente.
						k=i-(width*j);
						//Setto il valore del pixel (j,k).
						image.put(j, k, Integer.parseInt(secondSplit[i]));
					}
					//Equalizzo l'istogramma dell'immagine.
					Imgproc.equalizeHist(image, image);
					//Salvataggio della foto.
					//Training case.
					if (firstSplit[2].equals("Training")) 
					{
						SaveImage(trainingDirectory,Integer.parseInt(firstSplit[0]), image);
					}
					//Validation case.
					else if (firstSplit[2].equals("PublicTest")) 
					{
						SaveImage(validationDirectory,Integer.parseInt(firstSplit[0]), image);
					}
					//Test case.
					else 
					{
						SaveImage(testDirectory,Integer.parseInt(firstSplit[0]), image);
					}
					image.release();
				}
			    else
			    {
				    System.out.println("diverso da 2304!"+secondSplit.length);
			    }
			}
			br.close();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			Platform.runLater( () -> {controller.setText("There was a problem while trying to read the fer2013.csv file.\n"); controller.enableButton();});		
		}		
		//Aggiorno il log e riabilito il button.
		Platform.runLater( () -> {controller.setText("Images created successfully.\n"); controller.enableButton();});				
	}
	
	/*Funzione di creazione delle directory secondarie.*/
	private void CreateClassificationSubfolders(File directory)
	{
		File dir1 = new File(directory, "Anger");
		dir1.mkdir();
		File dir2 = new File(directory, "Disgust");
		dir2.mkdir();
		File dir3 = new File(directory, "Fear");
		dir3.mkdir();
		File dir4 = new File(directory, "Happiness");
		dir4.mkdir();
		File dir5 = new File(directory, "Neutrality");
		dir5.mkdir();
		File dir6 = new File(directory, "Sadness");
		dir6.mkdir();
		File dir7 = new File(directory, "Surprise");
		dir7.mkdir();
	}
	
	/*Funzione di salvataggio delle immagini nella cartella corrispondente.*/
	private void SaveImage(File directory, int emotion, Mat image)
	{
		//Anger.
		if (emotion==0)
		{
			Imgcodecs.imwrite(directory + "\\Anger\\" + imageCounter + ".jpg", image);
		}
		//Disgust.
		if (emotion==1) 
		{
			Imgcodecs.imwrite(directory + "\\Disgust\\" + imageCounter + ".jpg", image);
		}
		//Fear.
		if (emotion==2) 
		{
			Imgcodecs.imwrite(directory + "\\Fear\\" + imageCounter + ".jpg", image);
		}
		//Happiness.
		if (emotion==3) 
		{
			Imgcodecs.imwrite(directory + "\\Happiness\\" + imageCounter + ".jpg", image);
		}
		//Sadness.
		if (emotion==4) 
		{
			Imgcodecs.imwrite(directory + "\\Sadness\\" + imageCounter + ".jpg", image);
		}
		//Surprise.
		if (emotion==5) 
		{
			Imgcodecs.imwrite(directory + "\\Surprise\\" + imageCounter + ".jpg", image);
		}
		//Neutrality.
		if (emotion==6) 
		{
			Imgcodecs.imwrite(directory + "\\Neutrality\\" + imageCounter + ".jpg", image);
		}
		//Incremento del contatore del numero di immagini totali.
		imageCounter++;
	}
}
