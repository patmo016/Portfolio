/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.daoInterface;
import domain.Product;
//import java.awt.Robot;
import java.util.Collection;
import java.util.TreeSet;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.fixture.DialogFixture;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 *
 * @author patmo016
 */
public class ProductEditorTest {
    
	private daoInterface dao;
	private DialogFixture fixture;
	private Robot robot;

	@Before
	public void setUp() {
		robot = BasicRobot.robotWithNewAwtHierarchy();		
		// slow down the robot a bit - default is 30
		robot.settings().delayBetweenEvents(75);		
		
		// add some majors for testing with
		Collection<String> catagories = new TreeSet<>();
		catagories.add("cat9");
		catagories.add("cat8");
		// create a mock for the DAO
		dao = mock(daoInterface.class);
		// stub the getMajors method to return the test majors
		when(dao.getCatagories()).thenReturn(catagories);
	}

	@After
	public void tearDown() {
		// clean up fixture so that it is ready for the next test
		fixture.cleanUp();
	}
    @Test
    public void testSave() {
		// create the dialog passing in the mocked DAO
                ProductEditor dialog = new ProductEditor(null, true, dao);
		

		// use AssertJ to control the dialog
		fixture = new DialogFixture(robot, dialog);
		fixture.show().requireVisible();

		// enter some details into the UI components
		fixture.textBox("txtID").enterText("1234");
		fixture.textBox("txtName").enterText("Jack");
                fixture.textBox("txtDescription").enterText("description");
                fixture.comboBox("comboCategory").selectItem("cat8");
                fixture.textBox("txtPrice").enterText("12");
                fixture.textBox("txtQuantity").enterText("67");              
		// click the save button
		fixture.button("saveButton").click();
		// create a Mockito argument captor to use to retrieve the passed student from the mocked DAO
		ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);
		// verify that the DAO.save method was called, and capture the passed student
		verify(dao).saveProduct(argument.capture());
		// retrieve the passed student from the captor
		Product savedProduct = argument.getValue();
		// test that the student's details were properly saved
		assertEquals("Ensure the ID was saved", new Integer(1234), (Integer)savedProduct.getProductID());
                //assertEquals("Ensure the ID was saved", "1234", savedProduct.getProductID());
		assertEquals("Ensure the name was saved", "Jack", savedProduct.getName());
		assertEquals("Ensure the catagory was saved", "cat8", savedProduct.getCategory());
                assertEquals("Ensure the Description was saved", "description", savedProduct.getDescription());
                assertEquals("Ensure the Price was saved", 12, savedProduct.getPrice(), 0);
                assertEquals("Ensure the Quantity was saved", 67, savedProduct.getQuantityStock(), 0);
                        
	}
    @Test
	public void testEdit() {
		// a student to edit
		Product editProduct = new Product(1234, "Jack", "Description", "cat8", 67.00, 90.0);
		// create dialog passing in student and mocked DAO
		ProductEditor dialog = new ProductEditor (null, true, editProduct, dao);

		// use AssertJ to control the dialog
		fixture = new DialogFixture(robot, dialog);

		// show the dialog on the screen, and ensure it is visible
		fixture.show().requireVisible();

		// verify that the UI componenents contains the student's details
		fixture.textBox("txtID").requireText("1234");
		fixture.textBox("txtName").requireText("Jack");
		fixture.comboBox("comboCategory").requireSelection("cat8");
                fixture.textBox("txtDescription").requireText("Description");
                fixture.textBox("txtPrice").requireText("67.00");
                fixture.textBox("txtQuantity").requireText("90.0");

		// edit the name and major
		fixture.textBox("txtName").selectAll().deleteText().enterText("Jim");
		fixture.comboBox("comboCategory").selectItem("cat9");

		// click the save button
		fixture.button("saveButton").click();

		// create a Mockito argument captor to use to retrieve the passed student from the mocked DAO
		ArgumentCaptor<Product> argument = ArgumentCaptor.forClass(Product.class);

		// verify that the DAO.save method was called, and capture the passed student
		verify(dao).saveProduct(argument.capture());

		// retrieve the passed student from the captor
		Product editedProduct = argument.getValue();

		// check that the changes were saved
		assertEquals("Ensure the name was changed", "Jim", editedProduct.getName());
		assertEquals("Ensure the major was changed", "cat9", editedProduct.getCategory());
	}
    
}
