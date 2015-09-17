package ekp25.orange;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class SerialNumberTest {

	@Test
	public void test_getSerialNumber() {
		BigInteger bigInt = new BigInteger("2349871928");
		SerialNumber s = new SerialNumber(bigInt);
		assertTrue(bigInt.equals(s.getSerialNumber()));
	}

}
