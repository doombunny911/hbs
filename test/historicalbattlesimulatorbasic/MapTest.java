/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package historicalbattlesimulatorbasic;

import java.awt.image.BufferedImage;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Edward
 */
public class MapTest {
    
    public MapTest() {
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
     * Test of main method, of class Map.
     */
    @Test
    public void testMain() throws Exception {
        System.out.println("main");
        String[] args = null;
        Map.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateTiles method, of class Map.
     */
    @Test
    public void testGenerateTiles() {
        System.out.println("generateTiles");
        Map instance = null;
        instance.generateTiles();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertTile method, of class Map.
     */
    @Test
    public void testInsertTile() {
        System.out.println("insertTile");
        Tile insertTile = null;
        int x = 0;
        int y = 0;
        Map instance = null;
        instance.insertTile(insertTile, x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTileAllDirections method, of class Map.
     */


    /**
     * Test of setTileTerrain method, of class Map.
     */
    @Test
    public void testSetTileTerrain() {
        System.out.println("setTileTerrain");
        Tile t = null;
        BufferedImage img = null;
        Map instance = null;
        instance.setTileTerrain(t, img);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveMap method, of class Map.
     */
    @Test
    public void testSaveMap() throws Exception {
        System.out.println("saveMap");
        String fileName2 = "";
        Map instance = null;
        instance.saveMap(fileName2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
