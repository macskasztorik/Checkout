package com.checkout;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.checkout.controller.CheckoutController;

public class CheckoutControllerTests {

	CheckoutController checkoutController = null; 
	
	@Before 
	public void init(){
		checkoutController = new CheckoutController();
	}
	
	@Test
	public void validateArrayTestRuns() {
		assertEquals("Checking if the calculation returns with an answer", Collections.emptyList(),  checkoutController.validateArray(null));
	}
	
	@Test
	public void validateArrayTestNull() {
		String input = null;
		assertEquals("Checking if the calculation returns with an answer", Collections.emptyList(),  checkoutController.validateArray(input));
	}
	
	@Test
	public void validateArrayTestEmptyList() {
		String input = "[]";
		assertEquals("Checking if the calculation returns with an answer", Collections.emptyList(),  checkoutController.validateArray(input));
	}
	
	@Test
	public void validateArrayTestWrongFormat() {
		String input = "[apple";
		assertEquals("Checking if the calculation returns with an answer", Collections.emptyList(),  checkoutController.validateArray(input));
	}
	
	@Test
	public void validateArrayTestWhiteSpaces() {
		String input = "[apple,     apple,     orange    ]";
		List<String> expectedList = Arrays.asList("apple", "apple", "orange");
		
		assertEquals("Checking if the calculation returns with an answer",expectedList,  checkoutController.validateArray(input));
	}
	
	

}
