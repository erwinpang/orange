package ekp25.orange;

import java.math.BigInteger;
import java.util.Optional;

public class RequestStatus {
	
	private StatusCode statusCode;
	private Optional<BigInteger> result;
	
	public enum StatusCode{
		UNKNOWN,
		OK,
		FAIL;
	}
	
	public RequestStatus(){
		this.statusCode = StatusCode.UNKNOWN;
		result = Optional.empty();
	}
	
	public StatusCode getStatusCode(){
		return statusCode;
	}
	
	public Optional<BigInteger> getResult(){
		return result;
	}
	
	public void setStatusCode(StatusCode statusCode){
		this.statusCode = statusCode;
	}
	
	public void setResult(Optional<BigInteger> result){
		this.result = result;
	}

}
