
import classes.Logs;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jpcv2
 */
public class FileManageTest {

    private static Logs logs;

    public FileManageTest() {
        this.logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());
    }

    @Test(expected = NullPointerException.class)
    public void ReadCSVFileTest() {
        String resultado = classes.FileManage.readCSVFile(null);
    }

    @Test
    public void selectFileTest() {
        String resultado = classes.FileManage.selectFile(null);
        String esperado = "Archivo CSV, ruta errónea";
        assertEquals((Object) esperado, (Object) resultado);
    }

}
