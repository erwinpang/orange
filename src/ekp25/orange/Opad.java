package ekp25.orange;

import java.util.NavigableSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import ekp25.orange.ProductException.ErrorCode;
import ekp25.orange.RequestStatus.StatusCode;

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

	//An oPad is exchanged with the product that has the largest
	//compatible serial number that is strictly less than the original oPad
	//serial number
	public void process(Exchange request, RequestStatus requestStatus) throws ProductException{
		NavigableSet<SerialNumber> compatibleProducts = new TreeSet<SerialNumber>(request.getCompatibleProducts());
		SerialNumber potentialExchange = new SerialNumber(compatibleProducts.lower(this.serialNumber).getSerialNumber());
		if(compatibleProducts.isEmpty() || (potentialExchange == null)){
			throw new ProductException(ProductType.OPAD, this.serialNumber, ErrorCode.UNSUPPORTED_OPERATION);
		}
		else{
				requestStatus.setStatusCode(StatusCode.OK);
				requestStatus.setResult(Optional.of(potentialExchange.getSerialNumber()));
		}
	}
	
	//An oPad refund succeeds iff the greatest common divisor of
	//the RMA and the serial number is at least 12
	public void process(Refund request, RequestStatus requestStatus) throws ProductException{
		if(request.getRma().gcd(this.serialNumber.getSerialNumber()).intValue() > 12){
			requestStatus.setStatusCode(StatusCode.OK);
			requestStatus.setResult(Optional.empty());
		}
		throw new ProductException(ProductType.OPAD, this.serialNumber, ErrorCode.UNSUPPORTED_OPERATION);
	}
	

}
