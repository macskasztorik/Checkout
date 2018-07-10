package com.checkout.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class CheckoutService {

	// Using a reference to the property file would make the item prices
	// configurable without any recompile - just an idea
	// @Value("#{${price.items}}")
	// private Map<String, Double> ITEMPRICES;

	public Map<String, BigDecimal> itemPrices;
	
	/**This would be nicer on the long run to lead out to a separate POJO where every item could have it's own discount*/
	private final int appleDiscount = 2; //Every 2nd will be free
	private final int orangeDiscount = 3; //Every 3rd will be free
	
	private DecimalFormat dF = new DecimalFormat(".##");

	/**For the sake of the demo, let's fill it up manually instead of using any external resources.*/
	@PostConstruct
	private void init() {
		itemPrices = new HashMap<String, BigDecimal>();
		itemPrices.put("apple", new BigDecimal("0.6") );
		itemPrices.put("orange", new BigDecimal("0.25") );
	}

	
	/**This calculates the sum of the prices of the items */
	public String calculation(List<String> itemList) {
		
		int appleCounter = 1;
		int orangeCounter = 1;

		if (itemList == null || itemList.isEmpty())
			return "Please give a list of items in the request";

		BigDecimal checkoutBalance = new BigDecimal("0");

		// Looping through the user's items
		for (String checkedItem : itemList) {
			
			if (checkedItem.toLowerCase().equals("apple")){
				if (appleCounter < appleDiscount){
					appleCounter++;
				}else{
					appleCounter = 1;
					continue;
				}
				
			}else if(checkedItem.toLowerCase().equals("orange")){
				if (orangeCounter < orangeDiscount){
					orangeCounter++;
				}else{
					orangeCounter = 1;
					continue;
				}
			}
			

			// Calculating the price
			for (Map.Entry<String, BigDecimal> itemPrice : itemPrices.entrySet()) {

				// If we have an unidentified item, we have a potential risk, so
				// can't process
				if (!itemPrices.containsKey(checkedItem)) {
					return "There are unknown items in the basket! Please delete: " + checkedItem;
				}

				// Otherwise we take the cost and add it to the sum
				if (checkedItem.toLowerCase().equals(itemPrice.getKey())) {
					System.out.println("+ " + checkedItem + " £ " + itemPrice.getValue());
					checkoutBalance = checkoutBalance.add(itemPrice.getValue());
				}
			}

		}

		System.out.println("result: " + dF.format(checkoutBalance));
		return "£ " + checkoutBalance;
	}
	

	public void setItemPrices(Map<String, BigDecimal> itemPrices) {
		this.itemPrices = itemPrices;
	}

}
