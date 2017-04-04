package com.eval.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ParaReader {
	public String getPara(String path){
		if(path == null || path.trim().length() <= 0){
			path = "para.txt"; 
		}
		
		return readFile(path);
	}
	
	public String readFile(String path) {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;
        try {
        	Resource resource = new ClassPathResource(path);
        	reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null)
                builder.append(line);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
        	closeResource(reader);
        }
        return builder.toString();
    }

    private void closeResource(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException ioe) {
            	System.out.println("error while closing resource.");
            }
        }
    }

    public File getFile(String path) {
        return new File(path);
    }

}
