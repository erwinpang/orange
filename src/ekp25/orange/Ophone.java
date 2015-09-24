package ekp25.orange;

import java.math.BigInteger;
import java.util.Optional;
import java.util.Set;

import ekp25.orange.ProductException.ErrorCode;

public class Ophone extends AbstractProduct{
	SerialNumber serialNumber;
	Optional<Set<String>> description;
	ProductType productType;
	
	Ophone(SerialNumber serialNumber, Optional<Set<String>> description){
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
		return (serialNumber.isEven() && (gcdValue <= 14));
	}
	public void process(Exchange request, RequestStatus requestStatus) throws ProductException{
		throw new ProductException(ProductType.OPHONE, this.serialNumber, ErrorCode.UNSUPPORTED_OPERATION);
	}
	
	public void process(Refund request, RequestStatus requestStats) throws ProductException{
		throw new ProductException(ProductType.OPHONE, this.serialNumber, ErrorCode.UNSUPPORTED_OPERATION);
	}
}
