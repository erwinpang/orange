package ekp25.orange;

import java.util.Optional;
import java.util.Set;

import ekp25.orange.ProductException.ErrorCode;

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
	
	public void process(Exchange request, RequestStatus requestStatus) throws ProductException{
		throw new ProductException(ProductType.OPOD, this.serialNumber, ErrorCode.UNSUPPORTED_OPERATION);
	}
	
	public void process(Refund request, RequestStatus requestStats) throws ProductException{
		throw new ProductException(ProductType.OPOD, this.serialNumber, ErrorCode.UNSUPPORTED_OPERATION);
	}
}
