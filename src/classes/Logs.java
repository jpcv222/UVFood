/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

 import java.io.*;
import java.time.LocalDateTime;
/**
 *
 * @author jpcv2
 */
public class Logs {
    
    private String directorioRaiz;
    private String exceptionLogsFile;
    private String errorLogsFile;
    private String accessLogsFile;
    private String className;
    
    public Logs(String  className) {
        this.className = className;
        this.directorioRaiz = System.getProperty("user.dir");
        this.exceptionLogsFile = directorioRaiz+"/src/files/logs/exception.txt";
        this.errorLogsFile = directorioRaiz+"/src/files/logs/error.txt";
        this.accessLogsFile = directorioRaiz+"/src/files/logs/access.txt";
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void escribirExceptionLogs(String message)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        LocalDateTime now = LocalDateTime.now();  
        try
        {
            fichero = new FileWriter(exceptionLogsFile, true);
            pw = new PrintWriter(fichero);
            pw.println(this.getClassName() + "//"+ message + "// "+ now);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }

        }
    }
    
     public void escribirErrorLogs(String message)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        LocalDateTime now = LocalDateTime.now();  
        try
        {
            fichero = new FileWriter(errorLogsFile, true);
            pw = new PrintWriter(fichero);
            pw.println(this.getClassName() + "//"+ message + "// "+ now);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }

        }
    }
     
     public void escribirAccessLogs(String message)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        LocalDateTime now = LocalDateTime.now();  
        try
        {
            fichero = new FileWriter(accessLogsFile, true);
            pw = new PrintWriter(fichero);
            pw.println(this.getClassName() + "//"+ message + "// "+ now);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }

        }
    }
    
    public static void archivosDirectorio(String directorioRaiz) {
    File carpeta = new File(directorioRaiz);
    if (carpeta.exists()) {
        File[] ficheros = carpeta.listFiles(); //Listar archivos en carpeta raiz
        for (File f : ficheros) {
            System.out.println(f.getName());
        }
    } else {

    }
}

}
