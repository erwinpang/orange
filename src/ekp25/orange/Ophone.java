package ekp25.orange;

import java.math.BigInteger;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.Set;

import ekp25.orange.ProductException.ErrorCode;
import ekp25.orange.RequestStatus.StatusCode;

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
		SerialNumber exchange = findExchange(request);
		if(exchange == null){
			throw new ProductException(ProductType.OPHONE, this.getSerialNumber(), ErrorCode.UNSUPPORTED_OPERATION);
		}
		else{
			requestStatus.setStatusCode(StatusCode.OK);
			requestStatus.setResult(Optional.of(exchange.getSerialNumber()));
		}
	}
	
	//An oPhone refund succeeds if and only if the serial number can be obtained
	//by shifting to the left the RMA by 1, 2, or 3 bits
	public void process(Refund request, RequestStatus requestStatus) throws ProductException{
		boolean successfulRefund = checkRma(this.getSerialNumber().getSerialNumber(), request.getRma());
		if(successfulRefund){
			requestStatus.setStatusCode(StatusCode.OK);
			requestStatus.setResult(Optional.empty());
		}
		else{
			throw new ProductException(ProductType.OPHONE, this.getSerialNumber(), ErrorCode.UNSUPPORTED_OPERATION);
		}
	}
	
	public boolean checkRma(BigInteger serialNumber, BigInteger rma){
		if(rma.shiftLeft(1).equals(serialNumber)){
			return true;
		}
		else if(rma.shiftLeft(2).equals(serialNumber)){
			return true;
		}
		else if(rma.shiftLeft(3).equals(serialNumber)){
			return true;
		}
		else{
			return false;
		}
	}
	
	public SerialNumber findExchange(Exchange request){
		NavigableSet<SerialNumber> compatibleProducts = request.getCompatibleProducts().tailSet(this.getSerialNumber(), false);
		int total = 0;
		int numElements = 0;
		for(SerialNumber s : compatibleProducts){
			total += s.getSerialNumber().intValue();
			numElements++;
		}
		BigInteger avg = new BigInteger(((Integer) (total/numElements)).toString());
		SerialNumber s = new SerialNumber(avg);
		return compatibleProducts.lower(s);
	}
}
