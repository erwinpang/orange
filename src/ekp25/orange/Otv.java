package ekp25.orange;

import java.math.BigInteger;
import java.util.NavigableSet;
import java.util.Optional;
import java.util.Set;

import ekp25.orange.ProductException.ErrorCode;
import ekp25.orange.RequestStatus.StatusCode;

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
	
	public void process(Exchange request, RequestStatus requestStatus) throws ProductException{
		SerialNumber exchange = findExchange(request);
		if(exchange == null){
			throw new ProductException(ProductType.OPHONE, this.serialNumber, ErrorCode.UNSUPPORTED_OPERATION);
		}
		else{
			requestStatus.setStatusCode(StatusCode.OK);
			requestStatus.setResult(Optional.of(exchange.getSerialNumber()));
		}
	}
	
	public void process(Refund request, RequestStatus requestStatus) throws ProductException{
		//Succeeds if RMA is positive
		if(request.getRma().signum() == 1){
			requestStatus.setStatusCode(StatusCode.OK);
			requestStatus.setResult(Optional.empty());
		}
		else{
					throw new ProductException(ProductType.OTV, this.serialNumber, ErrorCode.UNSUPPORTED_OPERATION);
		}
	}
	
	public SerialNumber findExchange(Exchange request){
		BigInteger range = new BigInteger("1024");
		BigInteger topLimit = this.serialNumber.getSerialNumber().add(range);
		SerialNumber topLimiter = new SerialNumber(topLimit);
		NavigableSet<SerialNumber> compatibleProducts = request.getCompatibleProducts()
															   .tailSet(this.serialNumber, false)
															   .headSet(topLimiter, true);
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
