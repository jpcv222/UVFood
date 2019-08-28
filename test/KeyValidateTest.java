
import classes.Logs;
import  static org.junit.Assert.assertEquals;
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
public class KeyValidateTest {
    
    private static Logs logs;

    public KeyValidateTest() {
        this.logs = new Logs(Thread.currentThread().getStackTrace()[1].getClassName());
    }


    @Test
    public void haveKeyTest() {
        String resultado = managers.queries.KeyValidate.haveKey(null,-99);
        String esperado = "error.notfound_key";
        assertEquals((Object) esperado, (Object) resultado);
    }
    
    
    @Test
    public void resultHaveKeyTest() {
        boolean resultado = managers.queries.KeyValidate.resultHaveKey(null);
        boolean esperado = false;
        assertEquals((Object) esperado, (Object) resultado);
    }
    
}
