package ekp25.orange;

import java.util.Optional;
import java.util.Set;

public class Opad extends AbstractProduct{
	SerialNumber serialNumber;
	Optional<Set<String>> description;
	ProductType productType;
	
	Opad(SerialNumber serialNumber, Optional<Set<String>> description){
		super(serialNumber, description);
		this.productType = ProductType.OPOD;
	}
	
	public ProductType getProductType(){
		return productType;
	}
	
	public static Boolean isValidSerialNumber(SerialNumber serialNumber){
		return (serialNumber.isEven() && serialNumber.testBit(2));
	}
	

}
