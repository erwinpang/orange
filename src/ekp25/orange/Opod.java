package ekp25.orange;

import java.util.NavigableSet;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import ekp25.orange.ProductException.ErrorCode;
import ekp25.orange.RequestStatus.StatusCode;

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
		NavigableSet<SerialNumber> compatibleProducts = new TreeSet<SerialNumber>(request.getCompatibleProducts());
		if(compatibleProducts.isEmpty()){
			throw new ProductException(ProductType.OPOD, this.getSerialNumber(), ErrorCode.UNSUPPORTED_OPERATION);
		}
		else{
			requestStatus.setStatusCode(StatusCode.OK);
			requestStatus.setResult(Optional.of(compatibleProducts.first().getSerialNumber()));
		}
	}
	
	public void process(Refund request, RequestStatus requestStatus) throws ProductException{
		if(request.getRma().gcd(this.getSerialNumber().getSerialNumber()).intValue() > 24){
			requestStatus.setStatusCode(StatusCode.OK);
			requestStatus.setResult(Optional.empty());
		}
		else{

			throw new ProductException(ProductType.OPOD, this.getSerialNumber(), ErrorCode.UNSUPPORTED_OPERATION);
		}
	}
}
