package ekp25.orange;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class ExchangeTest {

	@Test
	public void testImmutability() {
		BigInteger test = new BigInteger("21951");
		BigInteger test2 = new BigInteger("31923");
		SerialNumber serNum = new SerialNumber(test);
		SerialNumber serNum2 = new SerialNumber(test2);
		Exchange exchange = new Exchange.Builder()
										.addCompatible(serNum)
										.build();
		exchange.getCompatibleProducts().add(serNum2);
		assertEquals(exchange.getCompatibleProducts().size(), 1);
		
	}

}
