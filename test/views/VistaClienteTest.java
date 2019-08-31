/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import javax.swing.JLabel;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adminis
 */
public class VistaClienteTest {
    
    public VistaClienteTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of hora method, of class VistaCliente.
     */
    @Test
    public void testHora() {
        System.out.println("hora");
        VistaCliente instance = new VistaCliente();
        instance.hora();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of run method, of class VistaCliente.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        VistaCliente instance = new VistaCliente();
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fecha method, of class VistaCliente.
     */
    @Test
    public void testFecha() {
        System.out.println("fecha");
        String expResult = "";
        String result = VistaCliente.fecha();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetColor method, of class VistaCliente.
     */
    @Test
    public void testResetColor() {
        System.out.println("resetColor");
        JLabel item = null;
        String image = "";
        VistaCliente instance = new VistaCliente();
        instance.resetColor(item, image);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setColor method, of class VistaCliente.
     */
    @Test
    public void testSetColor() {
        System.out.println("setColor");
        JLabel item = null;
        VistaCliente instance = new VistaCliente();
        instance.setColor(item);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeImage method, of class VistaCliente.
     */
    @Test
    public void testChangeImage() {
        System.out.println("changeImage");
        String nombreNueva = "";
        JLabel label = null;
        VistaCliente instance = new VistaCliente();
        instance.changeImage(nombreNueva, label);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class VistaCliente.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        VistaCliente.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
