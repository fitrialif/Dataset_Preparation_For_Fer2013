# Dataset Preparation For Fer2013

<p align="center">
	Screenshot of the Dataset Preparation For Fer2013 program.
   <img src="https://github.com/AnthonyMarc/Dataset_Preparation_For_Fer2013/blob/master/resources/dpFer2013.png"> 
</p>

Description available in english and italian below.


## English
This program is the Dataset Preparation software version that allows you to prepare the Fer2013 dataset for training a neural network capable of recognizing human emotions.
The Fer2013 dataset is distributed as a .csv file, so the Dataset Preparation for Fer2013 program does the following:

- cycles on every single line of the file;
- read the values ​​of the individual pixels and assign them to a new image;
- equalize the histogram of the image in order to improve the contrast;
- save the image in a different folder depending on the type of emotion and its use (training, validation or test).

In order to work, in the same folder in which the .jar file is created it is also necessary to insert a lib folder (in case you can copy the one in this repository) containing the OpenCV library, the respective .dll and the file of the Haar Detection in format .xml.

The program has been developed and tested on the Windows platform: to run in Linux or Mac you need to modify the OpenCV library.

The Fer2013 is a dataset consisting of a total of 35887 grayscale photos of 48x48 size, prepared by Pierre-Luc Carrier and Aaron Courville as part of a research project.
It was made available, as a preliminary version, for the "Challenges in Representation Learning: Facial Expression Recognition Challenge" workshop associated with the ICML 2013 (International Conference on Machine Learning)} and can be freely downloaded from Kaggle, upon registration, at the following link:
https://www.kaggle.com/c/challenges-in-representation-learning-facial-expression-recognition-challenge/data

At this link you can find the Dataset Preparation software version for the CK+ dataset:
https://github.com/AnthonyMarc/Dataset_Preparation_For_CK


## Italiano
Questo programma è la versione del software Dataset Preparation che consente di preparare il dataset Fer2013 per l'addestramento di una rete neurale capace di riconoscere le emozioni umane.
Il dataset Fer2013 è distribuito sotto forma di file .csv, pertanto il programma Dataset Preparation for Fer2013 effettua le seguenti operazioni:

- cicla su ogni singola riga del file;
- legge i valori dei singoli pixel e li assegna ad una nuova immagine;
- equalizza l'istogramma dell'immagine in modo da migliorarne il contrasto;
- salva l'immagine in una cartella differente a seconda del tipo di emozione e al suo utilizzo (addestramento, validazione o test).

Per poter funzionare, nella stessa cartella in cui viene creato il file .jar occorre anche inserire una cartella lib (nel caso si può copiare quella presente in questo repository) contenente la libreria di OpenCV, la rispettiva .dll e il file della Haar Detection in formato .xml.

Il programma è stato sviluppato e testato su piattaforma Windows: per girare in ambiente Linux o Mac occorre modificare la libreria relativa ad OpenCV.

Il Fer2013 è un dataset composto da un totale di 35887 foto in scala di grigi di dimensione 48x48, preparato da Pierre-Luc Carrier e Aaron Courville come parte di un progetto  di ricerca. 
Esso è stato reso disponibile, in versione preliminare, per il workshop "Challenges in Representation Learning: Facial Expression Recognition Challenge" associato all'ICML 2013 (International Conference on Machine Learning)} ed è scaricabile liberamente da Kaggle, previa registrazione, al seguente link: 
https://www.kaggle.com/c/challenges-in-representation-learning-facial-expression-recognition-challenge/data

A questo link è presente la versione del software Dataset Preparation per il dataset CK+:
https://github.com/AnthonyMarc/Dataset_Preparation_For_CK