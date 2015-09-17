package ekp25.orange;

import java.math.BigInteger;
import java.util.Optional;
import java.util.Set;

public class Otv extends AbstractProduct{
	
	SerialNumber serialNumber;
	Optional<Set<String>> description;
	ProductType productType;
	
	Otv(SerialNumber serialNumber, Optional<Set<String>> description){
		super(serialNumber, description);
		this.productType = ProductType.OPOD;
	}
	
	public ProductType getProductType(){
		return productType;
	}
	
	public static Boolean isValidSerialNumber(SerialNumber serialNumber){
		BigInteger bigInt = new BigInteger("630");
		SerialNumber ser = new SerialNumber(bigInt);
		int gcdValue = serialNumber.gcd(ser).intValue();
		return (serialNumber.isOdd() && (gcdValue > 42 ));
	}
}