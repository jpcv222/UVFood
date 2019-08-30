/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 *
 * @author Juan Pablo Castro 2019 GitHub: jpcv222
 */
public class FileManage {

    String  ruta;
    private static Logs logs;

    public FileManage() {
        this.logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());
        ruta = null;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public String calcularRutaArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(fileChooser);
        String result_ruta = null;
        try {
            result_ruta = fileChooser.getSelectedFile().getAbsolutePath();
            this.setRuta(result_ruta);
        } catch (Exception e) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + e.getMessage() + " " + e.toString());
        }
        return result_ruta;
    }

    public static String selectFile(String ruta_alt) {
        String response;
        if (ruta_alt != null) {
            response = ruta_alt;
        } else {
            response = "Archivo CSV, ruta errónea.";
            logs.escribirErrorLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// Archivo CSV, ruta errónea.");
        }
        return response;
    }

    public static String readCSVFile(String url_file) {
        String response = "error.unknow";
        String archCSV = url_file;
        try {
            CSVReader csvReader = new CSVReader(new FileReader(archCSV));
            String[] fila = null;
            while ((fila = csvReader.readNext()) != null) {
                System.out.println(fila[0]
                        + " | " + fila[1]
                        + " |  " + fila[2]);
            }
            response = "success";
        } catch (IOException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
        } catch (NoClassDefFoundError ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            response = "error.noclassdeffounderror";
        } catch (NullPointerException ex) {
            logs.escribirExceptionLogs(Thread.currentThread().getStackTrace()[1].getMethodName() + "// " + ex.getMessage() + " " + ex.toString());
            response = "error.nullpointerexception";
        }
            return response;
        

    }

}
