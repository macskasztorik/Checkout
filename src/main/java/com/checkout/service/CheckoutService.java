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
	
	private DecimalFormat dF = new DecimalFormat(".##");

	@PostConstruct
	private void init() {
		itemPrices = new HashMap<String, BigDecimal>();
		itemPrices.put("apple", new BigDecimal("0.6") );
		itemPrices.put("orange", new BigDecimal("0.25") );
	}

	public String calculation(List<String> itemList) {

		if (itemList == null || itemList.isEmpty())
			return "Please give a list of items in the request";

		BigDecimal checkoutBalance = new BigDecimal("0");

		// Looping through the user's items
		for (String checkedItem : itemList) {

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
