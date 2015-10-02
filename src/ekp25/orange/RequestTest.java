package ekp25.orange;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;

import ekp25.orange.RequestStatus.StatusCode;

public class RequestTest {

//	In addition to test cases that you would normally write to test the
//	correctness of your code, you should have test cases that check the
//	request status after request processing.
//	Furthermore, write one more test cases to exchange oPad 1048 with
//	either 1032 or 1244, assuming that the final request status does not have
//	to be checked. This test case should consist of a single instruction in
//	which multiple methods are invoked.
	
	
	@Test
	public void test() throws RequestException, ProductException {
		BigInteger b3 = new BigInteger("1302");
		SerialNumber sn3 = new SerialNumber(b3);
		Opad o3 = (Opad) AbstractProduct.make(ProductType.OPAD, sn3, Optional.empty());

		BigInteger b4 = new BigInteger("105");
		Refund r = new Refund.Builder()
							 .setRma(b4)
							 .build();
		
		RequestStatus requestStatus = new RequestStatus();
		o3.process(r, requestStatus);
		assertEquals(requestStatus.getStatusCode(), StatusCode.OK);
	}

	@Test (expected = ProductException.class)
	public void testExchange() throws RequestException, ProductException{

		BigInteger b = new BigInteger("1050");
		BigInteger b1 = new BigInteger("1034");
		BigInteger b2 = new BigInteger("1246");

		SerialNumber sn = new SerialNumber(b);
		SerialNumber sn1 = new SerialNumber(b1);
		SerialNumber sn2 = new SerialNumber(b2);

		
		Optional<Set<String>> description = Optional.empty();
		
		Opad o = (Opad) AbstractProduct.make(ProductType.OPAD, sn, description);
		
		Exchange e = new Exchange.Builder()
								 .addCompatible(sn1)
								 .addCompatible(sn2)
								 .build();
		o.process(e,  new RequestStatus());
		
	}
}
