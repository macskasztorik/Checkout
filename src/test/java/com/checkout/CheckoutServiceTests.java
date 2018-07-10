package com.checkout;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.checkout.service.CheckoutService;

public class CheckoutServiceTests {

	CheckoutService checkoutService = null; 
	
	@Before 
	public void init(){
		checkoutService = new CheckoutService();
		
		Map<String, BigDecimal> itemPrices = new HashMap<>();
		itemPrices.put("apple", new BigDecimal("0.6"));
		itemPrices.put("orange", new BigDecimal("0.25"));
		checkoutService.setItemPrices(itemPrices);
	}
	
	@Test
	public void calculationTestRun() {
		assertEquals("Checking if the calculation returns with an answer", "Please give a list of items in the request",  checkoutService.calculation(null));
	}
	
	@Test
	public void calculationTestNull() {
		List<String> itemList = null;
		assertEquals("Checking if calculation can deal with null", "Please give a list of items in the request", checkoutService.calculation(itemList));
	}
	
	@Test
	public void calculationTestEmptyList() {
		List<String> itemList = new ArrayList<>();
		assertEquals("Checking if calculation can deal with null", "Please give a list of items in the request", checkoutService.calculation(itemList));
	}
	
	@Test
	public void calculationTestWrongItem() {
		List<String> itemList = Arrays.asList("apple", "orange", "banana");
		assertEquals("Checking if a wrong item gives back error from calculation", "There are unknown items in the basket! Please delete: banana", checkoutService.calculation(itemList));
	}
	
	@Test
	public void calculationTestValues() {
		List<String> itemList = Arrays.asList("apple", "apple", "orange","orange");
		assertEquals("Checking if calculation gives back correct values", "£ 1.70", checkoutService.calculation(itemList));
		
		itemList = Arrays.asList("apple", "apple", "orange","orange","apple", "apple");
		assertEquals("Checking if calculation gives back correct values", "£ 2.90", checkoutService.calculation(itemList));
	}

}
