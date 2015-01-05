package com.mobile.nectar;

import java.io.IOException;
import java.util.List;

public class Runner {

	public static void main(String[] args) throws IOException {
		// Last 11 digits of card number on front of card, postcode with or without space
		// and surname, case insensitive.
		Nectar nectar = new Nectar("96452859014", "CH632PR", "Boulton");
		
		NectarAccount account = nectar.getAccount();
		
		System.out.println("Points balance: " + account.getPoints_balance());
		System.out.println("Monetary value: " + account.getCurrency() + " " + account.getMonetary_value());
		List<NectarOffer> nectarOffers = nectar.getOffers();
		
		for(NectarOffer no : nectarOffers) {
			System.out.println("----------------------------------------------------------");
			System.out.println("Offer description: " + no.getOffer_description());
			System.out.println("Type: " + no.getOffer_type() + " Opted in: " + no.isOpted_in());
			System.out.println("Message: " + no.getOfferMessage());
			System.out.println("Valid from " + no.getValid_from() + " to " + no.getValid_till());
		}
		
		System.out.println("* * * Done * * *");
		
	}
}
