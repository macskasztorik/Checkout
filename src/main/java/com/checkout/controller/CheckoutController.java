package com.checkout.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.checkout.service.CheckoutService;

@RestController
public class CheckoutController {
	
	@Autowired
	private CheckoutService checkoutService;

	/**
	 * This endpoint makes sure that the calculation is available for anyone who
	 * calls the API
	 */
	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public String checkout(@RequestBody String items) {
		
		//We send the request for validation 
		List<String> itemList = validateArray(items);

		if (itemList == null || itemList.isEmpty()){
			return "Request needs to have items. Example: [apple,orange,apple]";
		}
		
		//We send the items for calculation
		return checkoutService.calculation(itemList);
	}

	/**
	 * This validates an incoming String and makes sure that it is formatted properly as an array
	 */
	public static List<String> validateArray(String items){
		ArrayList<String> validatedArray = null;
		try{
			if (items == null || !items.startsWith("[") || !items.endsWith("]") || items.length() < 3){
				return Collections.emptyList();
			} else {
				items = items.substring(1, items.length()-1).replaceAll("\\s","");
			}
			
			validatedArray = new ArrayList<String>(Arrays.asList(items.split(",")));
		}catch(Exception e){
			System.out.println("Items were not found in the request");
			return Collections.emptyList();
		}
		return validatedArray;
		
	}

}
