/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Product;
import java.util.Arrays;
import java.util.Collection;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 *
 * @author patmo016
 */


@RunWith(Parameterized.class)
public class DaoJUnitTest {

    
    //daoInterface newdao = new ProductDataStorage();  
   daoInterface newdao;
   // daoInterface newdao = new DbStorage("jdbc:h2:tcp://localhost:9060/project-testing;IFEXISTS=TRUE");
    private Product prodOne;
    private Product prodTwo;
    
   public DaoJUnitTest(daoInterface testdao){
        this.newdao=testdao;
     }
 
    
    @Parameterized.Parameters
    public static Collection<?> daoObjectsToTest() {
        return Arrays.asList(new Object[][]{
            {new ProductDataStorage()},       
            {new DbStorage( "jdbc:h2:tcp://localhost:9060/project-testing;IFEXISTS=TRUE")}
        });
    }

    @Test
    public void testGetCategories() {
            // call getAll
       // Collection<Product> products = newdao.getProducts();
        Collection<String> categories = newdao.getCatagories();

// ensure the result includes the test products
        assertTrue("cat1 should exist", categories.contains("cat1"));
        assertTrue("cat2 should exist", categories.contains("cat2"));
// ensure the result ONLY includes the test products
        assertEquals("Only 2 categories in result", 2, categories.size());
// nd prodOne − result is generic collection, so we have to sequentially search for it
        for (String c : categories) {
            if (c.equals(prodOne.getCategory())) {
// ensure that all of the details were correctly retrieved
                assertEquals(prodOne.getCategory(), c);
                
                
            }else if (c.equals(prodTwo.getCategory())){
                assertEquals(prodTwo.getCategory(),c);
            }
        }
    
    }
    @Test
    public void testFilterByCategory(){
     //Collection<Product> products = newdao.getProducts();
        
        Collection<Product> categories = newdao.filterByCatagory("cat1");
        assertTrue("cat1 should exist", categories.contains(prodOne));
        assertEquals("only 1 category in result", 1, categories.size());
        
        for (Product c: categories){
                assertEquals(prodOne.getProductID(),c.getProductID());
                
                assertEquals(prodOne.getName(), c.getName());
                assertEquals(prodOne.getDescription(), c.getDescription());
                assertEquals(prodOne.getCategory(), c.getCategory());
                
                assertEquals(prodOne.getPrice(), c.getPrice(), 0.01);
                assertEquals(prodOne.getQuantityStock(), c.getQuantityStock(), 0.01);
            
        }      
      }  
    
    @Test
    public void testDaoSaveAndDelete() {
// create product for testing
        Product savedProd = new Product(3, "name", "desc", "cat90", 1.0, 2.0);
// save the product using DAO
        newdao.saveProduct(savedProd);
// retrieve the same product via DAO
        Product retrieved = newdao.searchID(3);
// ensure that the product we saved is the one we got back
        assertEquals("Retrieved product should be the same as the saved one",
                   savedProd, retrieved);
// edit the produt to see if still saving correctly
//        Product editProduct = new Product (390, "changedName", "change", "cat0", 1.0, 2.0);
//        newdao.saveProduct(retrieved);
//        Product newretrieved = newdao.searchID(390);       
//        assertEquals("New retrieved product should be the same as the new one", retrieved, newretrieved);       
//delete the product via the DAO
        newdao.deleteProduct(savedProd);
      //  newdao.deleteProduct(retrieved);
        
// try to retrieve the deleted product
        retrieved = newdao.searchID(390);
// ensure that the student was not retrieved (should be null)
        assertNull("Product should no longer exist", retrieved);
    }
    
    @Test
    public void testDaoGetAll() {
// call getAll
        Collection<Product> products = newdao.getProducts();
// ensure the result includes the test products
        assertTrue("prodOne should exist", products.contains(prodOne));
        assertTrue("prodTwo should exist", products.contains(prodTwo));
// ensure the result ONLY includes the test products
        assertEquals("Only 2 products in result", 2, products.size());
// nd prodOne − result is generic collection, so we have to sequentially search for it
        for (Product p : products) {
            if (p.equals(prodOne)) {
// ensure that all of the details were correctly retrieved
                assertEquals(prodOne.getProductID(), p.getProductID());
                
                assertEquals(prodOne.getName(), p.getName());
                assertEquals(prodOne.getDescription(), p.getDescription());
                assertEquals(prodOne.getCategory(), p.getCategory());
                
                assertEquals(prodOne.getPrice(), p.getPrice(), 0.01);
                assertEquals(prodOne.getQuantityStock(), p.getQuantityStock(), 0.01);
            }
        }
    }
@Test
    public void testDaoSearchId() {
// get prodOne using findById method
        Product p = newdao.searchID(1);
// ensure that you got back prodOne, and not another product
        assertEquals("Products should have equal ID's", prodOne, p);
// ensure that prodOne's details were properly retrieved
        assertEquals(prodOne.getProductID(), p.getProductID());

        assertEquals(prodOne.getName(), p.getName());
        assertEquals(prodOne.getDescription(), p.getDescription());
        assertEquals(prodOne.getCategory(), p.getCategory());

        assertEquals(prodOne.getPrice(), p.getPrice(), 0.01);
        assertEquals(prodOne.getQuantityStock(), p.getQuantityStock(), 0.01);

// call findById using a non−existent ID
        Product nullProd = newdao.searchID(943867);

// ensure that the result is null
        assertEquals("product should be null", nullProd, null);
    }
        
    @Test
    public void testEditSave(){
       Collection<Product> products= newdao.getProducts();
        // create product for testing
        Product temp = new Product(prodOne.getProductID(), "CHANGEDname", "CHANGEDdescription", prodOne.getCategory(), 1.0, 2.0);
        prodOne = temp;

// save the product using DAO
        newdao.saveProduct(prodOne);
// retrieve the same product via DAO
        Product retrieved = newdao.searchID(1);
// ensure that the product we saved is the one we got back
        assertEquals("Retrieved product should be the same as the saved one",
                   prodOne, retrieved);
        assertEquals("Size of dao should not change", 2, products.size());
        assertEquals("CHANGEDname", retrieved.getName());
        assertEquals("CHANGEDdescription", retrieved.getDescription());
    }

   
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        // rst test product
        this.prodOne = new Product(1, "prod1","description1","cat1", 11.0, 22.0);
        // second test product
        this.prodTwo = new Product(2, "name2", "description2", "cat2", 33.0, 44.0);
        // save the products
        newdao.saveProduct(prodOne);
        newdao.saveProduct(prodTwo);
    }
    
    @After
     public void tearDown() {
        newdao.deleteProduct(prodOne);
        newdao.deleteProduct(prodTwo);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
