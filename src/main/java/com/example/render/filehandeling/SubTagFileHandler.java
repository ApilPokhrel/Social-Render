package com.example.render.filehandeling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;



@Service
public class SubTagFileHandler {

	File f = new File("maintag.ser");

	
	//Write SubTagFile
	public void writeSubTagFiles() throws FileNotFoundException, IOException, ClassNotFoundException {
		

		ObjectInputStream obj2 = new ObjectInputStream(new FileInputStream(f));
		
		ArrayList<String> arr = (ArrayList<String>) obj2.readObject();
		
		
		
      ObjectMapper objectMapper = new ObjectMapper();
	  Map<String,ArrayList<String>> tagData = new HashMap<String,ArrayList<String>>(); 

		arr.forEach(new Consumer<String>(){

			
			
			@Override
			public void accept(String t) {
				// TODO Auto-generated method stub
				
				
			  ArrayList<String> ars = new ArrayList<String>();
			  ars.add("put your self");
			  
			  tagData.put(t, ars);
			  
				
			}
			
			
		});
		
		
		objectMapper.writeValue(new File("subtags.json"), tagData); 

		
	};
	
	
	
	
	
	//Read SubTagFile
	public  Map<String,ArrayList<String>> readSubTagFile() throws FileNotFoundException, IOException, ClassNotFoundException{
		
		  ObjectMapper objectMapper = new ObjectMapper();
		  Map<String,ArrayList<String>> tagData = new HashMap<String,ArrayList<String>>(); 
      
		   tagData = objectMapper.readValue(new File("subtags.json"), Map.class);
		
		
		
		return tagData;
		};
	
	
	
	//Update SubTagFile
	public void updateSubTagFile(String tagname) throws FileNotFoundException, IOException, ClassNotFoundException {
		ArrayList<String> arr = null;

		ObjectInputStream obj = new ObjectInputStream(new FileInputStream(f));
		
		arr = (ArrayList<String>) obj.readObject();
		
		arr.add(tagname);
		
		ObjectOutputStream obj2 = new ObjectOutputStream(new FileOutputStream(f));
		obj2.writeObject(arr);
		obj2.close();
	};
	
	
	
	//Delete MainTagFile
		public void deleteMainTagFile(int index) throws FileNotFoundException, IOException, ClassNotFoundException {
			
			ArrayList<String> arr = null;

			ObjectInputStream obj = new ObjectInputStream(new FileInputStream(f));
			
			arr = (ArrayList<String>) obj.readObject();
			
	        arr.remove(index);
	        
			ObjectOutputStream obj2 = new ObjectOutputStream(new FileOutputStream(f));
			obj2.writeObject(arr);
			obj2.close();
		};
}
