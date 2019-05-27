package com.example.render.filehandeling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class MainTagFileHandler {

	File f = new File("maintag.ser");

	
	
	//Write MainTagFile
	public void writeMainTagFile() throws FileNotFoundException, IOException {
		
		ArrayList<String> aro = new ArrayList<>();
		aro.add("fame");
		aro.add("Buisness");
		aro.add("Entertainment");
		aro.add("Materials");
		aro.add("Educational");
		aro.add("Science");
		aro.add("Social");
		aro.add("UpAndLow");
		aro.add("Sports");
		aro.add("Religious");
		aro.add("Technology");
		
                StringBuilder str = new StringBuilder();
                str.append("apil pokharel"); 
                   
              
                
		
                
		ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(f));
		obj.writeObject(aro); 
		obj.close();
	}
	
	
	
	//Read MainTagFile
	public ArrayList<String> readMainTagFile() throws FileNotFoundException, IOException, ClassNotFoundException{
		
		ArrayList<String> arr = null;

		
		ObjectInputStream obj = new ObjectInputStream(new FileInputStream(f));
		
		arr = (ArrayList<String>) obj.readObject();
		
		
		
		return arr;
	}
	
	
	
	//Update MainTagFile
	public void updateMainTagFile(String tagname) throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<String> arr = null;

		ObjectInputStream obj = new ObjectInputStream(new FileInputStream(f));
		
		arr = (ArrayList<String>) obj.readObject();
		
		arr.add(tagname);
		
		ObjectOutputStream obj2 = new ObjectOutputStream(new FileOutputStream(f));
		obj2.writeObject(arr);
		obj2.close();
		
	}
	
	
	
	
	//Delete MainTagFile
	public void deleteMainTagFile(int index) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		ArrayList<String> arr = null;

		ObjectInputStream obj = new ObjectInputStream(new FileInputStream(f));
		
		arr = (ArrayList<String>) obj.readObject();
		
        arr.remove(index);
        
		ObjectOutputStream obj2 = new ObjectOutputStream(new FileOutputStream(f));
		obj2.writeObject(arr);
		obj2.close();
	}
	
	
	
}
