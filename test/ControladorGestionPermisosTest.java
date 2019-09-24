
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
public class ControladorGestionPermisosTest {
    
    @Test
    public void validateAsignUserPermissions() {
        boolean resultado = managers.ControladorGestionPermisos.validateAsignUserPermissions(null);
        boolean esperado = true;
        assertEquals((Object) esperado, (Object) resultado);
    }
    
}
