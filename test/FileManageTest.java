
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
    
     @Test
    public void testLogin() {
       String resultado = classes.FileManage.readCSVFile(null);
       String esperado = "success";
        assertEquals((Object) esperado, (Object) resultado);
       
    }
    
}
