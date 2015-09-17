package ekp25.orange;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public final class Opod extends AbstractProduct{
	SerialNumber serialNumber;
	Optional<Set<String>> description;
	ProductType productType;
	
	Opod(SerialNumber serialNumber, Optional<Set<String>> description){
		super(serialNumber, description);
		this.productType = ProductType.OPOD;
	}
	
	public ProductType getProductType(){
		return productType;
	}
	
	public static Boolean isValidSerialNumber(SerialNumber serialNumber){
		return (serialNumber.isEven() && !serialNumber.testBit(2));
	}
	public static void main(String[] args) throws ProductException{
		BigInteger b = new BigInteger("54736");
		SerialNumber sn = new SerialNumber(b);
		Set<String> s = new HashSet<String>();
		s.add("this is an opod");
		s.add("not a owatch");
		Optional<Set<String>> description = Optional.of(s);
		Opod o = (Opod) AbstractProduct.make(ProductType.OPOD, sn, description);
	}
}
