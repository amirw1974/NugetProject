package boot;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;

public class Settings implements Serializable {
	String solvingAlgorithm;
	int numOfMaxThread;
	
	public Settings(String solvingAlgorithm, int numOfMaxThread) {
		this.solvingAlgorithm = solvingAlgorithm;
		this.numOfMaxThread = numOfMaxThread;
	}
	public Settings() {
		solvingAlgorithm = "DFS";
		numOfMaxThread = 5;
	}
	
	public String getSolvingAlgorithm() {
		return solvingAlgorithm;
	}
	public void setSolvingAlgorithm(String solvingAlgorithm) {
		this.solvingAlgorithm = solvingAlgorithm;
	}
	public int getNumOfMaxThread() {
		return numOfMaxThread;
	}
	public void setNumOfMaxThread(int numOfMaxThread) {
		this.numOfMaxThread = numOfMaxThread;
	}
	
	public void save()
	{
		try {
			XMLEncoder encoder= new XMLEncoder(new FileOutputStream("settings.xml"));
			encoder.writeObject(this);
			encoder.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void load()
	{
		XMLDecoder dencoder;
		try {
			dencoder = new XMLDecoder(new FileInputStream("settings.xml"));
			Settings s = (Settings) dencoder.readObject();
			this.numOfMaxThread = s.numOfMaxThread;
			this.solvingAlgorithm = s.solvingAlgorithm;
			dencoder.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
