/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

 import java.io.*;
/**
 *
 * @author jpcv2
 */
public class Ficheros {
    
    private String directorioRaiz;
    private String exceptionLogsFile;
    public Ficheros() {
        this.directorioRaiz = System.getProperty("user.dir");
        this.exceptionLogsFile = directorioRaiz+"/src/logs/exception.txt";
    }

    public void escribirExceptionLogs(String message)
    {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(exceptionLogsFile);
            pw = new PrintWriter(fichero);

            for (int i = 0; i < 10; i++)
                pw.println("Linea " + i);

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
