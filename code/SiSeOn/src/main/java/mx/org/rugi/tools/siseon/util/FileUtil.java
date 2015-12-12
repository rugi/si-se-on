/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.org.rugi.tools.siseon.util;

import java.io.*;

/**
 *
 * @author rugi
 */
public class FileUtil {

    /**
     * 
     */
    private static final String DIRECTORY_NAME = ".siseon";

    
    
    /**
     * 
     * @return 
     */
    public static String getUserHomeDirectory() {
        return System.getProperty("user.home").toString();
    }

    /**
     * 
     * @return 
     */
    public static String getWorkDirectory() {
        //validamos
        String workDirectory = getUserHomeDirectory() + File.separatorChar + DIRECTORY_NAME;
        File temp = new File(workDirectory);
        if (!temp.exists()) {
            boolean res = temp.mkdir();
            if (!res) {
                throw new RuntimeException("No se pudo crear la carpeta de trabajo");
            }
        }
        //boolean r = new File().
        return workDirectory;
    }

    /**
     * 
     * @param fileName
     * @return 
     */
    public static boolean exist(String fileName) {
        String dirTarger = fileName;
        File temp = new File(dirTarger);
        boolean res = false;
        if (temp.exists()) {
            res = true;
        } 
        return res;
    }  
    
    /**
     * 
     * @param fileName
     * @return 
     */
    public static boolean existOrCreate(String fileName) {
        String dirTarger = fileName;
        File temp = new File(dirTarger);
        boolean res = false;
        if (!temp.exists()) {
            try {
                res = temp.createNewFile();
            } catch (IOException ex) {
                res = false;
            }
        } else {
            res = true;
        }
        return res;
    }

 
    
  
 
     
    
    public static long getSizeFile(String file) {
        long r = -1;
        if (file == null || file.trim().length() == 0) {
            r = -1;
        } else {
            File f = new File(file);
            if (f.exists()) {
                r = f.length();
            } else {
                r = -1;
            }
        }
        return r;
    }
    
    public static long getSizeDirectory(String dir){
    
          long r = -1;
        if (dir == null || dir.trim().length() == 0) {
            r = -1;
        } else {
            File f = new File(dir);
            if (f.exists()) {
               if(f.isDirectory()){
                   File[] fs = f.listFiles();                   
                   for (int i = 0; i < fs.length; i++) {                       
                       r+=fs[i].length();
                   }
               }else{
                   //es archivo.
                   r = f.length();
               }
            } else {
                r = -1;
            }
        }
        return r;  
    }
        
}
