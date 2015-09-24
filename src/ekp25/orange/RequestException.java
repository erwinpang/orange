package ekp25.orange;

public class RequestException extends Exception{
	
	ErrorCode errorCode;
	
	public enum ErrorCode{
		INVALID_REQUEST,
		INVALID_RMA;
	}
	
	public RequestException(ErrorCode errorCode){
		this.errorCode = errorCode;
	}
	
	public ErrorCode getErrorCode(){
		return errorCode;
	}

}
