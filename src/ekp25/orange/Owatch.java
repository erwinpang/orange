package ekp25.orange;

import java.math.BigInteger;
import java.util.Optional;
import java.util.Set;

import ekp25.orange.ProductException.ErrorCode;
import ekp25.orange.RequestStatus.StatusCode;

public class Owatch extends AbstractProduct{
	
	SerialNumber serialNumber;
	Optional<Set<String>> description;
	ProductType productType;
	
	Owatch(SerialNumber serialNumber, Optional<Set<String>> description){
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
		return (serialNumber.isEven() && (14 < gcdValue && gcdValue <= 42 ));
	}
	
	public void process(Exchange request, RequestStatus requestStatus) throws ProductException{
		SerialNumber potentialExchange = request.getCompatibleProducts().higher(this.getSerialNumber());
		if( potentialExchange == null){
			throw new ProductException(ProductType.OWATCH, this.getSerialNumber(), ErrorCode.UNSUPPORTED_OPERATION);
		}
		else{
			requestStatus.setStatusCode(StatusCode.OK);
			requestStatus.setResult(Optional.of(potentialExchange.getSerialNumber()));
		}
	}
	
	public void process(Refund request, RequestStatus requestStatus) throws ProductException{
		if(request.getRma().xor(this.getSerialNumber().getSerialNumber()).intValue() > 14){
			requestStatus.setStatusCode(StatusCode.OK);
			requestStatus.setResult(Optional.empty());
		}
		else{
			throw new ProductException(ProductType.OWATCH, this.getSerialNumber(), ErrorCode.UNSUPPORTED_OPERATION);
		}
	}
}
