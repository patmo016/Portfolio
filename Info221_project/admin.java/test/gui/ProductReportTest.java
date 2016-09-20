/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.daoInterface;
import domain.Product;
import gui.helpers.SimpleListModel;
import java.util.Collection;
import java.util.TreeSet;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.junit.After;
import org.junit.Before;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import static org.assertj.swing.core.matcher.DialogMatcher.withTitle;
import static org.assertj.swing.core.matcher.JButtonMatcher.withText;
import org.assertj.swing.fixture.DialogFixture;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

/**
 *
 * @author patmo016
 */
public class ProductReportTest {

    private daoInterface dao;
    private DialogFixture fixture;
    private Robot robot;
    private int size;
    
    private Product prodOne;
    private Product prodTwo;
    private Product prodThree;
    
    @Before
    public void setUp() {
       // ListMultimap<String,Product> catagoryProducts= ArrayListMultimap.create();
        this.prodOne = new Product(1, "prodOne", "description1", "cat1", 11.0, 22.0);
        // second test product
        this.prodTwo = new Product(2, "prodTwo", "description2", "cat2", 33.0, 44.0);
        this.prodThree = new Product (3, "prodThree", "description3", "cat1", 34.00, 12.00);
        //create new Robot
        robot = BasicRobot.robotWithNewAwtHierarchy();
        // slow down the robot a bit - default is 30
        robot.settings().delayBetweenEvents(100);

        // add some catagories for testing with
        Collection<String> catagories = new TreeSet<>();
        catagories.add("cat1");
        catagories.add("cat2");
        
       
        // create a mock for the DAO
        dao = mock(daoInterface.class);
        // stub the getMajors method to return the test majors
        when(dao.getCatagories()).thenReturn(catagories);
       
        // create a products collection for stubbing purposes
        Collection<Product> products = new TreeSet<>();
      
        products.add(prodOne);
        products.add(prodTwo);
        products.add(prodThree);
        
        this.size= products.size();
        // stub the getProducts method
        when(dao.getProducts()).thenReturn(products);
        //stub the searchID method
        when(dao.searchID(1)).thenReturn(prodOne);
        //stub filter catagories
        Collection<Product> filteredCats = new TreeSet<>();
        filteredCats.add(prodOne);
        filteredCats.add(prodThree);
        when(dao.filterByCatagory("cat1")).thenReturn(filteredCats);
        //Stub edit product
     
        
        
        // stub the deleteProduct method
        Mockito.doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                products.remove(prodOne);
                return null;
            }
        }).when(dao).deleteProduct(prodOne);

        when(dao.getProducts()).thenReturn(products);
        
    }

    @After
    public void tearDown() {
         //clean up fixture so that it is ready for the next test
        fixture.cleanUp();
    }
    
    @Test
    public void deleteTest() {
        ProductReport dialog = new ProductReport(null, true, dao);
        fixture = new DialogFixture(robot, dialog);
        fixture.show().requireVisible();     
        fixture.list("productsList").selectItem(prodOne.toString());
        fixture.button("deleteProduct").click();
        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);
        // ensure a confirmation dialog is displayed
        DialogFixture confirmDialog = fixture.dialog(withTitle("confirmDelete").andShowing()).requireVisible();

    // click the Yes button on the confirmation dialog
        confirmDialog.button(withText("Yes")).click();
        verify(dao).deleteProduct(argument.capture());
        Product deletedProduct = argument.getValue();
        assertEquals("The correct product was deleted", deletedProduct.getProductID(), prodOne.getProductID());
        assertEquals("The size is", 2, size-1);
       
    }
    @Test
    public void searchByIDTest() {
        ProductReport dialog = new ProductReport(null, true, dao);
        fixture = new DialogFixture(robot, dialog);
        fixture.show().requireVisible();  
        fixture.textBox("searchTxt").enterText("1");
        fixture.button("search").click();
 
        ArgumentCaptor<Integer> argument = ArgumentCaptor.forClass(Integer.class);
        verify(dao).searchID(argument.capture());
        
        SimpleListModel model = (SimpleListModel) fixture.list("productsList").target().getModel();    
        // check the contents
        assertTrue("list contains the correct product", model.contains(prodOne));
        assertEquals("list ONLY contains the correct product", 1, model.getSize());
        
    }
    
@Test
    public void filterByCatagoryTest() {
        ProductReport dialog = new ProductReport(null, true, dao);
        fixture = new DialogFixture(robot, dialog);
        fixture.show().requireVisible();  
        fixture.comboBox("filterBox").selectItem("cat1");     
        
        ArgumentCaptor<String> argument = ArgumentCaptor.forClass(String.class);      
        verify(dao).filterByCatagory(argument.capture());   
        SimpleListModel model = (SimpleListModel) fixture.list("productsList").target().getModel();
        // check the contents
        assertTrue("list contains the correct product", model.contains(prodOne));
        assertEquals("list ONLY contains the correct products", 2, model.getSize());
    }
    
    @Test
    public void editTest() {
        ProductReport dialog = new ProductReport(null, true, dao);
        fixture = new DialogFixture(robot, dialog);
        fixture.show().requireVisible();
        // select item to edit
        fixture.list("productsList").selectItem(prodOne.toString());
       // click the edit button
        fixture.button("editProduct").click();        
        // find the data entry dialog that appears
        DialogFixture editDialog = fixture.dialog("ProductEditor");
        // check that the data entry dialog is displaying selected item
        editDialog.textBox("txtID").requireText(prodOne.getProductID().toString());
        // edit the details
        editDialog.textBox("txtName").selectAll().deleteText().enterText("edit");
        editDialog.comboBox("comboCategory").selectItem("cat2");
        editDialog.textBox("txtPrice").selectAll().deleteText().enterText("70");
        
        editDialog.button("saveButton").click();    
        // create a Mockito argument captor to use to retrieve the passed Product from the mocked DAO
        ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);
        // verify that the DAO.save method was called
        verify(dao).saveProduct(argument.capture());
            
        //ensure edits were made
        Product savedProduct = argument.getValue();
        assertEquals("Ensure the name was saved", "edit", savedProduct.getName());
        assertEquals("Ensure the catagory was saved", "cat2", savedProduct.getCategory());

        assertEquals("Ensure the Price was saved", 70, savedProduct.getPrice(), 0);

    }
}
