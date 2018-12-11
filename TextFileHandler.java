package Eliza;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class TextFileHandler implements TextFileIOable {
	
	int numTabs = 0;

	@Override
	public void createNewFile(String fileName) {
		PrintWriter outStream = null;
		try{
			outStream = new PrintWriter(fileName);
			
		}catch(FileNotFoundException e){
			System.out.println("Sorry could not create the file "+ fileName);
			e.printStackTrace();
		}
		finally{
			if(outStream != null){
				outStream.close();
			}
			System.out.println("all done check your file system");
		}
		
	}

	@Override
	public void writeToNewFile(String fileName, String text) {
		PrintWriter outStream = null;
		try{
			outStream = new PrintWriter(fileName);
			outStream.println(text);//write the text to the file and add the new line character after
		}catch(FileNotFoundException e){
			System.out.println("Sorry could not create/write to the file "+ fileName);
			e.printStackTrace();
		}
		finally{
			if(outStream != null){
				outStream.close();
			}
			System.out.println("all done check your file system");
		}
	}

	@Override
	public void appendToFile(String fileName, String text) {
		PrintWriter outStream = null;
		try{
//			outStream = new PrintWriter(fileName);
			outStream = new PrintWriter(new FileOutputStream(fileName, true));
			outStream.println(text);//write the text to the file and add the new line character after
		}catch(FileNotFoundException e){
			System.out.println("Sorry could not create/write to the file "+ fileName);
			e.printStackTrace();
		}
		finally{
			if(outStream != null){
				outStream.close();
			}
			System.out.println("all done check your file system");
		}
	}

	@Override
	public boolean deleteFile(String fileName) {
		File fileObj = new File(fileName);
		
		if(fileObj.exists()) {
			if(fileObj.delete()) {
				System.out.println("The file "+fileName+" was deleted.");
				return true;
			}
		}else {
			System.out.println("The file "+fileName+" was not deleted or does not exist.");
		}
		return false;
	}

	@Override
	public boolean copyFile(String fileName) {
//		Scanner inStream = null;
//		PrintWriter outStream = null;
//		try {
//			inStream = new Scanner(new File(fileName));
//			outStream = new PrintWriter(new File())
//		}catch(FileNotFoundException e){
//			e.printStackTrace();
//		}finally {
//			if(inStream != null) {
//				inStream.close();
//			}
//			if(outStream != null) {
//				outStream.close();
//			}
//		}
		
		
		return false;
	}

	@Override
	public boolean copyFile(String fileName, String fileNameOtherFile) {
		Scanner inStream = null;
		PrintWriter outStream = null;
		try {
			inStream = new Scanner(new File(fileName));
			outStream = new PrintWriter(new File(fileNameOtherFile));
			while(inStream.hasNextLine()) {
				String lineIn = inStream.nextLine();
				char c1 = lineIn.charAt(0);
				String lineOut = Character.toUpperCase(c1) + lineIn.substring(1);
				outStream.println(lineOut);
				
			}
			
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}finally {
			if(inStream != null) {
				inStream.close();
			}
			if(outStream != null) {
				outStream.close();
			}
			System.out.println("Go check your file system");
		}
		return false;
	}

	@Override
	public String readFile(String fileName) {
		String fileContents = "";
		Scanner inStream  = null;
		try {
			File theFileObject = new File(fileName);
			 inStream = new Scanner(theFileObject);
			 while(inStream.hasNextLine()){
				 fileContents += inStream.nextLine() +"\n";//get a line from the file append to the String 
			 }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("oops could not read file "+fileName);
			e.printStackTrace();
		}
		finally{
			if(inStream != null){
				inStream.close();
			}
		}
		
		return fileContents;
	}
	
	public String[] readFileArray(String fileName) {
		String[] fileContents = new String[20];
		Scanner inStream  = null;
		int n = 0;
		
		try {
			File theFileObject = new File(fileName);
			 inStream = new Scanner(theFileObject);
			 while(inStream.hasNextLine()){
				 fileContents[n] = inStream.nextLine();//get a line from the file append to the String array
				 n++;
				 if(n == fileContents.length) {
					 fileContents = extend(fileContents);
				 }
			 }
		} catch (FileNotFoundException e) {
			System.err.println("oops could not read file "+fileName);
			e.printStackTrace();
		}
		finally{
			if(inStream != null){
				inStream.close();
			}
		}
		
		return fileContents;
	}
	
	public String[] extend(String[] arr) {
		String[] newArr = new String[arr.length*2];
		for(int i = 0; i < arr.length; i++) {
			newArr[i] = arr[i];
		}
		return newArr;
	}

	@Override
	public boolean findAndReplaceFileContent(String fileName, String origText, String replacementText) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String readDelimitedFile(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String readDelimitedFile(String fileName, String delimiter) {
		Scanner inStream = null;
		String token = null;
		String fileContent = "";
		try {
			File theFile = new File(fileName);
			if(theFile.exists() && theFile.canRead()) {
				inStream = new Scanner(theFile);
				inStream.useDelimiter(delimiter);
				while(inStream.hasNext()) {
					token = inStream.next();
					System.out.println(token); // print to console
					fileContent += token + "\n";
				}
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(inStream != null) {
				inStream.close();
			}
			System.out.println("The content is: \n"+fileContent);
		}
		return fileContent;
	}
	
	public String readFileFromFileChooser() {
		JFileChooser jfc = new JFileChooser();
		int yesNo = jfc.showDialog(null, null);
		if(yesNo == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			if(file.isFile()) {
				return readFile(file.getAbsolutePath());
			}
		}
		return null;
	}
	
	public void recursivelyPrintAllDirAndFileNames(String fileName) {
		for(int i = 0; i < numTabs; i++) {
			System.out.print("  ");
		}
		File fileObj = new File(fileName);
		if(fileObj.exists()) {
			numTabs++;
			if(fileObj.isFile()) {
				System.out.println("\tFILE: "+fileName); // base case
			}else {
				System.out.println("DIR: "+fileName);
				File[] files = fileObj.listFiles();
				for(int i = 0; i < files.length; i++) {
					recursivelyPrintAllDirAndFileNames(files[i].getAbsolutePath());
				}
			}
			numTabs--;	
		}
	}
	
}
