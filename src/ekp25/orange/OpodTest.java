package ekp25.orange;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Test;

public class OpodTest {


	@Test
	public void test() throws ProductException {
		BigInteger b = new BigInteger("54736");
		SerialNumber sn = new SerialNumber(b);
		Set<String> s = new HashSet<String>();
		s.add("this is an opod");
		s.add("not a owatch");
		Optional<Set<String>> description = Optional.of(s);
		Opod o = (Opod) AbstractProduct.make(ProductType.OPOD, sn, description);
		assertTrue(o.toString().equals("oPod 54736 This is an opod Not a owatch "));
	}
}
