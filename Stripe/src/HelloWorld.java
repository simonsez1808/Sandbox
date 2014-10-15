import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.stripe.Stripe;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;
import com.stripe.model.Charge;

import credentials.Credentials;


public class HelloWorld {


	
			public static void main(String[] args) {
				try {
				Stripe.apiKey = Credentials.STRIPE_TEST_SECRET_KEY;
				Map<String, Object> chargeParams = new HashMap<String, Object>();
				chargeParams.put("amount", 400);
				chargeParams.put("currency", "gbp");
				Map<String, String> cardDetails = new HashMap<String, String>();
				cardDetails.put("number", "4242424242424242");
				cardDetails.put("exp_month", "12");
				cardDetails.put("exp_year", "2015");
				cardDetails.put("cvc", "123");
				chargeParams.put("card", cardDetails); // obtained with Stripe.js
				chargeParams.put("description", "Charge for test@example.com");
	
				Charge charge = Charge.create(chargeParams);
	
				System.out.println(charge.getAmount());

				} catch(Exception e) {
					System.out.println("Exception: " + e.getMessage());
				}

			}
	
	}


