package base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextNote extends Note {

	public String content;
	
    public TextNote(String content) {
        super(content);
    }
    
    public TextNote(String title, String content) {
        super(title);
        this.content = content;
    }
    
    public TextNote(File f) {
    	super(f.getName());
    	this.content = getTextFromFile(f.getAbsolutePath());
    }
    
    // Lab 3
    public String getContent() { return content; }
        
    // Lab 5
    private String getTextFromFile(String absolutePath) {
    	String result = "";
    	String line = "";
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
    	// TODO
    	try {
    		fis = new FileInputStream(absolutePath);
    		isr = new InputStreamReader(fis);
    		br = new BufferedReader(isr);
    		while((line = br.readLine()) != null) {
    			result += line;
    			System.out.println(result);
    		}
    		br.close();
    		isr.close();
    		fis.close();
    	}catch (FileNotFoundException e) {
    		e.printStackTrace();
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    	return result;
    }
    
    public void exportTextToFile(String pathFolder) {
    	String fileTitle = "";
		FileWriter fw = null;
		BufferedWriter bw = null;
		
    	try {
    		fileTitle = this.getTitle().replaceAll(" ", "_");
    		File file = new File(pathFolder + fileTitle + ".txt");
    		if (!file.exists()) {
    			file.createNewFile();
    		}
    		fw = new FileWriter(file);
    		bw = new BufferedWriter(fw);
    		bw.write(content);
    		bw.close();
    		fw.close();
    	}catch (IOException e) {
    		e.printStackTrace();
    	}
    }

}
